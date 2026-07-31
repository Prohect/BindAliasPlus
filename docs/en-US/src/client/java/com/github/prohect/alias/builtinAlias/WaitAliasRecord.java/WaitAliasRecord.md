# WaitAliasRecord (src/client/java/com/github/prohect/alias/builtinAlias/WaitAliasRecord.java)

A deferred task record that holds an alias chain definition and executes it after a specified number of ticks elapse. This is a simple mutable record-like class, not a Java `record`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.WaitAliasRecord
```

## Static Initializer

_None._

## Remarks

**Purpose:** Represents a single deferred alias chain. Created by `WaitAlias.run(String, String)` when the tick count is > 0. Each game tick, `MinecraftClientMixin` calls `tick()` on every record in `WaitAlias.tasksWaiting`.

**Lifecycle:**
1. Created with `ticks` (countdown), `definition` (the alias chain string), and `reapplyToGameKeyMapping` flag.
2. Each tick, `tick()` is called → decrements `ticks`.
3. When `ticks` reaches 0:
   - If `reapplyToGameKeyMapping` is true: looks up the builtin alias whose name matches `definition` and calls `reapplyToGameKeyMapping()`.
   - Otherwise: creates a new `UserAlias(definition)` and calls `run("")` to execute the deferred chain.
4. Removes `this` from `WaitAlias.tasksWaiting`.

**Fields:**

| Field | Type | Description |
|-------|------|-------------|
| `ticks` | int | Remaining tick count. Decremented each client tick. |
| `definition` | String (final) | The alias chain definition to execute when ticks reaches 0. |
| `reapplyToGameKeyMapping` | boolean | If true, the definition is treated as a builtin alias name for `reapplyToGameKeyMapping()`. |

**reapplyToGameKeyMapping mode:** When this flag is true, the `definition` field is assumed to be simply the alias name (not a full chain). On expiry, the record looks up the name in `aliasesWithArgs` and `aliasesWithArgs_notSuggested` and calls `reapplyToGameKeyMapping()` on the matching `BuiltinAliasWithBooleanArgs` instance. This is used internally for deferred key re-application after screen transitions.

**Thread safety:** Accessed only from the game thread.

## See Also

| Item | Description |
|------|-------------|
| [WaitAlias](../WaitAlias.java/WaitAlias.md) | Creator and owner of the `tasksWaiting` list |
| [UserAlias](../../UserAlias.java/UserAlias.md) | The chain executor called on expiry |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Target for `reapplyToGameKeyMapping` mode |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | Tick driver |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
