# onMouseButton method (src/client/java/com/github/prohect/mixin/client/MouseMixin.java)

## Syntax

```java
@Inject(at = @At("HEAD"), method = "onButton")
private void onMouseButton(long window, MouseButtonInfo button, int action, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `window` | `long` | The GLFW window handle; events for non-Minecraft windows are ignored |
| `button` | `net.minecraft.client.input.MouseButtonInfo` | The mouse button info containing the button code and modifiers |
| `action` | `int` | GLFW button action: `0` = release, `1` = press, `2` = repeat (ignored) |
| `ci` | `CallbackInfo` | Unused callback |

## Remarks

Injected at `HEAD` of `MouseHandler#onButton(long, MouseButtonInfo, int)`. Routes mouse button events to `KEY_QUEUE` with the same logic as `KeyBoardMixin.onKey`:

1. **Window guard**: ignores events for non-Minecraft windows.
2. **Text-input guard**: returns immediately if `Alias.isUnderTextInputScreen()` is true (mouse click events should not trigger aliases while typing).
3. **Key resolution**: creates an `InputConstants.Key` from `button.button()` via `Type.MOUSE.getOrCreate()`.
4. **Lock check**: returns if `LockAlias.LOCKED_PHYSICAL_KEYS` contains this key.
5. **Binding lookup**: checks `BindAliasClient.BINDING_PLUS`.
6. **Action filter**: action=2 (repeat) is dropped; action=0 enqueues `KeyPressed(key, false)`, action=1 enqueues `KeyPressed(key, true)`.

## See Also

| Item | Description |
|------|-------------|
| [KeyBoardMixin.onKey](../KeyBoardMixin.java/onKey.md) | Analogous injection for keyboard keys |
| [Alias.isUnderTextInputScreen](../../../alias/Alias.java/isUnderTextInputScreen.md) | The text-input guard used here |
| [LockAlias.LOCKED_PHYSICAL_KEYS](../../../alias/builtinAlias/LockAlias.java/LOCKED_PHYSICAL_KEYS.md) | The lock set that gates input |
