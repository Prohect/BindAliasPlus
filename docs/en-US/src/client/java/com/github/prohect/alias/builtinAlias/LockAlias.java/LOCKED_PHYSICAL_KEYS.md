# LOCKED_PHYSICAL_KEYS field (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Public static set of physical input keys currently blocked from reaching the game.

## Syntax

```java
public static final java.util.Set<com.mojang.blaze3d.platform.InputConstants$Key> LOCKED_PHYSICAL_KEYS
```

## Remarks

This set is checked by keyboard and mouse mixins to determine whether a physical key/mouse event should be suppressed. When a key is in this set, the mixin drops the event before it reaches vanilla key processing.

**Populated by:**
- `lockAction()` — adds the original key of a locked vanilla KeyMapping, plus any mod-bound keys targeting that action
- `lockAliasByName()` — adds all physical keys bound to a given alias name
- `lockModBoundKeys()` — adds keys from `BINDING_PLUS` whose alias targets a locked action

**Cleared by:**
- `unlockAction()` — removes the saved key and any exclusively-locked mod-bound keys
- `unlockAliasByName()` — removes keys associated with a specific alias
- `clearAllLocks()` — clears everything on disconnect

**Readers (mixins):**
- `KeyBoardMixin` — checks this set on key press events
- `MouseMixin` — checks this set on mouse button events

Thread safety: Only accessed from the game thread (alias execution and mixin injection points).

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
