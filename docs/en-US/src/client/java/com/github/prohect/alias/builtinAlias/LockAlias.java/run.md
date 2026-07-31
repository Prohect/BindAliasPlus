# run method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Main entry point for the builtin lock command. Parses `actionType\flag` and dispatches to `lockAction()` or `unlockAction()`.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.LockAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | Format: `actionType\flag` where actionType is a game-key action or alias name, flag is `"1"` (lock) or `"0"` (unlock) |

## Remarks

1. Splits args on the alias arg divider (`\`) — expects exactly 2 parts (`actionType` and `flag`). If not exactly 2 parts, logs a warning and returns.
2. Parses the flag: `"1"` means lock, anything else means unlock.
3. Dispatches:
   - If lock: calls `lockAction(actionType)`.
   - If unlock: calls `unlockAction(actionType)`.

**Examples:**
- `builtinLock\attack\1` — locks the attack key
- `builtinLock\attack\0` — unlocks the attack key
- `builtinLock\gameKey:forward\1` — locks forward movement (using gameKey prefix)
- `builtinLock\myAlias\1` — locks physical keys bound to custom alias `myAlias`

## See Also

| Item | Description |
|------|-------------|
| [lockAction](lockAction.md) | Lock a vanilla key or custom alias by name |
| [unlockAction](unlockAction.md) | Unlock a vanilla key or custom alias by name |
| [LockAlias_OnLock.run()](../LockAlias_OnLock.java/run.md) | User-facing `+lockKey` wrapper |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
