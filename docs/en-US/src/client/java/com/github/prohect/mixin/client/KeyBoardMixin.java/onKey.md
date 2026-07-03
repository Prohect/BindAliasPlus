# onKey method (src/client/java/com/github/prohect/mixin/client/KeyBoardMixin.java)

## Syntax

```java
private void onKey(long, int, net.minecraft.client.input.KeyEvent, org.spongepowered.asm.mixin.injection.callback.CallbackInfo)
```

## Parameters

| Name     | Type           | Description                                                                                |
| -------- | -------------- | ------------------------------------------------------------------------------------------ |
| `window` | `long`         | GLFW window handle. Ignored if not the main game window.                                   |
| `action` | `int`          | GLFW action code: `0` = release, `1` = press, `2` = repeat. Only `0` and `1` are enqueued. |
| `event`  | `KeyEvent`     | The key event containing the key code (`event.key()`).                                     |
| `ci`     | `CallbackInfo` | Mixin callback info (unused).                                                              |

## Remarks

Intercepts raw keyboard events and enqueues `KeyPressed` records for mod-bound keys into `BindAliasPlusClient.KEY_QUEUE`.

Algorithm:

1. Check if the event's `window` handle matches the main game window; if not, returns early (e.g., ignores events from other GLFW windows).
2. Create an `InputConstants.Key` from the event's key code using `InputConstants.Type.KEYSYM.getOrCreate()`.
3. If the key is in `LockAlias.LOCKED_PHYSICAL_KEYS`, skip it (the key's action is locked).
4. If the key is in `BINDING_PLUS`, create a `KeyPressed(keyFromCode, pressed)` record and add it to `KEY_QUEUE`:
   - Action `0` (release): `new KeyPressed(keyFromCode, false)`
   - Action `1` (press): `new KeyPressed(keyFromCode, true)`

Side effects: enqueues `KeyPressed` records into the global queue. The queue is drained by `KeyboardInputMixin.tick()` on the next tick.

Callers: called by the Mixin framework whenever a key event fires. The `KeyboardHandler.keyPress()` is GLFW's keyboard callback entry point.

## See Also

| Item                                                                            | Description            |
| ------------------------------------------------------------------------------- | ---------------------- |
| [KeyBoardMixin](KeyBoardMixin.md)                                               | Owning mixin class     |
| [KeyboardInputMixin.tick](../KeyboardInputMixin.java/tick.md)                   | Consumes the queue     |
| [MouseMixin.onMouseButton](../MouseMixin.java/onMouseButton.md)                 | Same pattern for mouse |
| [BindAliasPlusClient.KEY_QUEUE](../../../BindAliasPlusClient.java/KEY_QUEUE.md) | Target queue           |
| [KeyPressed](../../../KeyPressed.java/KeyPressed.md)                            | Record type enqueued   |
| [LockAlias](../../../alias/builtinAlias/LockAlias.java/LockAlias.md)            | Lock check             |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
