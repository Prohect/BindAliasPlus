# run method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_OnLock.java)

Locks a game key or custom alias. Thin wrapper around `LockAlias.lockAction()`.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.LockAlias_OnLock run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `actionType` | `String` | The action to lock: a game-key action (`gameKey:attack`) or custom alias name |

## Remarks

Simply delegates to `LockAlias.lockAction(actionType)`. See that method for the full locking algorithm.

**Examples:**
- `+lockKey\gameKey:forward` — locks the forward movement key
- `+lockKey\myMacro` — locks physical keys bound to `myMacro`

## See Also

| Item | Description |
|------|-------------|
| [LockAlias.lockAction()](../LockAlias.java/lockAction.md) | The implementation this delegates to |
| [LockAlias_Unlock.run()](../LockAlias_Unlock.java/run.md) | Inverse: unlocks |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
