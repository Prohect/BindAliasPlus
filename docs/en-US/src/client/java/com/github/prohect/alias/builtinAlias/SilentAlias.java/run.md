# run method (src/client/java/com/github/prohect/alias/builtinAlias/SilentAlias.java)

Toggles the global silent mode flag on `BindAliasClient`.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.SilentAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | `"1"` to enable silent mode, `"0"` to disable |

## Remarks

**Algorithm:**

1. Parse `args` via `parseArgs(args)` — sets `this.flag` (true for "1", false for "0").
2. Set `BindAliasClient.silentMode = flag`.

**Return value:** `this` (fluent return).

**Side effects:** Sets the global `silentMode` flag. When true, most builtin alias feedback messages (at INFO and WARN levels) are suppressed in chat. Error-level messages and the `log` alias are typically not suppressed.

**No screen suppression:** Works on any screen, including text-input screens. This is explicitly not a game operation.

**Behavior check:** Many builtin aliases check `BindAliasClient.silentMode` before logging feedback messages — for example, `UnloadCFGAliasesAlias`, `UnloadCFGVarsAlias`, etc.

## See Also

| Item | Description |
|------|-------------|
| [SilentAlias](SilentAlias.md) | Class overview |
| [LogAlias](../LogAlias.java/run.md) | Write to mod log (not suppressed) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
