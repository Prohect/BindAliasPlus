# static-init (src/client/java/com/github/prohect/util/McScreenHelper.java)

## Remarks

Executed once when the `McScreenHelper` class is loaded. Uses reflection to detect which Minecraft screen-access API is available:

1. Tries to get the `gui` field from `Minecraft.class` (present in both 26.1.x and 26.2+).
2. Checks whether the `gui` field's type exposes a `screen()` method:
   - **If yes** (26.2+): caches the `gui` field, `screen()` method, and `setScreen(Screen)` method on the `Gui` class. Sets `GUI_HAS_SCREEN = true`. The `Minecraft.screen`/`setScreen` fields remain null.
   - **If no** (26.1.x): reflects the `screen` field and `setScreen(Screen)` method directly on `Minecraft.class`. Sets `GUI_HAS_SCREEN = false`. The `Gui`-related handles remain null.

All reflected handles are made accessible via `setAccessible(true)`. Exceptions during detection are silently swallowed — if neither API is found, `getCurrentScreen`/`setScreen` will throw `RuntimeException` at call time.

## See Also

| Item | Description |
|------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | Uses `GUI_HAS_SCREEN` and the cached handles |
| [setScreen](setScreen.md) | Uses `GUI_HAS_SCREEN` and the cached handles |
