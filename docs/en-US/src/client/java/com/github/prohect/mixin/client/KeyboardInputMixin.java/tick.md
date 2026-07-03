# tick method (src/client/java/com/github/prohect/mixin/client/KeyboardInputMixin.java)

## Syntax

```java
private static void tick(org.spongepowered.asm.mixin.injection.callback.CallbackInfo)
```

## Parameters

| Name   | Type           | Description                   |
| ------ | -------------- | ----------------------------- |
| `info` | `CallbackInfo` | Mixin callback info (unused). |

## Remarks

Processes all pending key/mouse events from `BindAliasPlusClient.KEY_QUEUE` and dispatches matching `AliasWithoutArgs` instances.

Algorithm (per queued `KeyPressed`):

1. Poll `BindAliasPlusClient.KEY_QUEUE.poll()` in a loop until the queue is empty.
2. Look up the `KeyBindingPlus` from `BINDING_PLUS` by the `InputConstants.Key` code.
3. If no binding exists for this key, skip it.
4. Determine the alias name: `aliasNameOnKeyPressed()` if the key was pressed, `aliasNameOnKeyReleased()` if released.
5. Look up the alias from `Alias.aliasesWithoutArgs`. If not found, fall back to `Alias.aliasesWithoutArgs_fromBindCommand`.
6. If an alias is found, call `aliasWithoutArgs.run("")` to execute it.

Callers: called by the Mixin framework at the start of every `KeyboardInput.tick()`. This ensures key events are dispatched before vanilla input processing for the current tick.

Side effects: drains the `KEY_QUEUE` and executes bound aliases. Can trigger any side effect the aliases produce (key simulation, chat messages, commands, etc.).

## See Also

| Item                                                                                  | Description              |
| ------------------------------------------------------------------------------------- | ------------------------ |
| [KeyboardInputMixin](KeyboardInputMixin.md)                                           | Owning mixin class       |
| [KeyBoardMixin.onKey](../KeyBoardMixin.java/onKey.md)                                 | Enqueues keyboard events |
| [MouseMixin.onMouseButton](../MouseMixin.java/onMouseButton.md)                       | Enqueues mouse events    |
| [BindAliasPlusClient.KEY_QUEUE](../../../BindAliasPlusClient.java/KEY_QUEUE.md)       | Queue consumed here      |
| [BindAliasPlusClient.BINDING_PLUS](../../../BindAliasPlusClient.java/BINDING_PLUS.md) | Key-to-binding map       |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
