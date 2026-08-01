package com.github.prohect.mcp;

import com.github.prohect.BindAliasClient;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Tracks the last state snapshot sent to the MCP caller and assembles the response envelope:
 *
 * <pre>{@code {"client_tick":N, "state":{...}, "chat":[...], "mod":[...], "sound":[...], "unlocked_recipe":[...]}}</pre>
 * <p>
 * {@code state} is the FULL snapshot for {@code getState} and only the <b>changed</b> members for every other tool (a member
 * serialized as {@code null} means it disappeared — e.g. the container screen closed). {@code state} is omitted entirely when
 * nothing changed. {@code held_keys} is the exception: it is included in <b>every</b> envelope while non-empty, because screen
 * transitions re-apply held boolean aliases behind the scenes (see {@code MouseMixin#lockCursor}) and the caller must always
 * know what is currently held.
 * <p>
 * Channels are drained by {@link #finish(String)} — each message is delivered exactly once; empty channels are omitted. The
 * {@code container} and {@code hotbar} members are diffed at slot granularity: full view on getState / open / menu change,
 * afterwards only changed slots ({@code "item":null} = slot became empty) plus {@code empty_inv}/{@code container_grid} or
 * {@code hotbar_empty} only when they changed. Typical usage per request, in one main-thread roundtrip:
 *
 * <pre>
 * String env = StateTracker.begin(false); // state snapshot BEFORE the action
 * // ... run alias chain / send command ...
 * String json = StateTracker.finish(env); // drain messages produced BY the action
 * </pre>
 */
public final class StateTracker {

    private static Map<String, String> last = Map.of();
    private static GameStateCollector.ContainerSnapshot lastContainer;
    private static Map<String, String> lastHotbarItems;
    private static String lastHotbarEmpty;
    private static long baselineJoinTick = Long.MIN_VALUE;

    private StateTracker() {}

    /** Forget the baseline (call on world join / disconnect so the next envelope is full). */
    public static synchronized void reset() {
        last = Map.of();
        lastContainer = null;
        lastHotbarItems = null;
        lastHotbarEmpty = null;
        baselineJoinTick = Long.MIN_VALUE;
    }

    /**
     * Begin an envelope: snapshot the current state and emit {@code {"client_tick":N[,"state":{...}]}}. A world change since
     * the previous call forces a full snapshot. Must be called on the Minecraft main thread.
     *
     * @param full true for getState (always every member), false for the changed-members diff
     */
    public static synchronized String begin(boolean full) {
        if (BindAliasClient.joinTick != baselineJoinTick) {
            last = Map.of();
            lastContainer = null;
            lastHotbarItems = null;
            lastHotbarEmpty = null;
            baselineJoinTick = BindAliasClient.joinTick;
            full = true;
        }
        LinkedHashMap<String, String> current = GameStateCollector.collect();

        StringBuilder jsonBuilder = new StringBuilder(2048);
        jsonBuilder.append("{\"client_tick\":")
                .append(BindAliasClient.joinTick < 0 ? -1 : (BindAliasClient.currentTick - BindAliasClient.joinTick));

        StringBuilder state = new StringBuilder();
        for (Map.Entry<String, String> e : current.entrySet()) {
            String key = e.getKey();
            String value = e.getValue();
            // held_keys is force-included whenever non-empty (see class javadoc)
            if (full || key.equals("held_keys") || !value.equals(last.get(key))) {
                if (state.length() > 0)
                    state.append(',');
                state.append('"').append(key).append("\":").append(value);
            }
        }
        // members that disappeared since the previous snapshot → explicit null
        for (String key : last.keySet()) {
            if (!current.containsKey(key)) {
                if (state.length() > 0)
                    state.append(',');
                state.append('"').append(key).append("\":null");
            }
        }

        // container — diffed at slot granularity (full on getState / open / menu change)
        net.minecraft.client.MinecraftClient mc = net.minecraft.client.MinecraftClient.getInstance();
        GameStateCollector.ContainerSnapshot snap = GameStateCollector.containerSnapshot(mc, mc.player);
        if (snap == null) {
            if (lastContainer != null) {
                if (state.length() > 0)
                    state.append(',');
                state.append("\"container\":null");
                lastContainer = null;
            }
        } else if (full || lastContainer == null || lastContainer.menuIdentity != snap.menuIdentity) {
            if (state.length() > 0)
                state.append(',');
            state.append("\"container\":").append(GameStateCollector.containerFullJson(snap));
            lastContainer = snap;
        } else {
            String diff = GameStateCollector.containerDiffJson(lastContainer, snap);
            if (diff != null) {
                if (state.length() > 0)
                    state.append(',');
                state.append("\"container\":").append(diff);
            }
            lastContainer = snap;
        }

        // hotbar — diffed at slot granularity (full on getState / world change)
        if (mc.player != null) {
            Map<String, String> curHotbar = GameStateCollector.hotbarItems(mc.player);
            String curEmpty = GameStateCollector.hotbarEmptyRanges(mc.player);

            if (full || lastHotbarItems == null) {
                if (state.length() > 0)
                    state.append(',');
                state.append("\"hotbar\":").append(GameStateCollector.hotbarFullJson(curHotbar));
                if (curEmpty != null) {
                    state.append(",\"hotbar_empty\":").append(GameStateCollector.jsonEscape(curEmpty));
                } else if (lastHotbarEmpty != null) {
                    state.append(",\"hotbar_empty\":null");
                }
            } else {
                String hbDiff = GameStateCollector.hotbarDiffJson(lastHotbarItems, curHotbar);
                if (hbDiff != null) {
                    if (state.length() > 0)
                        state.append(',');
                    state.append("\"hotbar\":").append(hbDiff);
                }
                if (!java.util.Objects.equals(curEmpty, lastHotbarEmpty)) {
                    if (state.length() > 0)
                        state.append(',');
                    state.append("\"hotbar_empty\":")
                            .append(curEmpty == null ? "null" : GameStateCollector.jsonEscape(curEmpty));
                }
            }
            lastHotbarItems = curHotbar;
            lastHotbarEmpty = curEmpty;
        }

        if (state.length() > 0)
            jsonBuilder.append(",\"state\":{").append(state).append('}');

        last = current;
        return jsonBuilder.toString();
    }

    /** Drain all message channels into the envelope and close it. Thread-safe. */
    public static String finish(String begun) {
        StringBuilder sb = new StringBuilder(begun);
        Map<String, List<String>> channels = GameChannels.drain();
        for (Map.Entry<String, List<String>> e : channels.entrySet()) {
            sb.append(",\"").append(e.getKey()).append("\":[");
            boolean first = true;
            for (String msg : e.getValue()) {
                if (!first)
                    sb.append(',');
                first = false;
                sb.append(GameStateCollector.jsonEscape(msg));
            }
            sb.append(']');
        }
        return sb.append('}').toString();
    }
}
