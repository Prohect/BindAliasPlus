# MouseMixin (src/client/java/com/github/prohect/mixin/client/MouseMixin.java)

## Syntax

```java
public class com.github.prohect.mixin.client.MouseMixin
```

## Static Initializer

_None._

## Remarks

Mixin that injects into `net.minecraft.client.MouseHandler` for two purposes:

1. **Mouse button event interception** (`onButton`): enqueues mouse button events into `KEY_QUEUE` for alias dispatch, following the same pattern as `KeyBoardMixin`.

2. **Cursor grab reapply** (`grabMouse`): when a screen is closed and the game returns to 3D rendering, Minecraft releases and re-grabs the cursor. During this process, it checks GLFW key states and re-presses held keys from `gameOptions`. This mixin re-applies any `BuiltinAliasWithBooleanArgs` aliases whose `flag` is `true` to ensure the game's `KeyMapping` states are consistent with the mod's alias states.

**Injection points**:

- `onMouseButton`: `@Inject(at = @At("HEAD"), method = "onButton")` — intercepts mouse button events before vanilla handling.
- `lockCursor`: `@Inject(at = @At("RETURN"), method = "grabMouse")` — fires after cursor grab completes, re-applying held key states.

## See Also

| Item                                                                                                                              | Description                               |
| --------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------- |
| [KeyBoardMixin](../KeyBoardMixin.java/KeyBoardMixin.md)                                                                           | Same pattern for keyboard events          |
| [KeyboardInputMixin](../KeyboardInputMixin.java/KeyboardInputMixin.md)                                                            | Consumes the queue                        |
| [BindAliasClient.KEY_QUEUE](../../../BindAliasClient.java/KEY_QUEUE.md)                                                   | Queue for mouse events                    |
| [BuiltinAliasWithBooleanArgs.reapplyToGameKeyMapping](../../../alias/BuiltinAliasWithBooleanArgs.java/reapplyToGameKeyMapping.md) | Re-applies held key states                |
| [LockAlias](../../../alias/builtinAlias/LockAlias.java/LockAlias.md)                                                              | Lock mechanism checked in `onMouseButton` |
| [onMouseButton](onMouseButton.md)                                                                                                 | Injected mouse button handler             |
| [lockCursor](lockCursor.md)                                                                                                       | Injected cursor grab handler              |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
