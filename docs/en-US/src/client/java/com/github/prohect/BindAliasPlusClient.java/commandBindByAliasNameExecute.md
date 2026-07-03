# commandBindByAliasNameExecute method (src/client/java/com/github/prohect/BindAliasPlusClient.java)

## Syntax

```java
private int commandBindByAliasNameExecute(java.lang.String, java.lang.String, boolean)
```

## Parameters

| Name           | Type      | Description                                                   |
| -------------- | --------- | ------------------------------------------------------------- |
| `keyName`      | `String`  | Key string (e.g., `"f"`, `"mouse1"`). Parsed by `parseKey()`. |
| `aliasName`    | `String`  | An existing alias name, optionally prefixed with `+` or `-`.  |
| `fromAutoload` | `boolean` | `true` if called from `loadCFG()`.                            |

## Remarks

Binds a key to an existing alias by name, resolving `+`/`-` prefix patterns
to determine press vs. release behavior.

Algorithm:

1. Look up `aliasName` in `Alias.aliasesWithoutArgs`.
2. If not found, check if it starts with `+` or `-`:
   - Strip prefix and retry lookup.
   - `-` prefix means the alias should fire on release (flag = false).
   - If still not found, return `2` (alias doesn't exist in either step 2a or 2b paths).
3. If found directly (no prefix):
   - If name starts with `-`, fire on release (flag = false).
   - Set `flag0` based on whether a `+`/`-` pair pattern is detected.
4. Compute `aliasNameFinalExtra` — the "opposite" alias name (flip `+`/`-` prefix).
   If the opposite alias exists in `aliasesWithoutArgs`, it becomes the release counterpart;
   otherwise empty string.
5. Parse the key via `parseKey(keyName)`. Return `4` if key unknown.
6. Put a `KeyBindingPlus` into `BINDING_PLUS`. Depending on `flag`:
   - `true` (press): `aliasNameOnKeyPressed = aliasNameFinal`, `aliasNameOnKeyReleased = aliasNameFinalExtra`.
   - `false` (release): `aliasNameOnKeyPressed = aliasNameFinalExtra`, `aliasNameOnKeyReleased = aliasNameFinal`.

Return values:

- `1` — bound successfully.
- `2` — alias not found (even after prefix stripping).
- `3` — alias found but no `+`/`-` pattern detected (treated as press-only).
- `4` — unknown key name.

The two-arg overload delegates with `fromAutoload = false`.

## See Also

| Item                                                                  | Description                                           |
| --------------------------------------------------------------------- | ----------------------------------------------------- |
| [commandBindExecute](commandBindExecute.md)                           | Falls through from this method when not a named alias |
| [parseKey](parseKey.md)                                               | Key name parser                                       |
| [KeyBindingPlus](../KeyBindingPlus.java/KeyBindingPlus.md)            | The mapping stored in `BINDING_PLUS`                  |
| [Alias.aliasesWithoutArgs](../alias/Alias.java/aliasesWithoutArgs.md) | Registry looked up by alias name                      |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
