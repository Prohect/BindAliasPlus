# VarAlias (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Builtin alias that stores and retrieves in-game numeric variables. Extends `BuiltinAliasWithArgs`. This is the central variable system used by many other builtin aliases for argument resolution.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.VarAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.VarAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `var` — usage: `var\varName\source`.

**Variable storage architecture:**

| Map | Type | Purpose |
|-----|------|---------|
| `GENERAL_VARIABLES` | `Map<String, Number>` | All general variable name → numeric value (int or double) |
| `CONTAINER_SLOT_VARIABLES` | `Map<String, Integer>` | Variable name → container slot index (1-based), set only by `cN` source, read only by `SwapSlotAlias` |
| `CFG_VARIABLES` | `Set<String>` | Names of general variables loaded from CFG (for unload tracking) |
| `CFG_CONTAINER_SLOT_VARIABLES` | `Set<String>` | Names of container slot variables loaded from CFG (for unload tracking) |

**Valid sources:**

| Source | Type | Description |
|--------|------|-------------|
| `hotbarSlot` or `selectedSlot` | int | Current selected hotbar slot (1-9) |
| `itemsOfSlot0` | int | Item count in offhand (0 = empty) |
| `itemsOfSlot1`–`itemsOfSlot9` | int | Item count in hotbar slots 1-9 |
| `pitch` | double | Player's current pitch angle |
| `yaw` | double | Player's current yaw angle |
| `cN` (e.g., `c1`, `c5`, `c12`) | int | Container slot number — stored in both `GENERAL_VARIABLES` (numeric value) and `CONTAINER_SLOT_VARIABLES` (as container reference for `SwapSlotAlias`) |
| A literal number | int/double | Direct integer or floating-point value |

**Variable naming rules:** Names must not start with a number (validated by `isValidVarName()` using regex `^[0-9].*`). Null or empty names are also rejected.

**Two run() overloads:**
1. `run(String args)` — standard runtime execution. Stores in `GENERAL_VARIABLES` and `CONTAINER_SLOT_VARIABLES` without CFG tracking.
2. `run(String args, boolean fromAutoload)` — called during CFG loading. When `fromAutoload` is true, the variable name is also added to `CFG_VARIABLES` or `CFG_CONTAINER_SLOT_VARIABLES` for later cleanup by `unloadCFGVars`.

**Resolvers for other aliases:** Three static methods allow other builtin aliases to resolve their arguments through the variable system:
- `resolveValue(String)` → `Number` — resolve a variable name or numeric string to a Number.
- `resolveInt(String)` → `Integer` — convenience, returns the int value or null.
- `resolveDouble(String)` → `Double` — convenience, returns the double value or null.
- `isVariable(String)` → `boolean` — check if a string is a currently stored variable name.

**Container slot semantics:** When a variable is created with a `cN` source (e.g., `var\mySlot\c5`), the value N is stored BOTH in `GENERAL_VARIABLES` (as an integer) AND in `CONTAINER_SLOT_VARIABLES` (as the 1-based slot number). The `CONTAINER_SLOT_VARIABLES` entry is key because `SwapSlotAlias.parseSlotRef()` checks it to distinguish "this variable refers to a container slot" from "this variable holds the number 5". Without this dual storage, `swapSlot\mySlot` would incorrectly interpret `mySlot` as player inventory slot 5 instead of container slot c5.

## See Also

| Item | Description |
|------|-------------|
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md) | Primary consumer of container slot variables |
| [SlotAlias](../SlotAlias.java/SlotAlias.md) | Uses `resolveInt()` for slot selection |
| [PitchAlias](../PitchAlias.java/PitchAlias.md) | Uses `resolveDouble()` for relative rotation |
| [WaitAlias](../WaitAlias.java/WaitAlias.md) | Uses `resolveInt()` for tick count |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md) | Removes CFG-loaded general variables |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/UnloadUserVarsAlias.md) | Removes runtime-created variables |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Direct base class |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
