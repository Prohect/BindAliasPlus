package com.github.prohect.mcp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ChatCapture {
    private ChatCapture() {}

    private static volatile boolean active;
    private static final List<String> buffer = Collections.synchronizedList(new ArrayList<>());

    public static void begin() {
        buffer.clear();
        active = true;
    }

    public static String end() {
        active = false;
        String r = String.join("\n", buffer);
        buffer.clear();
        return r;
    }

    public static void onSystemMessage(String text) {
        if (active)
            buffer.add(text);
    }
}
