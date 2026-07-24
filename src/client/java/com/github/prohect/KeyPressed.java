package com.github.prohect;

import com.mojang.blaze3d.platform.InputConstants;
import org.jetbrains.annotations.NotNull;

public record KeyPressed(@NotNull InputConstants.Key key, boolean pressed) {
}
