# GENERAL_VARIABLES field (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Global map storing all variable name → numeric value pairs.

## Syntax

```java
public static final java.util.Map<java.lang.String, java.lang.Number> GENERAL_VARIABLES
```

## Remarks

**Purpose:** The central storage for all variables created by the `var` alias. Keys are variable names (strings), values are `Number` instances (either `Integer` or `Double`).

**Writers:** `VarAlias.run()` (both overloads) — stores values from sources. `UnloadCFGVarsAlias` — removes CFG-loaded entries. `UnloadUserVarsAlias` — removes runtime entries.

**Readers:**
- `VarAlias.resolveValue()`, `resolveInt()`, `resolveDouble()`, `isVariable()` — public static resolvers.
- `SlotAlias.run()` — resolves slot numbers.
- `PitchAlias.run()`, `YawAlias.run()` — resolves rotation angles via `BuiltinAliasWithDoubleArgs.parseArgs()`.
- `SetPitchAlias.run()`, `SetYawAlias.run()` — resolves absolute angles.
- `SetPerspectiveAlias.run()` — resolves perspective index.
- `WaitAlias.run()` — resolves tick counts.
- `SwapSlotAlias.parseSlotRef()` — resolves slot numbers for non-container references.

**Thread safety:** Accessed only from the game thread (single-threaded). No synchronization needed.

**Default value:** Empty `HashMap` initialized at class load.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
