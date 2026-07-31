# UnloadCFGBindsAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGBindsAlias.java)

One-shot alias that removes all keybindings loaded from the configuration file (CFG). Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnloadCFGBindsAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadCFGBindsAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `unloadCFGBinds` (internal, exposed as `unloadCFGBinds`).

**Behavior:** Iterates through `BindAliasClient.BINDING_PLUS`, removing every binding whose `fromCFG()` returns true. Also cleans up the associated aliases from `Alias.aliasesWithoutArgs_fromBindCommand` — any alias names stored in the binding's `aliasNameOnKeyPressed()` and `aliasNameOnKeyReleased()` are removed from the bind command registry.

**What is NOT affected:**
- Runtime-created bindings (via `/bind` or `/bindByAliasName` commands) — these have `fromCFG() == false`.
- Aliases in `aliasesWithoutArgs` (use `unloadCFGAliases`).
- Variables (use `unloadCFGVars`).

**Silent mode:** When `BindAliasClient.silentMode` is false, logs `"[unloadCFGBinds] Removed {count} autoloaded keybinding(s)"` (without tick prefix). Silent mode suppresses this log.

**Cleanup logic:** The `aliasesWithoutArgs_fromBindCommand` map stores aliases that were auto-created when binding keys. When CFG bindings are removed, their associated auto-created aliases should be cleaned up too to avoid orphaned entries.

## See Also

| Item | Description |
|------|-------------|
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md) | Remove all CFG-loaded items |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md) | Remove CFG-loaded aliases |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md) | Remove CFG-loaded variables |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/UnloadUserBindsAlias.md) | Remove runtime-created bindings (opposite) |
| [UnbindAlias](../UnbindAlias.java/UnbindAlias.md) | Server-command based unbind |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
