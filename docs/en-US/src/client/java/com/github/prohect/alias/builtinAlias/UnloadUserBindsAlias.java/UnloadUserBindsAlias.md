# UnloadUserBindsAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserBindsAlias.java)

One-shot alias that removes all keybindings created at runtime (not from CFG). Extends `BuiltinAliasWithoutArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnloadUserBindsAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadUserBindsAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `unloadUserBinds` (internal, exposed as `unloadUserBinds`).

**Behavior:** Iterates through `BindAliasClient.BINDING_PLUS`, removing every binding where `fromCFG() == false` (i.e., created at runtime via `/bind` or `/bindByAliasName` commands). Also cleans up associated aliases from `Alias.aliasesWithoutArgs_fromBindCommand`.

**What is NOT affected:**
- CFG-loaded bindings (`fromCFG() == true`).
- Aliases in `aliasesWithoutArgs` (use `unloadUserAliases`).
- Variables (use `unloadUserVars`).

**Silent mode:** When `BindAliasClient.silentMode` is false, logs `"[unloadUserBinds] Removed {count} runtime keybinding(s)"` (without tick prefix).

**Cleanup:** Associated auto-created aliases from bind commands are also removed from `aliasesWithoutArgs_fromBindCommand`.

## See Also

| Item | Description |
|------|-------------|
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/UnloadUserAllAlias.md) | Remove all runtime-created items |
| [UnloadUserAliasesAlias](../UnloadUserAliasesAlias.java/UnloadUserAliasesAlias.md) | Remove runtime-created aliases |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/UnloadUserVarsAlias.md) | Remove runtime-created variables |
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | Remove CFG-loaded bindings (opposite) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
