# WaitAlias (src/client/java/com/github/prohect/alias/builtinAlias/WaitAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.WaitAlias extends com.github.prohect.alias.BuiltinAliasWithIntegerArgs<com.github.prohect.alias.builtinAlias.WaitAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to delay execution of subsequent alias actions. Registered as `wait`.

**Purpose**: Inserts a pause (in game ticks) into an alias sequence. When the UserAlias dispatch encounters a `wait` command, it creates a `WaitAliasRecord` and adds it to the static `tasksWaiting` list. The tick handler then counts down and executes the deferred alias definition when the timer reaches zero.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup. The static `tasksWaiting` list holds all pending wait records across all alias instances.

**Thread safety**: Not thread-safe (render-thread only). The `tasksWaiting` list is mutated on the render thread.

**Key collaborators**: Creates `[WaitAliasRecord](WaitAliasRecord.java/WaitAliasRecord.md)` instances. Consumed by the tick handler (likely in `MinecraftClientMixin` or similar) that calls `WaitAliasRecord.tick()` each game tick. The deprecated `run(String)` overload is kept for backward compatibility with existing UserAlias definitions that use simple `wait\N` syntax.

## See Also

| Item                                                                                                 | Description                                |
| ---------------------------------------------------------------------------------------------------- | ------------------------------------------ |
| [WaitAliasRecord](../WaitAliasRecord.java/WaitAliasRecord.md)                                        | Deferred execution record                  |
| [BuiltinAliasWithIntegerArgs](../../BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | Parent class providing `flag` (tick count) |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
