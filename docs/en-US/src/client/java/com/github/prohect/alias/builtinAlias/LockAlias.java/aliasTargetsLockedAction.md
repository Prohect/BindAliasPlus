# aliasTargetsLockedAction method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
private static boolean aliasTargetsLockedAction(java.lang.String, java.util.List<java.lang.String>)
```

## Parameters

| Name        | Type           | Description                                                                              |
| ----------- | -------------- | ---------------------------------------------------------------------------------------- |
| `aliasName` | `String`       | The alias name to check. May be null/empty.                                              |
| `patterns`  | `List<String>` | Action alias patterns (e.g. `["+attack", "-attack", "builtinAttack"]`) to match against. |

## Remarks

Determines whether a given alias name targets one of the specified action patterns. This is used during lock/unlock of mod-bound keys to decide whether a `KeyBindingPlus` entry should be blocked.

**Algorithm**:

1. If `aliasName` is null or empty, return `false`.
2. If `patterns` directly contains `aliasName`, return `true`.
3. Look up the alias in `Alias.aliasesWithoutArgs` and `Alias.aliasesWithoutArgs_fromBindCommand`.
4. If the alias is a `UserAlias`: split its definition string by the alias definition divider, then check each token:
   - If the token is `+lockKey` or `-lockKey`, extract the lock action name and compare to bare pattern names (stripped of `+`/`-` prefix).
   - If the token itself matches any pattern, return `true`.

**Side effects**: None (pure function — only reads static state).

**Callers**: `lockModBoundKeys()` and `unlockModBoundKeys()`.

Return value: `true` if the alias targets (directly or transitively via UserAlias definition) one of the given patterns.

## See Also

| Item                                        | Description |
| ------------------------------------------- | ----------- |
| [lockModBoundKeys](lockModBoundKeys.md)     | Caller      |
| [unlockModBoundKeys](unlockModBoundKeys.md) | Caller      |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
