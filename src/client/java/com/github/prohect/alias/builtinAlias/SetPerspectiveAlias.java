package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithIntegerArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Perspective;

public class SetPerspectiveAlias extends BuiltinAliasWithIntegerArgs<SetPerspectiveAlias> {

    /**
     * @param args 0 = FIRST_PERSON, 1 = THIRD_PERSON_BACK, 2 = THIRD_PERSON_FRONT
     */
    @Override
    public SetPerspectiveAlias run(String args) {
        parseArgs(args);

        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        GameOptions options = minecraftClient.options;

        if (options == null) {
            BindAliasPlusClient.LOGGER.warn("[SetPerspective]Options is null");
            return this;
        }

        // Validate input range
        if (flag < 0 || flag > 2) {
            BindAliasPlusClient.LOGGER.warn(
                "[SetPerspective]Invalid perspective value: " + flag + ". Must be 0 (first-person), 1 (third-person back), or 2 (third-person front)"
            );
            return this;
        }

        // Get current and target perspectives
        Perspective currentPerspective = options.getPerspective();
        Perspective targetPerspective = Perspective.values()[flag];

        // Only update if different
        if (currentPerspective != targetPerspective) {
            options.setPerspective(targetPerspective);

            // Update camera entity if switching between first-person and third-person
            if (currentPerspective.isFirstPerson() != targetPerspective.isFirstPerson()) {
                minecraftClient.gameRenderer.onCameraEntitySet(
                    targetPerspective.isFirstPerson() ? minecraftClient.getCameraEntity() : null
                );
            }
        }

        return this;
    }
}
