# KeyboardInputMixin (src/client/java/com/github/prohect/mixin/client/KeyboardInputMixin.java)

## Syntax

```java
public class com.github.prohect.mixin.client.KeyboardInputMixin
```

## Static Initializer

_None._

## Remarks

Mixin that injects into `net.minecraft.client.player.KeyboardInput.tick()` to process the mod's key-binding queue.

**Injection point**: `@Inject(at = @At("HEAD"), method = "tick")` — fires at the start of every keyboard input tick, before vanilla input processing.

**Purpose**: polls `BindAliasClient.KEY_QUEUE` (populated by `KeyBoardMixin` and `MouseMixin`) and dispatches matching `AliasWithoutArgs` instances. This is the bridge between raw key/mouse events and alias execution.

The tick method is static — it does not need access to the `KeyboardInput` instance, only the global state maps.

**Dispatch logic**: for each `KeyPressed` in the queue:

1. Look up the bound `KeyBindingPlus` from `BINDING_PLUS`.
2. Depending on press/release, look up the alias name from `aliasNameOnKeyPressed()` or `aliasNameOnKeyReleased()`.
3. Resolve the alias from `Alias.aliasesWithoutArgs` (or fallback to `aliasesWithoutArgs_fromBindCommand`).
4. Call `aliasWithoutArgs.run("")` to execute the alias.

## See Also

| Item                                                                                  | Description                                   |
| ------------------------------------------------------------------------------------- | --------------------------------------------- |
| [KeyBoardMixin](../KeyBoardMixin.java/KeyBoardMixin.md)                               | Enqueues keyboard events into `KEY_QUEUE`     |
| [MouseMixin](../MouseMixin.java/MouseMixin.md)                                        | Enqueues mouse button events into `KEY_QUEUE` |
| [BindAliasClient.KEY_QUEUE](../../../BindAliasClient.java/KEY_QUEUE.md)       | The queue consumed here                       |
| [BindAliasClient.BINDING_PLUS](../../../BindAliasClient.java/BINDING_PLUS.md) | Key-to-alias binding map                      |
| [Alias.aliasesWithoutArgs](../../../alias/Alias.java/aliasesWithoutArgs.md)           | Alias registry for dispatch                   |
| [tick](tick.md)                                                                       | The injected callback method                  |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
