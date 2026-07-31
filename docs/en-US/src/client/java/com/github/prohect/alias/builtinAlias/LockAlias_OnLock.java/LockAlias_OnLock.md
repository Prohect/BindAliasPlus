# LockAlias_OnLock (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_OnLock.java)

User-facing lock-key alias. Wraps `LockAlias.lockAction()` for the `+lockKey` switch form. Extends `BuiltinAliasWithArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.LockAlias_OnLock extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.LockAlias_OnLock>
```

## Static Initializer

_None._

## Remarks

Registered as `"+lockKey"`. Usage:
- `+lockKey\gameKey:attack` — locks the vanilla attack key
- `+lockKey\myAlias` — locks physical keys bound to the custom alias `myAlias`

Command completion suggests both `gameKey:*` actions and custom `UserAlias` names.

This is a thin wrapper: `run(actionType)` simply calls `LockAlias.lockAction(actionType)`, which dispatches to either key-binding replacement (for vanilla keys) or `lockAliasByName()` (for custom aliases).

## See Also

| Item | Description |
|------|-------------|
| [LockAlias_Unlock](../LockAlias_Unlock.java/LockAlias_Unlock.md) | Inverse `-lockKey` wrapper |
| [LockAlias](../LockAlias.java/LockAlias.md) | Core lock implementation |
| [LockAlias.lockAction()](../LockAlias.java/lockAction.md) | The method this delegates to |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
