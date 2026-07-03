# LogAlias (src/client/java/com/github/prohect/alias/builtinAlias/LogAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.LogAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.LogAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that logs a message to the mod's logger (`BindAliasPlusClient.LOGGER`).

Lifecycle: singleton — instantiated once during `onInitializeClient()`, registered via `putToAliasesWithArgs()` into `Alias.aliasesWithArgs`, and reused for every invocation.

Thread safety: not thread-safe; runs on the render thread.

Key collaborators: extends [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) directly (not the boolean/double/greedy subclasses), meaning it receives the raw args string as-is. The entire args string is logged at INFO level via `BindAliasPlusClient.LOGGER`.

Useful for debugging alias chains and verifying variable values at runtime.

## See Also

| Item                                                                            | Description                              |
| ------------------------------------------------------------------------------- | ---------------------------------------- |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class — takes raw string args     |
| [LocalSayAlias](../LocalSayAlias.java/LocalSayAlias.md)                         | Displays messages in chat instead of log |
| [BindAliasPlusClient](../../../BindAliasPlusClient.java/BindAliasPlusClient.md) | Owns the LOGGER used here                |
| [run](run.md)                                                                   | The `run` method that writes to the log  |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
