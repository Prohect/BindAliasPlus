# commandAliasExecute method (src/client/java/com/github/prohect/BindAliasPlusClient.java)

## Syntax

```java
private int commandAliasExecute(java.lang.String, java.lang.String, boolean)
```

## Parameters

| Name           | Type      | Description                                                         |
| -------------- | --------- | ------------------------------------------------------------------- |
| `aliasName`    | `String`  | The name for the new alias.                                         |
| `definition`   | `String`  | The alias definition string (chain of alias definitions with args). |
| `fromAutoload` | `boolean` | `true` if called from `loadCFG()`.                                  |

## Remarks

Creates or replaces a user alias. Guards against overwriting built-in aliases.

Algorithm:

1. Check if `aliasName` already exists as a built-in:
   - If it's in `aliasesWithArgs_notSuggested`, `aliasesWithArgs`, or
     `aliasesWithoutArgs_notSuggested` — return `2` (cannot replace builtin).
2. Look up `aliasName` in `aliasesWithoutArgs`:
   - If it exists but is NOT a `UserAlias` — return `3` (already a builtin).
3. Put/replace the entry in `aliasesWithoutArgs` with a new `UserAlias(definition, fromAutoload)`.
   Return `1`.

Return values:

- `1` — alias created/replaced successfully.
- `2` — name conflicts with a built-in alias with args or non-suggested alias.
- `3` — name conflicts with a non-UserAlias builtin in `aliasesWithoutArgs`.

The two-arg overload delegates with `fromAutoload = false`.

## See Also

| Item                                                                  | Description                 |
| --------------------------------------------------------------------- | --------------------------- |
| [UserAlias](../alias/UserAlias.java/UserAlias.md)                     | The alias type created here |
| [Alias.aliasesWithoutArgs](../alias/Alias.java/aliasesWithoutArgs.md) | Registry written to         |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
