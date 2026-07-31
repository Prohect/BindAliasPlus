# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserAllAlias.java)

Removes all runtime-created aliases, keybindings, and variables, logging a single summary.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.UnloadUserAllAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. Save current `silentMode` and set it to `true`.
2. **Unload runtime aliases:** Instantiate and run `UnloadUserAliasesAlias`. Count removed aliases by filtering the registry for `!isFromCFG() && !isPredefined()`.
3. **Unload runtime bindings:** Instantiate and run `UnloadUserBindsAlias`. Count removed bindings by filtering `BINDING_PLUS` for `!fromCFG()`.
4. **Unload runtime variables:** Count user variables (those not in `CFG_VARIABLES` and not in `CFG_CONTAINER_SLOT_VARIABLES`), then instantiate and run `UnloadUserVarsAlias`. The total includes both general and container slot variables.
5. Restore original `silentMode`.
6. If not originally in silent mode, log summary.

**Return value:** `this` (fluent return).

**Side effects:** Removes all runtime-created items from their respective registries. CFG-loaded and builtin items are preserved.

**Variable counting:** The pre-unload count combines:
- General variables in `GENERAL_VARIABLES` not tracked in `CFG_VARIABLES`.
- Container slot variables in `CONTAINER_SLOT_VARIABLES` not tracked in `CFG_CONTAINER_SLOT_VARIABLES`.

## See Also

| Item | Description |
|------|-------------|
| [UnloadUserAllAlias](UnloadUserAllAlias.md) | Class overview |
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/run.md) | Remove CFG-loaded items (opposite) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
