# UnloadUserVarsAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserVarsAlias.java)

One-shot alias that removes all variables created at runtime (not from CFG), including both general variables and container slot variables. Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnloadUserVarsAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadUserVarsAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `unloadUserVars` (internal, exposed as `unloadUserVars`).

**Behavior:** Removes all runtime-created variables from both variable maps:
1. `GENERAL_VARIABLES` — removes entries whose name is NOT in `CFG_VARIABLES`.
2. `CONTAINER_SLOT_VARIABLES` — removes entries whose name is NOT in `CFG_CONTAINER_SLOT_VARIABLES`.

**What is NOT affected:** Variables loaded from CFG (those tracked in `CFG_VARIABLES` and `CFG_CONTAINER_SLOT_VARIABLES`).

**Silent mode:** When `BindAliasClient.silentMode` is false, logs a detailed message: `"Removed {total} runtime variable(s) ({generalCount} general, {containerCount} container_slot)"`.

**Key difference from `unloadCFGVars`:** This alias cleans BOTH general variables AND container slot variables. `UnloadCFGVarsAlias` only cleans general variables (it does NOT access `CONTAINER_SLOT_VARIABLES` or `CFG_CONTAINER_SLOT_VARIABLES`).

**Use case:** Clean up temporary variables created during gameplay/automation while preserving permanently configured variables from the CFG.

## See Also

| Item | Description |
|------|-------------|
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/UnloadUserAllAlias.md) | Remove all runtime-created items |
| [UnloadUserAliasesAlias](../UnloadUserAliasesAlias.java/UnloadUserAliasesAlias.md) | Remove runtime-created aliases |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/UnloadUserBindsAlias.md) | Remove runtime-created keybindings |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md) | Remove CFG-loaded variables (opposite, general only) |
| [VarAlias](../VarAlias.java/VarAlias.md) | Variable system |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
