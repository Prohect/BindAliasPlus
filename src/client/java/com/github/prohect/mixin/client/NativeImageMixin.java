package com.github.prohect.mixin.client;

import com.github.prohect.mcp.ScreenshotCapture;
import com.mojang.blaze3d.platform.NativeImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Hooks {@link NativeImage#writeToFile(Path)} to capture PNG bytes in memory before they hit disk. The MCP screenshot endpoint
 * polls {@link ScreenshotCapture#nextPngFuture} instead of reading the filesystem, cutting response time from ~500 ms (sleep +
 * FS scan) to &lt;50 ms (GPU readback + PNG encode).
 */
@Mixin(NativeImage.class)
public abstract class NativeImageMixin {

    /**
     * Access-widened invoker for {@code NativeImage.writeToChannel}. The access widener removes the {@code private} flag so
     * Mixin can generate the bridge; the runtime call still executes the original STB-image PNG encoder on {@code this}.
     */
    @Invoker("writeToChannel")
    abstract boolean invokeWriteToChannel(WritableByteChannel channel) throws IOException;

    @Inject(method = "writeToFile(Ljava/nio/file/Path;)V", at = @At("HEAD"))
    private void onWriteToFile(Path file, CallbackInfo ci) {
        // Only intercept screenshot writes, not other NativeImage usage.
        Path parent = file.getParent();
        if (parent == null || !parent.getFileName().toString().equals("screenshots")) {
            return;
        }

        CompletableFuture<byte[]> f = ScreenshotCapture.nextPngFuture;
        if (f == null)
            return;
        ScreenshotCapture.nextPngFuture = null; // one-shot

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            WritableByteChannel channel = Channels.newChannel(baos);
            if (invokeWriteToChannel(channel)) {
                byte[] bytes = baos.toByteArray();
                ScreenshotCapture.lastPath = file.toAbsolutePath().toString();
                ScreenshotCapture.lastName = file.getFileName().toString();
                f.complete(bytes);
            } else {
                f.complete(null);
            }
        } catch (IOException e) {
            f.completeExceptionally(e);
        }
    }
}
