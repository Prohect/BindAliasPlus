# onKey method (src/client/java/com/github/prohect/mixin/client/KeyBoardMixin.java)

## Syntax

```java
@Inject(at = @At("HEAD"), method = "keyPress")
private void onKey(long window, int action, KeyEvent event, CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `window` | `long` | The GLFW window handle; events for non-Minecraft windows are ignored |
| `action` | `int` | GLFW key action: `0` = release, `1` = press, `2` = repeat (ignored) |
| `event` | `net.minecraft.client.input.KeyEvent` | The key event containing the key code and modifiers |
| `ci` | `CallbackInfo` | Unused callback |

## Remarks

Injected at `HEAD` of `KeyboardHandler#keyPress(long, int, KeyEvent)`. Processing logic:

1. **Window guard**: if `window != Minecraft.getInstance().getWindow().handle()`, returns immediately.
2. **Key resolution**: creates an `InputConstants.Key` from the event's key code (`Type.KEYSYM.getOrCreate(event.key())`).
3. **Lock check**: if `LockAlias.LOCKED_PHYSICAL_KEYS` contains this key, returns immediately — locked keys never reach the alias system.
4. **Binding lookup**: checks `BindAliasClient.BINDING_PLUS` for a matching `BindAliasKeyBinding`.
5. **Action filter**: action=2 (repeat) is silently dropped; action=0 enqueues `KeyPressed(key, false)`, action=1 enqueues `KeyPressed(key, true)`.

The actual alias execution happens later in the same tick via [`KeyboardInputMixin.tick`](../KeyboardInputMixin.java/tick.md) which drains `KEY_QUEUE`.

## See Also

| Item | Description |
|------|-------------|
| [LockAlias.LOCKED_PHYSICAL_KEYS](../../../alias/builtinAlias/LockAlias.java/LOCKED_PHYSICAL_KEYS.md) | The lock set that gates key processing |
| [BindAliasClient.BINDING_PLUS](../../../BindAliasClient.java/BINDING_PLUS.md) | The key→key-binding lookup map |
| [KeyPressed](../../../KeyPressed.java/README.md) | The record enqueued to `KEY_QUEUE` |
| [MouseMixin.onMouseButton](../MouseMixin.java/onMouseButton.md) | Analogous injection for mouse buttons |
