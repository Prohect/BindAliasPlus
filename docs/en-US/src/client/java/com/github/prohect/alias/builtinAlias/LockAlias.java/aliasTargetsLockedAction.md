# aliasTargetsLockedAction method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Recursively checks whether an alias name (or its `UserAlias` definition) targets a locked game action.

## Syntax

```java
private static boolean aliasTargetsLockedAction(java.lang.String, java.util.List<java.lang.String>)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `aliasName` | `String` | The alias name to check (e.g., `"+attack"`, `"myCustomAlias"`) |
| `patterns` | `List<String>` | The action-name patterns to match against (e.g., `["+attack", "-attack", "builtinAttack"]`) |

## Return value

`true` if the alias (or its definition if it's a `UserAlias`) directly or indirectly targets the locked action.

## Remarks

**Algorithm:**

1. If `aliasName` is null or empty, returns `false`.
2. **Direct match:** If `patterns.contains(aliasName)`, returns `true`.
3. **UserAlias lookup:** Looks up the alias in `Alias.aliasesWithoutArgs` (and `aliasesWithoutArgs_fromBindCommand` as fallback).
4. If the alias is a `UserAlias`:
   - Gets its definition string and splits by the alias definition divider.
   - For each token in the definition:
     - Splits the token by arg divider to extract the alias part.
     - **Special `+lockKey`/`-lockKey` handling:** If the token starts with `+lockKey` or `-lockKey` and has an action argument, checks if that action (stripped of `+`/`-` prefix) matches any bare pattern.
     - **Direct match:** If the alias part itself is in the patterns list, returns `true`.
5. Returns `false` if no match found.

This recursive check ensures that if a user defines an alias like `doAttack swapSlot\1\9 +attack wait\1 -attack swapSlot\1\9`, any physical key bound to `doAttack` will also be locked when `attack` is locked.

## See Also

| Item | Description |
|------|-------------|
| [lockModBoundKeys](lockModBoundKeys.md) | Uses this to find mod keys targeting locked actions |
| [UserAlias](../../UserAlias.java/UserAlias.md) | User-defined alias whose definition is inspected |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
