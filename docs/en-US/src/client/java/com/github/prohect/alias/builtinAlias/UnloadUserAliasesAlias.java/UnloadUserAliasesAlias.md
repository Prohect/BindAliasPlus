# UnloadUserAliasesAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserAliasesAlias.java)

One-shot alias that removes all user aliases created at runtime (not from CFG and not predefined). Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnloadUserAliasesAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadUserAliasesAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `unloadUserAliases` (internal, exposed as `unloadUserAliases`).

**Behavior:** Iterates through `Alias.aliasesWithoutArgs`, removing every `UserAlias` where `isFromCFG() == false` AND `isPredefined() == false`. This means only truly runtime-created aliases are removed — CFG-loaded and builtin predefined aliases are preserved.

**What is NOT affected:**
- CFG-loaded aliases (`isFromCFG() == true`).
- Predefined aliases (`isPredefined() == true` — these are builtin aliases that are exposed as user-facing aliases).
- Keybindings (use `unloadUserBinds`) or variables (use `unloadUserVars`).

**Silent mode:** When `BindAliasClient.silentMode` is false, logs `"Removed {count} runtime alias(es)"`.

**Use case:** Clean up aliases created during a test session without affecting permanently configured aliases from the CFG.

## See Also

| Item | Description |
|------|-------------|
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/UnloadUserAllAlias.md) | Remove all runtime-created items |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/UnloadUserBindsAlias.md) | Remove runtime-created keybindings |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/UnloadUserVarsAlias.md) | Remove runtime-created variables |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md) | Remove CFG-loaded aliases (opposite) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
