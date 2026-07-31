# clearAllLocks method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Restores all locked game keys and custom alias bindings, clearing all lock state. Called on server disconnect to prevent stale key bindings.

## Syntax

```java
public static void clearAllLocks()
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| _(none)_ | | |

## Remarks

**Algorithm:**

1. Iterates over a copy of `savedBoundKeys.keySet()` and calls `unlockAction(actionType)` for each — this restores each vanilla KeyBinding to its original key binding.
2. Iterates over a copy of `LOCKED_ALIAS_KEYS.keySet()` and calls `unlockAliasByName(aliasName)` for each — this removes the physical key blocks for custom aliases.
3. Clears `LOCKED_PHYSICAL_KEYS`, `savedBoundKeys`, and `LOCKED_ALIAS_KEYS` entirely — defensive cleanup in case unlock methods failed to fully clean up.

**Lifecycle:** Called from `ClientPacketListenerMixin` when the client disconnects from a server, ensuring no stale lock state persists across server sessions.

## See Also

| Item | Description |
|------|-------------|
| [lockAction](lockAction.md) | Individual action locking |
| [unlockAction](unlockAction.md) | Individual action unlocking |
| [ClientPacketListenerMixin](../../../mixin/ClientPacketListenerMixin.java/ClientPacketListenerMixin.md) | Calls this on disconnect |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
