# ReapplyAlias (src/client/java/com/github/prohect/alias/builtinAlias/ReapplyAlias.java)

Builtin alias that manually re-asserts a single held-down switch alias (BooleanArgs) after a screen transition. Extends `BuiltinAliasWithArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.ReapplyAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.ReapplyAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `reapply` — usage: `reapply\action`.

**Purpose:** Vanilla Minecraft releases all held keys when a new screen opens (`setScreen` event). This alias re-asserts a single held key by calling `reapplyToGameKeyMapping()` on the corresponding builtin alias, but only if its `flag` is currently true (i.e., the key was being held before the screen transition).

**Supported actions:** Defined in `SUPPORTED_ACTIONS`:
- `attack`, `use`, `forward`, `back`, `left`, `right`, `jump`, `sneak`, `sprint`, `drop`, `openInventory`, `playerList`

**How it resolves:** Takes the action name, strips any `+`/`-` prefix, then derives the internal builtin name by prepending `"builtin"` and capitalizing the first letter (e.g., `forward` → `builtinForward`). Looks up this name in both `aliasesWithArgs` and `aliasesWithArgs_notSuggested` registries. If found and it's a `BuiltinAliasWithBooleanArgs` with `flag == true`, calls `reapplyToGameKeyMapping()`.

**Error handling:** Logs a warning if no action name is provided, or if the resolved builtin alias is not found or not currently held.

**Typical usage:** Call `reapply\forward` at the end of a UserAlias sequence after a screen transition to re-assert held movement keys.

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Base class providing `reapplyToGameKeyMapping()` |
| [WaitAlias](../WaitAlias.java/WaitAlias.md) | Deferred execution, useful for scheduling reapply after screen transition |
| [SUPPORTED_ACTIONS](SUPPORTED_ACTIONS.md) | List of supported action names |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
