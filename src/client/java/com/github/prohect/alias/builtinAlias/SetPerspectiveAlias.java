package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithIntegerArgs;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;

public class SetPerspectiveAlias extends BuiltinAliasWithIntegerArgs<SetPerspectiveAlias> {

    public SetPerspectiveAlias() {
        super("builtinSetPerspective");
    }

    /**
     * @param args 0 = FIRST_PERSON, 1 = THIRD_PERSON_BACK, 2 = THIRD_PERSON_FRONT
     */
    @Override
    public SetPerspectiveAlias run(String args) {
        parseArgs(args);

        Minecraft minecraftClient = Minecraft.getInstance();
        Options options = minecraftClient.options;

        if (options == null) {
            BindAliasClient.LOGGER.warn("{}[SetPerspective]Options is null", BindAliasClient.tickPrefix());
            return this;
        }

        // Validate input range
        if (flag < 0 || flag > 2) {
            BindAliasClient.LOGGER.warn(
                    "{}[SetPerspective]Invalid perspective value: {}. Must be 0 (first-person), 1 (third-person back), or 2 (third-person front)",
                    BindAliasClient.tickPrefix(), flag);
            return this;
        }

        // Get current and target perspectives
        CameraType currentPerspective = options.getCameraType();
        CameraType targetPerspective = CameraType.values()[flag];

        // Only update if different
        if (currentPerspective != targetPerspective) {
            options.setCameraType(targetPerspective);

            // Update camera entity if switching between first-person and third-person
            if (currentPerspective.isFirstPerson() != targetPerspective.isFirstPerson()) {
                minecraftClient.setCameraEntity(targetPerspective.isFirstPerson() ? minecraftClient.getCameraEntity() : null);
            }
        }

        return this;
    }
}
