# LockAlias

Complex builtin alias for temporarily locking vanilla game keys and custom alias key bindings. Replaces KeyMapping keys with `InputConstants.UNKNOWN` and tracks locked physical keys in a mixin-checked set.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [SUPPORTED_ACTIONS](SUPPORTED_ACTIONS.md) | `static final List<String>` | All supported game-key action types, prefixed with `gameKey:` |
| [LOCKED_PHYSICAL_KEYS](LOCKED_PHYSICAL_KEYS.md) | `static final Set<Key>` | Physical keys currently blocked; checked by keyboard/mouse mixins |
| `savedBoundKeys` | `static final Map<String, Key>` | Saved original key bindings per action type (private, package access) |
| `LOCKED_ALIAS_KEYS` | `static final Map<String, Set<Key>>` | Tracks locked keys per alias name for alias-based locking (private, package access) |
| `ACTION_ALIAS_PATTERNS` | `static final Map<String, List<String>>` | Maps bare action name to alias-name patterns (populated by static-init) |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `LockAlias run(String args)` | Parse `actionType\flag` and dispatch lock/unlock |
| [lockAction](lockAction.md) | `static void lockAction(String)` | Lock a vanilla game key or fall through to lockAliasByName |
| [unlockAction](unlockAction.md) | `static void unlockAction(String)` | Unlock a vanilla game key or fall through to unlockAliasByName |
| [lockAliasByName](lockAliasByName.md) | `static void lockAliasByName(String)` | Lock all physical keys bound to a custom alias name |
| [unlockAliasByName](unlockAliasByName.md) | `static void unlockAliasByName(String)` | Unlock physical keys for a custom alias name |
| [clearAllLocks](clearAllLocks.md) | `static void clearAllLocks()` | Restore all locks; called on server disconnect |
| [getKeyBindingForAction](getKeyBindingForAction.md) | `static KeyMapping getKeyBindingForAction(String)` | Map action type to vanilla KeyMapping |
| [lockModBoundKeys](lockModBoundKeys.md) | `static void lockModBoundKeys(String)` | Block mod-bound keys targeting a locked action |
| [unlockModBoundKeys](unlockModBoundKeys.md) | `static void unlockModBoundKeys(String)` | Remove mod-key locks for an action (if not still needed) |
| [aliasTargetsLockedAction](aliasTargetsLockedAction.md) | `static boolean aliasTargetsLockedAction(String, List)` | Recursively check if an alias targets a locked action |

## See Also

| Item | Description |
|------|-------------|
| [LockAlias_OnLock](../LockAlias_OnLock.java/LockAlias_OnLock.md) | User-facing `+lockKey` wrapper |
| [LockAlias_Unlock](../LockAlias_Unlock.java/LockAlias_Unlock.md) | User-facing `-lockKey` wrapper |
| [ClientPacketListenerMixin](../../../mixin/ClientPacketListenerMixin.java/ClientPacketListenerMixin.md) | Calls `clearAllLocks()` on disconnect |
| [KeyBoardMixin](../../../mixin/KeyBoardMixin.java/KeyBoardMixin.md) | Checks `LOCKED_PHYSICAL_KEYS` on key events |
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | Checks `LOCKED_PHYSICAL_KEYS` on mouse events |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
