# run method (src/client/java/com/github/prohect/alias/builtinAlias/ReapplyAlias.java)

Resolves the action name to a builtin alias and calls `reapplyToGameKeyMapping()` if the key is currently held.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.ReapplyAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Action name (e.g., `"forward"`, `"attack"`, `"+forward"`, `"sneak"`, `"-left"`) |

## Remarks

**Algorithm:**

1. If `args` is null or blank, log a warning and return.
2. Strip `+` or `-` prefix from `args` to get `cleanName`.
3. Derive builtin name: `"builtin" + cleanName with first letter capitalized`.
4. Look up the alias in `aliasesWithArgs` then `aliasesWithArgs_notSuggested`.
5. If found and it's a `BuiltinAliasWithBooleanArgs` with `flag == true`, call `reapplyToGameKeyMapping()`.
6. Otherwise, log a warning about alias not found or not held.

**Example resolutions:**
- `"forward"` → `"builtinForward"`
- `"+sneak"` → `"builtinSneak"`
- `"-attack"` → `"builtinAttack"`
- `"playerList"` → `"builtinPlayerList"`
- `"openInventory"` → `"builtinOpenInventory"`

**Return value:** `this` (fluent return).

**Side effects:** Re-asserts the held key into the game's key mapping state. No-op if the key is not currently held.

## See Also

| Item | Description |
|------|-------------|
| [ReapplyAlias](ReapplyAlias.md) | Class overview |
| [SUPPORTED_ACTIONS](SUPPORTED_ACTIONS.md) | All supported action names |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/reapplyToGameKeyMapping.md) | The method invoked on matched aliases |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
