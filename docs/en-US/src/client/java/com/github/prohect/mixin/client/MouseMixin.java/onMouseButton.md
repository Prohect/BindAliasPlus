# onMouseButton method (src/client/java/com/github/prohect/mixin/client/MouseMixin.java)

## Syntax

```java
private void onMouseButton(long, net.minecraft.client.input.MouseButtonInfo, int, org.spongepowered.asm.mixin.injection.callback.CallbackInfo)
```

## Parameters

| Name     | Type              | Description                                                                                |
| -------- | ----------------- | ------------------------------------------------------------------------------------------ |
| `window` | `long`            | GLFW window handle. Ignored if not the main game window.                                   |
| `button` | `MouseButtonInfo` | The mouse button event info containing `button.button()`.                                  |
| `action` | `int`             | GLFW action code: `0` = release, `1` = press, `2` = repeat. Only `0` and `1` are enqueued. |
| `ci`     | `CallbackInfo`    | Mixin callback info (unused).                                                              |

## Remarks

Intercepts raw mouse button events and enqueues `KeyPressed` records for mod-bound mouse buttons into `BindAliasPlusClient.KEY_QUEUE`.

Algorithm:

1. Check if the event's `window` handle matches the main game window; if not, returns early.
2. Create an `InputConstants.Key` from `MouseButtonInfo.button.button()` using `InputConstants.Type.MOUSE.getOrCreate()`.
3. If `Alias.isUnderTextInputScreen()` is true, returns early (avoids interfering with text input).
4. If the mouse button is in `LockAlias.LOCKED_PHYSICAL_KEYS`, skip it.
5. If the mouse button is in `BINDING_PLUS`, create a `KeyPressed` record and add to `KEY_QUEUE`:
   - Action `0`: release (`pressed = false`)
   - Action `1`: press (`pressed = true`)

Differs from `KeyBoardMixin.onKey` in that it has an additional `isUnderTextInputScreen()` guard for mouse events.

Side effects: enqueues `KeyPressed` records into the global queue.

Callers: called by the Mixin framework whenever a mouse button event fires.

## See Also

| Item                                                                                | Description               |
| ----------------------------------------------------------------------------------- | ------------------------- |
| [MouseMixin](MouseMixin.md)                                                         | Owning mixin class        |
| [KeyBoardMixin.onKey](../KeyBoardMixin.java/onKey.md)                               | Same pattern for keyboard |
| [KeyboardInputMixin.tick](../KeyboardInputMixin.java/tick.md)                       | Consumes the queue        |
| [BindAliasPlusClient.KEY_QUEUE](../../../BindAliasPlusClient.java/KEY_QUEUE.md)     | Target queue              |
| [Alias.isUnderTextInputScreen](../../../alias/Alias.java/isUnderTextInputScreen.md) | Extra guard for mouse     |
| [LockAlias](../../../alias/builtinAlias/LockAlias.java/LockAlias.md)                | Lock check                |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
