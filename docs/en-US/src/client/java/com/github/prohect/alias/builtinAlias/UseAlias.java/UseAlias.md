# UseAlias (src/client/java/com/github/prohect/alias/builtinAlias/UseAlias.java)

Switch alias (`+use` / `-use`) that simulates holding/releasing the use/item key (right-click). Extends `BuiltinAliasWithArgs` directly (NOT `BuiltinAliasWithBooleanArgs`), giving it manual boolean parsing.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UseAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.UseAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `builtinUse` (internal, exposed via `+use` / `-use`).

**Behavior:**
- `+use` (args="1"): Presses the vanilla `useKey` keybinding (right-click), causing the player to use the held item or interact with the targeted block. Increments `timesPressed` for the initial-press event.
- `-use` (args="0"): Releases the `useKey` keybinding.

**Why it extends BuiltinAliasWithArgs directly:** Unlike other BooleanArgs aliases that use `parseArgs()`, `UseAlias` manually switches on the args string (`"0"` → false, `"1"` → true). This is an outlier in the codebase — most other switch aliases use the standard `parseArgs(args)` → `this.flag` pattern.

**Screen suppression:** The press event is cancelled when `Alias.isUnderTextInputScreen()` returns true. Additionally, `+use` and `+attack` have a builtin guard in the MCP system to prevent bypass of the vanilla `releaseAll()` on screen transitions — they're fully suppressed on ALL screens, not just text-input screens. The release event is never suppressed.

**Tap vs hold semantics:**
- Tap (quick press + release): Places a block or uses an item on a block/entity.
- Hold (continuous press): Continuously uses the held item (eating food, drawing a bow, blocking with a shield).

**Reapply behavior:** `+use` and `+attack` are referenced in `ReapplyAlias.SUPPORTED_ACTIONS` as `"use"`. However, the MCP system's builtin guard prevents them from being re-applied automatically after screen transitions for safety.

**Error handling:** Invalid args (neither "0" nor "1") log a warning but do not change key state.

## See Also

| Item | Description |
|------|-------------|
| [AttackAlias](../AttackAlias.java/AttackAlias.md) | Left-click (attack/break) counterpart |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | Movement key (another BooleanArgs alias) |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | Reapply held keys after screen transitions |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Direct base class |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
