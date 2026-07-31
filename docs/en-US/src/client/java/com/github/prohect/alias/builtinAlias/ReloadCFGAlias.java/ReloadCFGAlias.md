# ReloadCFGAlias (src/client/java/com/github/prohect/alias/builtinAlias/ReloadCFGAlias.java)

One-shot alias that reloads the configuration file (CFG) at runtime. Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.ReloadCFGAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.ReloadCFGAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `reloadCFG` (internal, exposed as `reloadCFG`).

**Behavior:** Calls `BindAliasClient.INSTANCE.loadCFG()` which re-reads and processes the configuration file. This loads aliases, keybindings, and variables defined in the CFG into the running game without requiring a restart.

**Use case:** Allows live editing of the config file — make changes, then run `reloadCFG` to apply them in-game. Also used by the MCP server's `writeCFG` tool which writes new CFG content and reloads it.

**No screen suppression:** This alias works even when a text-input screen or any other screen is open — it's a configuration operation, not a game input.

**Relationship with unload aliases:** To fully reset CFG-loaded state before reloading, use `unloadCFGAll` followed by `reloadCFG`. The `writeCFG` MCP tool automatically unloads before reloading.

## See Also

| Item | Description |
|------|-------------|
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md) | Remove all CFG-loaded items |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md) | Remove only CFG-loaded aliases |
| [BindAliasClient](../../../BindAliasClient.java/BindAliasClient.md) | Client entry point providing `loadCFG()` |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
