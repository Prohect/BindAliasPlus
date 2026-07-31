# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAllAlias.java)

Removes all CFG-loaded aliases, keybindings, and variables, logging a single summary.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.UnloadCFGAllAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. Save current `silentMode` and set it to `true` (suppress sub-operation logs).
2. Instantiate and run `UnloadCFGAliasesAlias` — count removed aliases via stream filtering.
3. Instantiate and run `UnloadCFGBindsAlias` — count removed bindings via stream filtering.
4. Read `VarAlias.CFG_VARIABLES.size()` as the pre-count, then instantiate and run `UnloadCFGVarsAlias`.
5. Restore original `silentMode`.
6. If not originally in silent mode, log summary: `"Removed {N} alias(es), {M} keybinding(s), {K} variable(s)"`.

**Return value:** `this` (fluent return).

**Side effects:** Removes all CFG-loaded aliases from `aliasesWithoutArgs`, all CFG-loaded bindings from `BINDING_PLUS`, and all CFG-loaded variables from `GENERAL_VARIABLES` and `CONTAINER_SLOT_VARIABLES`.

**Count accuracy:** The alias/bindings counts are derived by comparing registry state before and after each sub-operation (since they run in silent mode and don't report counts). The variables count uses the pre-operation size of `CFG_VARIABLES`.

## See Also

| Item | Description |
|------|-------------|
| [UnloadCFGAllAlias](UnloadCFGAllAlias.md) | Class overview |
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/run.md) | Remove runtime items (opposite) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
