# run method (src/client/java/com/github/prohect/alias/builtinAlias/ReloadCFGAlias.java)

Triggers a reload of the configuration file by calling `BindAliasClient.INSTANCE.loadCFG()`.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.ReloadCFGAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. Call `BindAliasClient.INSTANCE.loadCFG()` — re-reads the configuration file and processes all aliases, keybindings, and variables defined in it.

**Side effects:**
- New aliases, keybindings, and variables from the CFG are registered. Existing ones with the same name are overwritten.
- CFG-defined items already in memory that are no longer in the CFG are NOT removed — use `unloadCFGAll` before reloading if a full reset is needed.

**Return value:** `this` (fluent return).

**No screen suppression:** Works on any screen.

## See Also

| Item | Description |
|------|-------------|
| [ReloadCFGAlias](ReloadCFGAlias.md) | Class overview |
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/run.md) | Full CFG unload (use before reload) |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
