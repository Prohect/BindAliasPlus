# LockAlias (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Builtin alias that temporarily locks a vanilla game key or custom user alias, preventing the player's physical keyboard/mouse input from interfering with an alias sequence. Extends `BuiltinAliasWithArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.LockAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.LockAlias>
```

## Static Initializer

_See [static-init](static-init.md)._

## Remarks

Registered as `"builtinLock"`. Usage: `builtinLock\actionType\flag` where `actionType` is a game-key action or custom alias name, and `flag` is `"1"` (lock) or `"0"` (unlock).

**Supported game-key actions** (use `gameKey:` prefix): `gameKey:attack`, `gameKey:use`, `gameKey:forward`, `gameKey:back`, `gameKey:left`, `gameKey:right`, `gameKey:jump`, `gameKey:sneak`, `gameKey:sprint`.

**Lock mechanism for vanilla keys:**
1. Saves the original key binding (`InputUtil.Key`) for the action.
2. Replaces the `KeyBinding.key` with `InputUtil.UNKNOWN_KEY` (GLFW_KEY_UNKNOWN = -1), which GLFW handles gracefully and Minecraft already skips in `releaseAll()` / key polling.
3. Calls `KeyBinding.resetMapping()` to apply the change.
4. Also locks any mod-bound keys (`BINDING_PLUS` entries) whose alias targets the locked action, by adding their physical keys to `LOCKED_PHYSICAL_KEYS`.

**Lock mechanism for custom aliases (`lockAliasByName`):**
1. Looks up all physical keys in `BindAliasClient.BINDING_PLUS` whose bound alias name (on press or release) matches the given name.
2. Adds those physical keys to `LOCKED_PHYSICAL_KEYS` — the set checked by keyboard/mouse mixins to block input.
3. The alias can still be triggered programmatically via `builtinRunAlias`.

**User-facing shortcuts:** `+lockKey\gameKey:attack` ([LockAlias_OnLock](../LockAlias_OnLock.java/LockAlias_OnLock.md)) and `-lockKey\gameKey:attack` ([LockAlias_Unlock](../LockAlias_Unlock.java/LockAlias_Unlock.md)). To lock a custom user alias: `+lockKey\myAliasName`.

**Cleanup:** `clearAllLocks()` is called on server disconnect to restore all original key bindings and prevent stale state.

## See Also

| Item | Description |
|------|-------------|
| [LockAlias_OnLock](../LockAlias_OnLock.java/LockAlias_OnLock.md) | User-facing `+lockKey` wrapper |
| [LockAlias_Unlock](../LockAlias_Unlock.java/LockAlias_Unlock.md) | User-facing `-lockKey` wrapper |
| [SUPPORTED_ACTIONS](SUPPORTED_ACTIONS.md) | List of supported game-key action types |
| [LOCKED_PHYSICAL_KEYS](LOCKED_PHYSICAL_KEYS.md) | Set checked by mixins to block physical input |
| [clearAllLocks](clearAllLocks.md) | Restores all locks on disconnect |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
