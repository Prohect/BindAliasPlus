package com.github.prohect.mcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

/**
 * Channel-based message hub feeding the MCP response envelope.
 * <p>
 * Channels (each drained independently, delivered exactly once):
 * <ul>
 * <li>{@link #CHAT} — game chat (server/system/player messages), fed by {@code ChatComponentMixin}.</li>
 * <li>{@link #MOD} — this mod's log output (alias feedback, errors, {@code log\} messages), fed by a Log4j appender on the
 * {@code "bind-alias"} logger.</li>
 * <li>{@link #SOUND} — sound events (subtitle-audible sounds only) with precise direction and distance, fed by
 * {@link SoundCapture}. Repeats of the same sound coalesce <b>by key</b> (even when interleaved with other sounds) into one
 * updating line with an {@code " xN"} counter, until drained.</li>
 * <li>{@link #RECIPE} — newly unlocked recipes (toast notifications), fed by {@code ClientPacketListenerMixin}.</li>
 * </ul>
 * Every channel is a bounded, insertion-ordered buffer with a monotonic cursor; {@link #drain()} returns messages posted since
 * the previous drain and is zero-cost when nothing new arrived.
 */
public final class GameChannels {

    public static final String CHAT = "chat";
    public static final String MOD = "mod";
    public static final String SOUND = "sound";
    public static final String RECIPE = "recipe";

    private static final int MAX_BUFFER = 100;
    private static final Object lock = new Object();
    private static final Map<String, Channel> CHANNELS = new LinkedHashMap<>();

    static {
        CHANNELS.put(CHAT, new Channel(false));
        CHANNELS.put(MOD, new Channel(false));
        CHANNELS.put(SOUND, new Channel(true));
        CHANNELS.put(RECIPE, new Channel(false));
    }

    private GameChannels() {}

    private static final class Entry {
        final String key;
        String text;
        int count;

        Entry(String key, String text) {
            this.key = key;
            this.text = text;
            this.count = 1;
        }
    }

    private static final class Channel {
        final boolean coalescing;
        /** insertion-ordered: cursor → entry */
        final LinkedHashMap<Long, Entry> entries = new LinkedHashMap<>();
        /** coalescing only: key → cursor of its latest entry */
        final Map<String, Long> byKey = new HashMap<>();
        /** Total logical entries ever posted (equals entry cursors). */
        long cursor;
        /** Cursor snapshot from the last drain. */
        long lastSent;

        Channel(boolean coalescing) {
            this.coalescing = coalescing;
        }

        void append(Entry entry) {
            entries.put(++cursor, entry);
            if (coalescing && entry.key != null)
                byKey.put(entry.key, cursor);
            while (entries.size() > MAX_BUFFER) {
                Long eldestCursor = entries.keySet().iterator().next();
                Entry eldest = entries.remove(eldestCursor);
                if (eldest != null && eldest.key != null && eldestCursor.equals(byKey.get(eldest.key)))
                    byKey.remove(eldest.key);
            }
        }
    }

    /** Post a message to a channel (any thread). */
    public static void post(String channel, String message) {
        if (message == null || message.isEmpty())
            return;
        Channel ch = CHANNELS.get(channel);
        if (ch == null)
            return;
        synchronized (lock) {
            ch.append(new Entry(null, message));
        }
    }

    /**
     * Post a message with coalescing: if an <b>undrained</b> entry with the same {@code key} exists, it is updated in place
     * (new text, {@code " xN"} counter for N &gt; 1, original queue position kept) instead of appending — keeps spammy
     * repeating sources (footsteps, ambient crackles) to one updating line each, even when interleaved.
     */
    public static void postCoalescing(String channel, String key, String message) {
        if (message == null || message.isEmpty())
            return;
        Channel ch = CHANNELS.get(channel);
        if (ch == null)
            return;
        synchronized (lock) {
            Long existing = key == null ? null : ch.byKey.get(key);
            Entry entry = existing == null ? null : ch.entries.get(existing);
            if (entry != null && existing > ch.lastSent) {
                entry.count++;
                entry.text = entry.count > 1 ? message + " x" + entry.count : message;
            } else {
                ch.append(new Entry(key, message));
            }
        }
    }

    /**
     * Drain new messages of every channel since the previous drain. Thread-safe.
     *
     * @return non-empty channel name → new messages (insertion order: chat, mod, sound, recipe); empty map when nothing new.
     */
    public static Map<String, List<String>> drain() {
        synchronized (lock) {
            Map<String, List<String>> out = new LinkedHashMap<>();
            for (Map.Entry<String, Channel> e : CHANNELS.entrySet()) {
                Channel ch = e.getValue();
                if (ch.cursor == ch.lastSent)
                    continue;
                List<String> messages = new ArrayList<>();
                for (Map.Entry<Long, Entry> en : ch.entries.entrySet()) {
                    if (en.getKey() > ch.lastSent)
                        messages.add(en.getValue().text);
                }
                ch.lastSent = ch.cursor;
                if (!messages.isEmpty())
                    out.put(e.getKey(), messages);
            }
            return out;
        }
    }

    /** Mark every channel as read (call on world join so stale title-screen noise is not delivered). */
    public static void resetAll() {
        synchronized (lock) {
            for (Channel ch : CHANNELS.values()) {
                ch.lastSent = ch.cursor;
                if (ch.coalescing)
                    ch.byKey.clear();
            }
        }
    }

    // ---- Log4j appender (one-time init, feeds the MOD channel) ----

    private static final String LOGGER_NAME = "bind-alias";
    private static final Object initLock = new Object();
    private static boolean initialized;

    /** Register a Log4j appender feeding the mod's log output into {@link #MOD}. Safe to call multiple times. */
    public static void init() {
        synchronized (initLock) {
            if (initialized)
                return;
            initialized = true;
        }
        try {
            LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
            Appender appender = new AbstractAppender("BindAlias-LogCapture", null, null, false, null) {
                @Override
                public void append(LogEvent event) {
                    String msg = event.getMessage().getFormattedMessage();
                    if (msg != null && !msg.isEmpty())
                        post(MOD, msg);
                }
            };
            appender.start();
            // Register on our mod's logger only — skips Fabric/mixin/rendering noise from root.
            // NOTE: logger.addAppender() (AbstractConfiguration.addLoggerAppender) would create the
            // child LoggerConfig inheriting the ROOT's additivity — false for the root logger — which
            // swallows all bind-alias logs so they never reach console/latest.log again.
            // Build the child LoggerConfig with additive=true so normal output keeps flowing.
            Configuration config = ctx.getConfiguration();
            LoggerConfig lc = config.getLoggerConfig(LOGGER_NAME);
            if (lc.getName().equals(LOGGER_NAME)) {
                lc.addAppender(appender, null, null);
            } else {
                LoggerConfig nlc = new LoggerConfig(LOGGER_NAME, lc.getLevel(), true);
                nlc.addAppender(appender, null, null);
                nlc.setParent(lc);
                config.addLogger(LOGGER_NAME, nlc);
            }
            ctx.updateLoggers();
        } catch (Exception ignored) {
            // Log capture is best-effort; don't crash the mod if Log4j internals change.
        }
    }
}
