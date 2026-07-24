package com.github.prohect.mcp;

import java.util.concurrent.CompletableFuture;

public final class ScreenshotCapture {
    private ScreenshotCapture() {}
    public static volatile CompletableFuture<byte[]> nextPngFuture;
    public static volatile String lastPath;
    public static volatile String lastName;
}
