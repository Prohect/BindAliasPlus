# UnloadCFGAllAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAllAlias.java)

One-shot alias that removes all aliases, keybindings, and variables loaded from the configuration file (CFG). Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnloadCFGAllAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadCFGAllAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `unloadCFGAll` (internal, exposed as `unloadCFGAll`).

**Behavior:** A convenience alias that calls all three unload operations in sequence:
1. `UnloadCFGAliasesAlias` — removes CFG-loaded user aliases
2. `UnloadCFGBindsAlias` — removes CFG-loaded keybindings
3. `UnloadCFGVarsAlias` — removes CFG-loaded variables

**Silent mode handling:** Each sub-operation normally logs its own message. To avoid spam, `UnloadCFGAllAlias` temporarily enables silent mode during the sub-operations, then logs a single summary message with all three counts: `"Removed {N} alias(es), {M} keybinding(s), {K} variable(s)"`.

**Count tracking:** Since sub-operations run in silent mode and don't return counts, `UnloadCFGAllAlias` counts items before/after each operation. This counting uses stream-based filtering on the relevant registries.

**What is NOT affected:** Runtime-created items (via commands during gameplay) and builtin items.

**Use case:** Typically called before `reloadCFG` to fully reset CFG-loaded state, or when temporarily disabling all CFG behavior.

## See Also

| Item | Description |
|------|-------------|
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md) | Remove CFG-loaded aliases only |
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | Remove CFG-loaded keybindings only |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md) | Remove CFG-loaded variables only |
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/UnloadUserAllAlias.md) | Remove runtime-created items (opposite) |
| [ReloadCFGAlias](../ReloadCFGAlias.java/ReloadCFGAlias.md) | Reload CFG after unloading |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
