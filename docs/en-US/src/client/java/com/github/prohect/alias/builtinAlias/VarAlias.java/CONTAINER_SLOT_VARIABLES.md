# CONTAINER_SLOT_VARIABLES field (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Map storing variable name → container slot index (1-based), set only by `cN` source.

## Syntax

```java
public static final java.util.Map<java.lang.String, java.lang.Integer> CONTAINER_SLOT_VARIABLES
```

## Remarks

**Purpose:** Stores a parallel mapping of variable names to container slot numbers (1-based). When a variable is created with a `cN` source (e.g., `var\mySlot\c5`), the name is stored here with the value N. This allows `SwapSlotAlias.parseSlotRef()` to distinguish container slot references from plain player inventory slot numbers.

**Writers:** `VarAlias.run()` (both overloads) — adds entries for `cN` sources, removes entries when a non-`cN` source overwrites the same variable name. `UnloadUserVarsAlias` — removes runtime entries not in `CFG_CONTAINER_SLOT_VARIABLES`.

**Reader:** `SwapSlotAlias.parseSlotRef()` — the only consumer. Checks this map before falling back to `resolveInt()` so that `cN`-sourced variables are correctly interpreted as container slots.

**Key design insight:** Without this parallel map, `swapSlot\mySlot` would resolve `mySlot` via `resolveInt()` and get the number 5, treating it as player inventory slot 5. With this map, `parseSlotRef()` first checks `CONTAINER_SLOT_VARIABLES`, finds the entry, and correctly treats it as container slot c5.

**Thread safety:** Accessed only from the game thread.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
