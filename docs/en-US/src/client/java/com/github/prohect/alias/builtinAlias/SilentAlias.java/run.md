# run method (src/client/java/com/github/prohect/alias/builtinAlias/SilentAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                |
| ------ | -------- | -------------------------------------------------------------------------- |
| `args` | `String` | `"0"` to disable silent mode, `"1"` to enable it. Parsed by `parseArgs()`. |

## Remarks

Sets `BindAliasPlusClient.silentMode` to the boolean `flag` value parsed from `args`.

Algorithm:

1. Calls `parseArgs(args)` to set the inherited `flag` field (`true` for `"1"`, `false` for `"0"`).
2. Assigns `flag` to `BindAliasPlusClient.silentMode`.

Unlike movement aliases, this does not gate on `Alias.isUnderTextInputScreen()` because toggling silent mode is not a game input operation.

Side effects: modifies `BindAliasPlusClient.silentMode`, a static boolean that controls whether feedback messages are suppressed during alias execution.

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"builtinSilent"` matches an `AliasRecord`. Also invoked via `+silent`/`-silent` shortcuts.

Error handling: `parseArgs()` logs a warning for invalid args (not `"0"` or `"1"`) via `BindAliasPlusClient.LOGGER`.

## See Also

| Item                                                                                         | Description                    |
| -------------------------------------------------------------------------------------------- | ------------------------------ |
| [SilentAlias](SilentAlias.md)                                                                | Owning class                   |
| [BuiltinAliasWithBooleanArgs.parseArgs](../../BuiltinAliasWithBooleanArgs.java/parseArgs.md) | Parses `"0"`/`"1"` into `flag` |
| [BindAliasPlusClient.silentMode](../../../BindAliasPlusClient.java/silentMode.md)            | The field this method modifies |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
