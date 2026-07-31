# commandBindExecute method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
private int commandBindExecute(java.lang.String, java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `keyName` | `String` | Human-readable key name (e.g. `"f"`, `"mouse1"`) |
| `args` | `String` | Alias definition chain — either an existing alias name or a new inline definition |

## Remarks

Binds a key to an alias definition. First attempts `commandBindByAliasNameExecute` — if the `args` string matches an existing `AliasWithoutArgs` name, delegates to that path (returns `1`). Otherwise treats `args` as a new inline alias definition:

1. Generates two random 16-character alias names (for press and release).
2. Resolves `keyName` via `parseKey` — returns `2` if unknown.
3. Creates a `UserAlias` from `args` stored in `aliasesWithoutArgs_fromBindCommand` under the first random name.
4. Computes the opposite definition via `Alias.getOppositeDefinition(args)` (e.g., `+attack` → `-attack`). If non-blank, stores a second `UserAlias` under the second random name.
5. Puts a `BindAliasKeyBinding` into `BINDING_PLUS` mapping the key to the generated alias names.

Returns `3` on success (alias created and bound). The private 3-arg overload adds `fromAutoload` tracking for CFG-origin bindings.

## See Also

| Item | Description |
|------|-------------|
| [commandBindByAliasNameExecute](commandBindByAliasNameExecute.md) | First-attempt path for existing alias names |
| [BindAliasKeyBinding](BindAliasKeyBinding.md) | The binding record stored in `BINDING_PLUS` |
| [UserAlias](../alias/UserAlias.java/UserAlias.md) | Created to wrap the inline definition |
| [parseKey](parseKey.md) | Converts key name strings to `InputUtil.Key` |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
