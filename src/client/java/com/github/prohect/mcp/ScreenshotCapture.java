package com.github.prohect.mcp;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Shared state for the {@code NativeImageMixin} → {@link McpHttpServer} screenshot capture pipeline. Static fields live here
 * rather than on the mixin class itself (Mixin forbids non-private static members), and this class is deliberately
 * <b>outside</b> the mixin package so the Mixin framework doesn't try to transform it.
 */
public final class ScreenshotCapture {

    private ScreenshotCapture() {}

    /**
     * Queue of futures waiting to be completed by the mixin with in-memory PNG bytes. Supports multiple concurrent
     * {@code printScreen} calls in one chain.
     */
    private static final Queue<CompletableFuture<byte[]>> pngFutures = new ConcurrentLinkedQueue<>();

    /** Push a future onto the queue for the mixin to complete with PNG bytes (any thread). */
    public static void pushPngFuture(CompletableFuture<byte[]> f) {
        if (f != null)
            pngFutures.add(f);
    }

    /** Remove a specific future from the queue (e.g. on timeout or error — any thread). */
    public static void removePngFuture(CompletableFuture<byte[]> f) {
        if (f != null)
            pngFutures.remove(f);
    }

    /** Poll the next future that wants PNG bytes, or {@code null} if none are queued (called by the mixin on render thread). */
    public static CompletableFuture<byte[]> pollPngFuture() {
        return pngFutures.poll();
    }

    /** Absolute path of the last captured screenshot. */
    public static volatile String lastPath;

    /** File name of the last captured screenshot. */
    public static volatile String lastName;
}
