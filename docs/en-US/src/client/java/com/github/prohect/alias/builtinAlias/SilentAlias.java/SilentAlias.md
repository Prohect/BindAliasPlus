# SilentAlias (src/client/java/com/github/prohect/alias/builtinAlias/SilentAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SilentAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.SilentAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that toggles silent mode, suppressing feedback messages (e.g., success confirmations) in chat.

Lifecycle: singleton — instantiated once during `onInitializeClient()`, registered via `putToAliasesWithArgs()` into `Alias.aliasesWithArgs`, and reused for every invocation.

Thread safety: not thread-safe; runs on the render thread. Sets `BindAliasPlusClient.silentMode` which is read by other aliases during dispatch.

Key collaborators: extends [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) which provides `parseArgs()` for `"0"`/`"1"` parsing and `flag` field. Writes to `BindAliasPlusClient.silentMode`. Unlike movement aliases, does not gate on `Alias.isUnderTextInputScreen()` because this is not a game operation.

Shortcuts: `+silent` enables silent mode, `-silent` disables it (resolved by the config parser).

## See Also

| Item                                                                                                 | Description                                       |
| ---------------------------------------------------------------------------------------------------- | ------------------------------------------------- |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Parent class providing boolean arg parsing        |
| [BindAliasPlusClient](../../../BindAliasPlusClient.java/BindAliasPlusClient.md)                      | Owns the `silentMode` field                       |
| [UserAlias](../../UserAlias.java/UserAlias.md)                                                       | Checks `silentMode` to suppress feedback messages |
| [run](run.md)                                                                                        | The `run` method that sets silent mode            |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
