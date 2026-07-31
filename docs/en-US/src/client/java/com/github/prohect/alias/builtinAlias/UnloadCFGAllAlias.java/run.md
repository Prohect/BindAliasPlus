# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAllAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                           |
| ------ | -------- | ----------------------------------------------------- |
| `args` | `String` | Ignored — passed through to sub-aliases but not used. |

## Remarks

Removes all autoloaded aliases, keybindings, and variables in one call. Runtime-created items are not affected.

**Algorithm**:

1. Save and set `BindAliasClient.silentMode = true` to suppress per-operation log messages.
2. Count current aliases: instantiate `UnloadCFGAliasesAlias`, call `run(args)`, then count removed UserAliases by comparing pre/post counts.
3. Count current bindings: instantiate `UnloadCFGBindsAlias`, call `run(args)`, count removed bindings similarly.
4. Count current variables: read `VarAlias.AUTOLOADED_VARIABLES.size()`, then call `UnloadCFGVarsAlias.run(args)`.
5. Restore `silentMode` to original value.
6. If not originally in silent mode, log a summary with counts.

**Side effects**: Removes entries from `Alias.aliasesWithoutArgs`, `BindAliasClient.BINDING_PLUS`, `Alias.aliasesWithoutArgs_fromBindCommand`, `VarAlias.VARIABLES`, and `VarAlias.AUTOLOADED_VARIABLES`.

**Callers**: Invoked by the alias dispatch system.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
