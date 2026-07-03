# LockAlias

## Fields

| Name                   | Type                                          | Description                                                                       |
| ---------------------- | --------------------------------------------- | --------------------------------------------------------------------------------- |
| `SUPPORTED_ACTIONS`    | `public static final List<String>`            | Game-key action types available for command suggestions, prefixed with `gameKey:` |
| `LOCKED_PHYSICAL_KEYS` | `public static final Set<InputConstants.Key>` | Physical keys currently blocked by any lock. Mixins check this set.               |

## Methods

| Name                                                    | Signature                                                               | Description                                                                     |
| ------------------------------------------------------- | ----------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| [run](run.md)                                           | `public LockAlias run(String args)`                                     | Parses `actionType\flag` args and locks/unlocks accordingly                     |
| [clearAllLocks](clearAllLocks.md)                       | `public static void clearAllLocks()`                                    | Restores all locked keys and clears all lock state; called on server disconnect |
| [lockAction](lockAction.md)                             | `static void lockAction(String actionType)`                             | Locks a vanilla key or alias by name                                            |
| [unlockAction](unlockAction.md)                         | `static void unlockAction(String actionType)`                           | Unlocks a vanilla key or alias by name                                          |
| [lockAliasByName](lockAliasByName.md)                   | `static void lockAliasByName(String aliasName)`                         | Locks all physical keys bound to a given alias name                             |
| [unlockAliasByName](unlockAliasByName.md)               | `static void unlockAliasByName(String aliasName)`                       | Unlocks physical keys previously locked for an alias name                       |
| [aliasTargetsLockedAction](aliasTargetsLockedAction.md) | `private static boolean aliasTargetsLockedAction(String, List<String>)` | Checks whether an alias name targets one of the given action patterns           |
| [getKeyBindingForAction](getKeyBindingForAction.md)     | `private static KeyMapping getKeyBindingForAction(String)`              | Maps an action name to the vanilla `KeyMapping`                                 |
| [lockModBoundKeys](lockModBoundKeys.md)                 | `private static void lockModBoundKeys(String)`                          | Locks mod-bound keys that target a given action                                 |
| [unlockModBoundKeys](unlockModBoundKeys.md)             | `private static void unlockModBoundKeys(String)`                        | Unlocks mod-bound keys, preserving keys still needed by other locks             |

## See Also

| Item                                                              | Description                 |
| ----------------------------------------------------------------- | --------------------------- |
| [LockAlias_OnLock](../LockAlias_OnLock.java/README.md)            | User-facing lock shortcut   |
| [LockAlias_Unlock](../LockAlias_Unlock.java/README.md)            | User-facing unlock shortcut |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/README.md) | Parent class                |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
