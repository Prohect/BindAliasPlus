# MinecraftClientMixin (src/client/java/com/github/prohect/mixin/client/MinecraftClientMixin.java)

## Syntax

```java
public class com.github.prohect.mixin.client.MinecraftClientMixin
```

## Static Initializer

_None._

## Remarks

Mixin that injects into `net.minecraft.client.Minecraft.tick()` to drive per-tick background tasks.

**Injection point**: `@Inject(at = @At("HEAD"), method = "tick")` — fires at the start of every game client tick.

**Purpose**: performs two tick-based tasks:

1. **WaitAlias processing**: drains `WaitAlias.tasksWaiting` — a list of deferred alias executions. Each `WaitAliasRecord` has a countdown; `tick()` decrements it each frame. When countdown reaches zero, the queued aliases are dispatched and the record is removed.

2. **DropAlias continuous drop**: drives continuous item dropping while the drop key is held. Looks up the `DropAlias` singleton from `Alias.aliasesWithArgs_notSuggested` by the name `"builtinDrop"` and calls `tickDrop()`, which handles both container-screen slot-clicking and 3D-world drop via `clickCount`.

## See Also

| Item                                                                                            | Description                        |
| ----------------------------------------------------------------------------------------------- | ---------------------------------- |
| [WaitAlias](../../../alias/builtinAlias/WaitAlias.java/WaitAlias.md)                            | Deferred task list consumed here   |
| [DropAlias](../../../alias/builtinAlias/DropAlias.java/DropAlias.md)                            | Continuous drop alias driven here  |
| [Alias.aliasesWithArgs_notSuggested](../../../alias/Alias.java/aliasesWithArgs_notSuggested.md) | Registry where DropAlias is stored |
| [KeyboardInputMixin](../KeyboardInputMixin.java/KeyboardInputMixin.md)                          | Key dispatch on the same tick      |
| [tick](tick.md)                                                                                 | The injected callback method       |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
