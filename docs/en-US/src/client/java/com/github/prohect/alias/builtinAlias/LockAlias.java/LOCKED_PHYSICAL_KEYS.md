# LOCKED_PHYSICAL_KEYS field (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
public static final java.util.Set<com.mojang.blaze3d.platform.InputConstants$Key> LOCKED_PHYSICAL_KEYS
```

## Remarks

A `HashSet` of physical `InputConstants.Key` instances that are currently blocked from normal input processing. Mixins into Minecraft's input handling check this set to suppress locked keys.

Populated by:

- `lockAction()` — when locking a vanilla game key, the key's original `InputConstants.Key` is added
- `lockModBoundKeys()` — when locking mod-bound keys that target the action
- `lockAliasByName()` — when locking all keys bound to a specific alias name

Cleared by `clearAllLocks()` on server disconnect. Individual keys are removed by `unlockAction()`, `unlockModBoundKeys()`, or `unlockAliasByName()`.

Declared `final` (the reference is immutable) but the `Set` contents are mutable. Not thread-safe.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
