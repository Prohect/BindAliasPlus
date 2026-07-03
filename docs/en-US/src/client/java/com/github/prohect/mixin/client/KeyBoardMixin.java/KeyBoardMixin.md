# KeyBoardMixin (src/client/java/com/github/prohect/mixin/client/KeyBoardMixin.java)

## Syntax

```java
public class com.github.prohect.mixin.client.KeyBoardMixin
```

## Static Initializer

_None._

## Remarks

Mixin that injects into `net.minecraft.client.KeyboardHandler.keyPress()` to enqueue keyboard events for the mod's alias dispatch system.

**Injection point**: `@Inject(at = @At("HEAD"), method = "keyPress")` — fires at the start of key press handling, before vanilla processing.

**Purpose**: intercepts raw key events from GLFW, filters for mod-bound keys (those in `BINDING_PLUS`), and enqueues `KeyPressed` records into `BindAliasPlusClient.KEY_QUEUE`. The queue is later processed by `KeyboardInputMixin.tick()`.

**Guards**:

- Ignores events from windows other than the main game window (checks `window` handle).
- Skips keys whose action is currently locked by `LockAlias`.
- Only enqueues on action `0` (release) and `1` (press); ignores action `2` (repeat).

## See Also

| Item                                                                                  | Description                                       |
| ------------------------------------------------------------------------------------- | ------------------------------------------------- |
| [KeyboardInputMixin](../KeyboardInputMixin.java/KeyboardInputMixin.md)                | Consumes the queue this mixin fills               |
| [MouseMixin](../MouseMixin.java/MouseMixin.md)                                        | Same pattern for mouse button events              |
| [BindAliasPlusClient.KEY_QUEUE](../../../BindAliasPlusClient.java/KEY_QUEUE.md)       | The queue where `KeyPressed` records are enqueued |
| [BindAliasPlusClient.BINDING_PLUS](../../../BindAliasPlusClient.java/BINDING_PLUS.md) | Map checked for mod-bound keys                    |
| [LockAlias](../../../alias/builtinAlias/LockAlias.java/LockAlias.md)                  | Lock mechanism that blocks certain keys           |
| [KeyPressed](../../../KeyPressed.java/KeyPressed.md)                                  | The record enqueued for each key event            |
| [onKey](onKey.md)                                                                     | The injected callback method                      |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
