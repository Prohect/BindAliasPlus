# UnloadCFGVarsAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGVarsAlias.java)

One-shot alias that removes all variables loaded from the configuration file (CFG). Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnloadCFGVarsAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadCFGVarsAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `unloadCFGVars` (internal, exposed as `unloadCFGVars`).

**Behavior:** Iterates through `VarAlias.CFG_VARIABLES` (the set tracking which variables were loaded from CFG), removing each variable name from both `VarAlias.GENERAL_VARIABLES` and `VarAlias.CFG_VARIABLES`.

**What is NOT affected:**
- Runtime-created variables (via the `var` alias at runtime) — these are not in `CFG_VARIABLES`.
- Container slot variables in `CONTAINER_SLOT_VARIABLES` — these are NOT cleaned up by this alias. The `CFG_CONTAINER_SLOT_VARIABLES` set is referenced by `UnloadUserVarsAlias` for cleanup.

**Note on CONTAINER_SLOT_VARIABLES:** This alias ONLY cleans general variables. Container slot variables are not tracked in `CFG_VARIABLES` — they use `CFG_CONTAINER_SLOT_VARIABLES` instead, which is cleaned by `UnloadUserVarsAlias` or `UnloadCFGAllAlias` indirectly.

**Silent mode:** When `BindAliasClient.silentMode` is false, logs an info message with the count.

**Use case:** Remove CFG-defined variables before reloading CFG.

## See Also

| Item | Description |
|------|-------------|
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md) | Remove all CFG-loaded items |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md) | Remove CFG-loaded aliases |
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | Remove CFG-loaded keybindings |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/UnloadUserVarsAlias.md) | Remove runtime-created variables (opposite) |
| [VarAlias](../VarAlias.java/VarAlias.md) | Variable system |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
