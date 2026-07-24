package com.github.prohect.mcp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Cross-thread capture of system chat messages (command feedback). Set via {@link #begin()} / {@link #end()} around a command
 * execution to collect {@code sendFeedback} output. Uses a plain volatile flag + synchronized list because the capture window
 * is set on the HTTP thread but the mixin fires on the game (render) thread.
 */
public final class ChatCapture {

    private ChatCapture() {}

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
        // Drain atomically — build return value from a snapshot
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

    /** Called by the mixin when a system message is added (any thread). */
    public static void onSystemMessage(String text) {
        if (active) {
            buffer.add(text);
        }
    }
}
