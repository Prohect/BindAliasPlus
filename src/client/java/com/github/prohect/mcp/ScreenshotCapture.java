package com.github.prohect.mcp;

import java.util.concurrent.CompletableFuture;

/**
 * Shared state for the {@code NativeImageMixin} → {@link McpHttpServer} screenshot capture pipeline. Static fields live here
 * rather than on the mixin class itself (Mixin forbids non-private static members), and this class is deliberately
 * <b>outside</b> the mixin package so the Mixin framework doesn't try to transform it.
 */
public final class ScreenshotCapture {

    private ScreenshotCapture() {}

    /** Future completed by the mixin with in-memory PNG bytes. */
    public static volatile CompletableFuture<byte[]> nextPngFuture;

    /** Absolute path of the last captured screenshot. */
    public static volatile String lastPath;

    /** File name of the last captured screenshot. */
    public static volatile String lastName;
}
