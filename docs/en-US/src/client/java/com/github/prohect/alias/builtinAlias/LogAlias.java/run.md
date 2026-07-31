# run method (src/client/java/com/github/prohect/alias/builtinAlias/LogAlias.java)

Writes a message to the mod log at INFO level with a tick-number prefix.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.LogAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | The text to log |

## Remarks

1. Calls `BindAliasClient.LOGGER.info("{}{}", BindAliasClient.tickPrefix(), args)` to write the message to the mod log.

The output format is: `[tickPrefix]message` where `tickPrefix` is the current client tick number (e.g., `[t1234]`). This helps correlate log entries with game events when debugging alias scripts.

No null checks are needed — the logger handles null args gracefully.

## See Also

| Item | Description |
|------|-------------|
| [LocalSayAlias.run()](../LocalSayAlias.java/run.md) | Displays message in local chat |
| [BindAliasClient.LOGGER](../../../BindAliasClient.java/LOGGER.md) | The mod logger instance |
| [BindAliasClient.tickPrefix()](../../../BindAliasClient.java/tickPrefix.md) | Returns current tick prefix |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
