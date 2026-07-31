# UnloadCFGAliasesAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAliasesAlias.java)

One-shot alias that removes all user aliases loaded from the configuration file (CFG). Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnloadCFGAliasesAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadCFGAliasesAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `unloadCFGAliases` (internal, exposed as `unloadCFGAliases`).

**Behavior:** Iterates through `Alias.aliasesWithoutArgs`, removing every `UserAlias` whose `isFromCFG()` returns true. CFG-loaded aliases are those loaded during `loadCFG()` processing from the configuration file.

**What is NOT affected:**
- Runtime-created aliases (via the `alias` builtin or `/alias` command) — these have `isFromCFG() == false`.
- Predefined/builtin aliases — these are not `UserAlias` instances.
- Keybindings (use `unloadCFGBinds`) or variables (use `unloadCFGVars`).

**Silent mode:** When `BindAliasClient.silentMode` is false (normal), logs an info message with the count of removed aliases. When silent mode is active, no feedback is logged.

**Logging format:** `"[unloadCFGAliases] Removed {count} autoloaded alias(es)"` (without tick prefix).

**Use case:** Used before re-loading CFG to ensure a clean state, or to temporarily remove CFG-defined behavior without affecting runtime-created aliases.

## See Also

| Item | Description |
|------|-------------|
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md) | Remove all CFG-loaded items (aliases + binds + vars) |
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | Remove CFG-loaded keybindings |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md) | Remove CFG-loaded variables |
| [UnloadUserAliasesAlias](../UnloadUserAliasesAlias.java/UnloadUserAliasesAlias.md) | Remove runtime-created aliases (opposite) |
| [ReloadCFGAlias](../ReloadCFGAlias.java/ReloadCFGAlias.md) | Reload CFG after unloading |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
