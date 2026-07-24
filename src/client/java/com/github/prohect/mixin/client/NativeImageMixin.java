package com.github.prohect.mixin.client;

import com.github.prohect.mcp.ScreenshotCapture;
import net.minecraft.client.texture.NativeImage;
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
 * Hooks {@link NativeImage#writeTo(Path)} to capture PNG bytes in memory before
 * they hit disk. The MCP screenshot endpoint polls
 * {@link ScreenshotCapture#nextPngFuture} instead of reading the filesystem,
 * cutting response time from ~500 ms (sleep + FS scan) to &lt;50 ms (GPU
 * readback + PNG encode).
 */
@Mixin(NativeImage.class)
public abstract class NativeImageMixin {

	/**
	 * Access-widened invoker for {@code NativeImage.write}. Mixin generates a
	 * bridge to call the private PNG encoder.
	 */
	@Invoker("write")
	abstract boolean invokeWrite(WritableByteChannel channel) throws IOException;

	@Inject(method = "writeTo(Ljava/nio/file/Path;)V", at = @At("HEAD"))
	private void onWriteTo(Path file, CallbackInfo ci) {
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
			if (invokeWrite(channel)) {
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
