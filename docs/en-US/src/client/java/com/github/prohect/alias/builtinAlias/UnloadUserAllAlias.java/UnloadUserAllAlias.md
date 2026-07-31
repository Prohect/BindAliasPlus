# UnloadUserAllAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserAllAlias.java)

One-shot alias that removes all aliases, keybindings, and variables created at runtime (not from CFG). Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnloadUserAllAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadUserAllAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `unloadUserAll` (internal, exposed as `unloadUserAll`).

**Behavior:** A convenience alias that calls all three runtime cleanup operations in sequence:
1. `UnloadUserAliasesAlias` — removes runtime-created user aliases
2. `UnloadUserBindsAlias` — removes runtime-created keybindings
3. `UnloadUserVarsAlias` — removes runtime-created variables (both general and container slot)

**Silent mode handling:** Like `UnloadCFGAllAlias`, this temporarily enables silent mode during sub-operations and logs a single summary: `"Removed {N} alias(es), {M} keybinding(s), {K} variable(s)"`.

**What is NOT affected:** CFG-loaded items and builtin items.

**Variable cleanup includes:** Both `GENERAL_VARIABLES` (general variables) and `CONTAINER_SLOT_VARIABLES` (container slot references). The count includes both types.

**Use case:** Reset all temporary/runtime state without affecting the permanent CFG configuration. Useful at the end of a test session or before re-running a sequence of tests.

## See Also

| Item | Description |
|------|-------------|
| [UnloadUserAliasesAlias](../UnloadUserAliasesAlias.java/UnloadUserAliasesAlias.md) | Remove runtime aliases only |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/UnloadUserBindsAlias.md) | Remove runtime keybindings only |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/UnloadUserVarsAlias.md) | Remove runtime variables only |
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md) | Remove CFG-loaded items (opposite) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
