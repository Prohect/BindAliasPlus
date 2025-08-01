package com.github.prohect;

import net.minecraft.client.util.InputUtil;
import org.jetbrains.annotations.NotNull;

public record KeyPressed(@NotNull InputUtil.Key key, boolean pressed) {
}
