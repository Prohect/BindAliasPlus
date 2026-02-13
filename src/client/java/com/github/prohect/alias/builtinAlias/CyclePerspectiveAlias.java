package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Perspective;

public class CyclePerspectiveAlias
    extends BuiltinAliasWithoutArgs<CyclePerspectiveAlias>
{

    @Override
    public CyclePerspectiveAlias run(String args) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        GameOptions options = minecraftClient.options;

        if (options == null) {
            BindAliasPlusClient.LOGGER.warn(
                "[CyclePerspective]Options is null"
            );
            return this;
        }

        // Get current perspective and switch to next
        Perspective currentPerspective = options.getPerspective();
        Perspective nextPerspective = currentPerspective.next();
        options.setPerspective(nextPerspective);

        // Update camera entity if switching between first-person and third-person
        if (
            currentPerspective.isFirstPerson() !=
            nextPerspective.isFirstPerson()
        ) {
            minecraftClient.gameRenderer.onCameraEntitySet(
                nextPerspective.isFirstPerson()
                    ? minecraftClient.getCameraEntity()
                    : null
            );
        }

        return this;
    }
}
