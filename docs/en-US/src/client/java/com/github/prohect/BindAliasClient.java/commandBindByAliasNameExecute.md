# commandBindByAliasNameExecute method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
private int commandBindByAliasNameExecute(java.lang.String, java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `keyName` | `String` | Human-readable key name (e.g. `"f"`, `"mouse1"`) |
| `aliasName` | `String` | An existing alias name, optionally prefixed with `+` or `-` |

## Remarks

Binds a key to an existing `AliasWithoutArgs` by name, supporting the `+`/`-` toggle pattern. The algorithm:

1. Look up `aliasName` in `Alias.aliasesWithoutArgs`.
2. If not found, try stripping a leading `+` or `-` and look up the remainder.
3. Determine the press/release pair:
   - If the original name starts with `+`, the press alias is `+name` and release is `-name` (or the reverse if it starts with `-`).
   - If the name is bare (no `+`/`-` prefix) but its `-` counterpart exists, both are used. Otherwise only the bare name is bound (release alias is empty).
4. Parse the key name via `parseKey`.
5. Put a `BindAliasKeyBinding` into `BINDING_PLUS` with the resolved press and release alias names.

Return codes: `1` = success, `2` = alias not found, `3` = alias found but no `+`/`-` pattern to infer (non-toggle), `4` = unknown key.

## See Also

| Item | Description |
|------|-------------|
| [BindAliasKeyBinding](BindAliasKeyBinding.md) | The binding record stored in `BINDING_PLUS` |
| [parseKey](parseKey.md) | Converts key name strings to `InputConstants.Key` |
| [Alias](../alias/Alias.java/Alias.md) | The `aliasesWithoutArgs` map queried here |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
