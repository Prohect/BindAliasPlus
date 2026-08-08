package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import com.github.prohect.mcp.GameChannels;
import com.github.prohect.mcp.ScreenshotCapture;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.MinecraftClient;

/**
 * Grabs a screenshot via the {@link ScreenshotCapture} in-memory pipeline and posts the base64 PNG to the {@code agent_msg}
 * channel keyed by clientTick once the async GPU readback + encode completes. Only one screenshot per tick is kept — subsequent
 * calls at the same tick are no-ops.
 */
public class PrintScreenAlias extends BuiltinAliasWithoutArgs<PrintScreenAlias> {

    /** Max wait for the async screenshot pipeline (GPU readback + PNG encode) before giving up on the image. */
    private static final long SCREENSHOT_TIMEOUT_SECONDS = 3;

    public PrintScreenAlias() {
        super("printScreen");
    }

    @Override
    public PrintScreenAlias run(String args) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null)
            return this;

        long clientTick = BindAliasClient.joinTick < 0 ? -1 : (BindAliasClient.currentTick - BindAliasClient.joinTick);

        CompletableFuture<byte[]> pngFuture = new CompletableFuture<>();
        ScreenshotCapture.pushPngFuture(pngFuture);
        try {
            net.minecraft.client.util.ScreenshotRecorder.saveScreenshot(mc.runDirectory, null, mc.getFramebuffer(), 1, msg -> {
            });
        } catch (Exception e) {
            ScreenshotCapture.removePngFuture(pngFuture);
            return this;
        }

        pngFuture.orTimeout(SCREENSHOT_TIMEOUT_SECONDS, TimeUnit.SECONDS).whenComplete((data, err) -> {
            // Already polled from the queue by the mixin — this is a no-op unless grab() threw above
            ScreenshotCapture.removePngFuture(pngFuture);
            if (err == null && data != null) {
                GameChannels.postAgentScreenShot(clientTick, Base64.getEncoder().encodeToString(data));
            }
        });
        return this;
    }
}
