package com.github.prohect.mcp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

/**
 * Cross-thread capture of game log messages for MCP tools.
 * <p>
 * Two modes:
 * <ul>
 * <li><b>Windowed</b> — {@link #begin()} / {@link #end()} around a command to collect that command's feedback output.</li>
 * <li><b>Diff</b> — persistent ring buffer of the most recent {@value #MAX_DIFF_BUFFER} messages; {@link #diff()} returns
 * messages since the last call and is zero-cost when nothing new arrived.</li>
 * </ul>
 * <p>
 * On startup, a Log4j appender is registered on the {@code "bind-alias-plus"} logger to capture mod log output
 * ({@code LOGGER.info()} calls from aliases, CFG autoload, etc.). The {@link ChatComponentMixin} feeds chat messages.
 */
public final class ChatCapture {

    private ChatCapture() {}

    // ---- Log4j appender (one-time init, captures ALL log output) ----

    private static final String LOGGER_NAME = "bind-alias-plus";
    private static final Object initLock = new Object();
    private static boolean initialized;

    /** Register a Log4j appender that feeds all log messages into {@link #onMessage(String)}. Safe to call multiple times. */
    public static void init() {
        synchronized (initLock) {
            if (initialized)
                return;
            initialized = true;
        }
        try {
            LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
            Appender appender = new AbstractAppender("BindAliasPlus-LogCapture", null, null, false, null) {
                @Override
                public void append(LogEvent event) {
                    String msg = event.getMessage().getFormattedMessage();
                    if (msg != null && !msg.isEmpty())
                        onMessage(msg);
                }
            };
            appender.start();
            // Register on our mod's logger only — skips Fabric/mixin/rendering noise from root.
            // NOTE: logger.addAppender() (AbstractConfiguration.addLoggerAppender) would create the
            // child LoggerConfig inheriting the ROOT's additivity — false for the root logger — which
            // swallows all bind-alias-plus logs so they never reach console/latest.log again.
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

    // ---- windowed capture (begin/end) ----

    private static volatile boolean active;
    private static final List<String> buffer = Collections.synchronizedList(new ArrayList<>());

    /** Start capturing system messages. */
    public static void begin() {
        buffer.clear();
        active = true;
    }

    /** Stop capturing, drain and return captured text joined with newlines. */
    public static String end() {
        active = false;
        String[] snapshot;
        synchronized (buffer) {
            snapshot = buffer.toArray(new String[0]);
            buffer.clear();
        }
        return snapshot.length == 0 ? "" : String.join("\n", snapshot);
    }

    /** @return true while a capture is in progress. */
    public static boolean isActive() {
        return active;
    }

    // ---- persistent diff (always-on ring buffer) ----

    private static final int MAX_DIFF_BUFFER = 200;
    /** Guard for diffBuffer and diffCursor. */
    private static final Object diffLock = new Object();
    private static final Deque<String> diffBuffer = new ArrayDeque<>(MAX_DIFF_BUFFER);
    /** Monotonically increasing counter — equals total messages ever added. */
    private static long diffCursor;
    /** Snapshot of diffCursor from the last {@link #diff()} call. */
    private static long diffLastSent;

    /**
     * Return new messages since the last {@link #diff()} call (or since startup on first call). Thread-safe, can be called from
     * HTTP thread.
     *
     * @return messages that arrived since previous {@code diff()}, joined by newlines; empty string if none.
     */
    public static String diff() {
        synchronized (diffLock) {
            if (diffCursor == diffLastSent)
                return "";

            long oldestInBuffer = diffCursor - diffBuffer.size();
            long start = Math.max(diffLastSent, oldestInBuffer);
            int skip = (int) (start - oldestInBuffer);

            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (String msg : diffBuffer) {
                if (i >= skip) {
                    if (sb.length() > 0)
                        sb.append('\n');
                    sb.append(msg);
                }
                i++;
            }

            diffLastSent = diffCursor;
            return sb.toString();
        }
    }

    /** Reset the diff cursor so the next {@link #diff()} only returns messages after this point. */
    public static void resetDiff() {
        synchronized (diffLock) {
            diffLastSent = diffCursor;
        }
    }

    // ---- unified entry point (called by mixin and Log4j appender) ----

    /** Called by the mixin and Log4j appender when a message is produced (any thread). */
    public static void onMessage(String text) {
        // Windowed capture
        if (active)
            buffer.add(text);
        // Persistent diff buffer
        synchronized (diffLock) {
            if (diffBuffer.size() >= MAX_DIFF_BUFFER)
                diffBuffer.pollFirst();
            diffBuffer.addLast(text);
            diffCursor++;
        }
    }
}
