package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;

public class CyclePerspectiveAlias extends BuiltinAliasWithoutArgs<CyclePerspectiveAlias> {

    public CyclePerspectiveAlias() {
        super("cyclePerspective");
    }

    @Override
    public CyclePerspectiveAlias run(String args) {
        Minecraft minecraftClient = Minecraft.getInstance();
        Options options = minecraftClient.options;

        if (options == null) {
            BindAliasPlusClient.LOGGER.warn("{}[CyclePerspective]Options is null", BindAliasPlusClient.tickPrefix());
            return this;
        }

        // Get current perspective and switch to next
        CameraType currentPerspective = options.getCameraType();
        CameraType nextPerspective = currentPerspective.cycle();
        options.setCameraType(nextPerspective);

        // Update camera entity if switching between first-person and third-person
        if (currentPerspective.isFirstPerson() != nextPerspective.isFirstPerson()) {
            minecraftClient.setCameraEntity(nextPerspective.isFirstPerson() ? minecraftClient.getCameraEntity() : null);
        }

        return this;
    }
}
