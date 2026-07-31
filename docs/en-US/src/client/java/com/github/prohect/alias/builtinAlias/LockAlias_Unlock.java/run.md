# run method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_Unlock.java)

Unlocks a game key or custom alias. Thin wrapper around `LockAlias.unlockAction()`.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.LockAlias_Unlock run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `actionType` | `String` | The action to unlock: a game-key action (`gameKey:attack`) or custom alias name |

## Remarks

Simply delegates to `LockAlias.unlockAction(actionType)`. See that method for the full unlocking algorithm.

**Examples:**
- `-lockKey\gameKey:forward` — unlocks the forward movement key
- `-lockKey\myMacro` — unlocks physical keys bound to `myMacro`

## See Also

| Item | Description |
|------|-------------|
| [LockAlias.unlockAction()](../LockAlias.java/unlockAction.md) | The implementation this delegates to |
| [LockAlias_OnLock.run()](../LockAlias_OnLock.java/run.md) | Inverse: locks |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
