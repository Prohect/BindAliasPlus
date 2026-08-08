package com.github.prohect.mcp;

import com.github.prohect.BindAliasClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Tracks the last state snapshot sent to the MCP caller and assembles the response envelope:
 *
 * <pre>{@code {"client_tick":N, "state":{...}, "chat":[...], "mod":[...], "sound":[...], "unlocked_recipe":[...]}}</pre>
 * <p>
 * {@code state} is the FULL snapshot when the caller passes {@code verbose} and only the <b>changed</b> members otherwise (a
 * member serialized as {@code null} means it disappeared — e.g. the container screen closed). {@code state} is omitted entirely
 * when nothing changed. {@code held_keys} is the exception: it is included in <b>every</b> envelope while non-empty, because
 * screen transitions re-apply held boolean aliases behind the scenes (see {@code MouseMixin#lockCursor}) and the caller must
 * always know what is currently held.
 * <p>
 * Channels are drained by {@link #finish(String)} — each message is delivered exactly once; empty channels are omitted. The
 * {@code container} and {@code hotbar} members are diffed at slot granularity: full view on verbose / open / menu change,
 * afterwards only changed slots ({@code "item":null} = slot became empty) plus {@code empty_inv}/{@code container_grid} or
 * {@code hotbar_empty} only when they changed. While a container screen is open, the hotbar members are NOT emitted — the
 * container's {@code inventory_items}/{@code empty_inv} already cover slots 1-41; the transition emits
 * {@code "hotbar":null,"hotbar_empty":null} once. {@code selected} (slot index + item stack) is always present. Typical usage
 * per request, in one main-thread roundtrip:
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
     * @param full true for verbose (always every member), false for the changed-members diff
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
            if (full) {
                if (state.length() > 0)
                    state.append(',');
                state.append('"').append(key).append("\":").append(value);
            } else if (key.equals("held_keys")) {
                // held_keys is force-included whenever non-empty (see class javadoc)
                if (!value.equals(last.get(key))) {
                    if (state.length() > 0)
                        state.append(',');
                    state.append('"').append(key).append("\":").append(value);
                }
            } else {
                // Deep-diff: recursively compare JSON so only changed sub-fields are emitted.
                String diffed = diffJson(last.get(key), value);
                if (diffed != null) {
                    if (state.length() > 0)
                        state.append(',');
                    state.append('"').append(key).append("\":").append(diffed);
                }
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
        net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
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

        // hotbar — standalone only while NO container screen is open (an open container's
        // inventory_items/empty_inv already cover slots 1-41). On the transition to a container, the members are
        // nulled once and the baselines dropped, so the next no-container envelope re-emits the full hotbar.
        if (mc.player != null) {
            if (snap != null) {
                if (lastHotbarItems != null) {
                    if (state.length() > 0)
                        state.append(',');
                    state.append("\"hotbar\":null");
                    if (lastHotbarEmpty != null)
                        state.append(",\"hotbar_empty\":null");
                    lastHotbarItems = null;
                    lastHotbarEmpty = null;
                }
            } else {
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
        }

        if (state.length() > 0)
            jsonBuilder.append(",\"state\":{").append(state).append('}');

        last = current;
        return jsonBuilder.toString();
    }

    // ---- deep JSON diff (recursive field-level comparison) ----

    /**
     * Deep-diff two JSON values (serialized as strings). Returns the minimal diff — only changed sub-fields for objects/arrays,
     * the full new value when old was absent, {@code "null"} when the key disappeared, or {@code null} when identical.
     */
    private static String diffJson(String oldVal, String newVal) {
        if (oldVal == null)
            return newVal;
        if (newVal == null)
            return "null";
        if (oldVal.equals(newVal))
            return null;

        try {
            JsonElement oldElem = JsonParser.parseString(oldVal);
            JsonElement newElem = JsonParser.parseString(newVal);
            String diffed = diffElement(oldElem, newElem);
            return diffed != null ? diffed : newVal;
        } catch (Exception e) {
            return newVal; // parse error → fall back to full replacement
        }
    }

    private static String diffElement(JsonElement oldElem, JsonElement newElem) {
        if (oldElem == null)
            return newElem.toString();
        if (newElem.isJsonNull())
            return oldElem.isJsonNull() ? null : "null";
        if (oldElem.isJsonNull())
            return newElem.toString();

        if (oldElem.isJsonObject() && newElem.isJsonObject())
            return diffObject(oldElem.getAsJsonObject(), newElem.getAsJsonObject());
        if (oldElem.isJsonArray() && newElem.isJsonArray())
            return diffArray(oldElem.getAsJsonArray(), newElem.getAsJsonArray());

        return oldElem.equals(newElem) ? null : newElem.toString();
    }

    /** Recursively diff two JSON objects: only emit fields that were added, changed, or removed. */
    private static String diffObject(JsonObject oldObj, JsonObject newObj) {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;

        for (Map.Entry<String, JsonElement> e : newObj.entrySet()) {
            String key = e.getKey();
            String diffed = diffElement(oldObj.get(key), e.getValue());
            if (diffed != null) {
                if (!first)
                    sb.append(',');
                first = false;
                sb.append('"').append(key).append("\":").append(diffed);
            }
        }
        for (Map.Entry<String, JsonElement> e : oldObj.entrySet()) {
            String key = e.getKey();
            if (!newObj.has(key)) {
                if (!first)
                    sb.append(',');
                first = false;
                sb.append('"').append(key).append("\":null");
            }
        }

        sb.append('}');
        return first ? null : sb.toString();
    }

    /** Arrays are diffed as a whole — positional element diffs are too ambiguous to be useful. */
    private static String diffArray(JsonArray oldArr, JsonArray newArr) {
        return oldArr.equals(newArr) ? null : newArr.toString();
    }

    // ---- envelope assembly ----
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
        // agent_msg entries are already JSON objects — insert raw, NOT jsonEscape-wrapped
        List<String> agentMsgs = GameChannels.drainAgentMsg();
        if (!agentMsgs.isEmpty()) {
            sb.append(",\"").append(GameChannels.AGENT_MSG).append("\":[");
            boolean first = true;
            for (String msg : agentMsgs) {
                if (!first)
                    sb.append(',');
                first = false;
                sb.append(msg);
            }
            sb.append(']');
        }
        return sb.append('}').toString();
    }

    /**
     * Close the envelope WITHOUT draining message channels — used by intermediate snap captures so only the last one drains.
     */
    public static String finishNoDrain(String begun) {
        return begun + '}';
    }
}
