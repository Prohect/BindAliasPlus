# static-init (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Remarks

Executes once when `McScreenHelper` is first loaded. Resolves all reflection
fields and methods needed to bridge the MC 26.1.x / 26.2+ screen API split.

Algorithm:

1. Attempt to reflect `Minecraft.gui` field and find `Gui.screen()` and
   `Gui.setScreen(Screen)`. If successful, sets `GUI_HAS_SCREEN = true`.
2. If step 1 fails (MC 26.1.x path): reflect `Minecraft.screen` field and
   `Minecraft.setScreen(Screen)` method as fallbacks.

All caught exceptions are silently ignored — the detection is best-effort.
Fields that couldn't be resolved are left `null`.

This must live in a static block (not field initializers) because the two
paths are interdependent: the fallback (Minecraft-direct) path is only needed
if the Gui-path detection fails.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
