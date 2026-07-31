# Java Doc Drafting Guide

Extension of [docs/README.md](../README.md) — see that first for the general schema, path structure, and cross-reference rules. This file adds only Java-specific conventions.

## Source layout

```
src/client/java/com/github/prohect/   ← Fabric client-only sources (mixins, aliases, MCP)
src/main/java/com/github/prohect/     ← common sources (entry point, shared config)
```

Generated doc stubs mirror this under `docs/en-US/src/`. Run `./gradlew build` then `bash scripts/generate_docs.sh` to scaffold missing stubs.

## Architecture overview

### Entry points

| Class | Role |
|-------|------|
| `BindAlias` (main source set) | Mod init — logs startup, sets `MOD_ID` |
| `BindAliasClient` (client source set) | Client init — registers all aliases, key bindings, screen-blacklist, loads CFG, starts MCP server |

### Alias hierarchy

```
Alias (interface)
├── AliasWithArgs         — builtin aliases that accept arguments
│   └── BuiltinAliasWithArgs (abstract)
│       ├── BuiltinAliasWithBooleanArgs  — +flag / -flag aliases (attack, use, forward, …)
│       ├── BuiltinAliasWithIntegerArgs  — aliases taking integer args (slot, wait, yaw, pitch)
│       ├── BuiltinAliasWithDoubleArgs   — aliases taking float args (setYaw, setPitch)
│       ├── BuiltinAliasWithStringArgs   — aliases taking string args (say, bind, sendCommand)
│       ├── LockAlias                    — direct arg-based lock (builtinLock\action\flag)
│       └── VarAlias                     — variable storage (var\name\source)
├── AliasWithoutArgs       — aliases triggered by key events (no args)
│   └── BuiltinAliasWithoutArgs (abstract)
│       ├── Single-action (esc, toggleInventory, swapHand, …)
│       └── LockAlias_OnLock / LockAlias_Unlock — +lockKey / -lockKey wrappers
└── UserAlias              — user-defined aliases from CFG or `alias` builtin
```

Key records: `AliasRecord`, `WaitAliasRecord`, `BindAliasKeyBinding`, `KeyPressed`

### Registration pattern

Every builtin alias is registered in `BindAliasClient.onInitializeClient()` via a builder chain:

```java
new SomeAlias()
    .putToAliasesWithArgs()           // or putToAliasesWithoutArgs()
    .addToScreenBlackList()           // optional — suppress on screens
    ;
```

- `putToAliasesWithArgs` — registers by builtin alias name (e.g. `"slot"`, `"var"`)
- `putToAliasesWithArgs_notSuggested` — registers for internal use only (e.g. `"builtinDrop"`)
- `putToAliasesWithoutArgs` / `putToAliasesWithoutArgs_notSuggested` — same for no-args aliases
- `addToScreenBlackList()` — suppresses the alias when any screen is open (checked in `UserAlias.run()`)

### Switch aliases (+/- pattern)

Aliases extending `BuiltinAliasWithBooleanArgs` respond to `+name` / `-name`:

- `parseArgs(args)` sets `this.flag` from `"0"` (off) or `"1"` (on)
- `run("1")` → press, `run("0")` → release
- `reapplyToGameKeyMapping()` is called after screen transitions — default: if `flag` is true, re-run with `"1"`
- Screen suppression: check `Alias.isUnderTextInputScreen()` at the top of `run()` for text-input screens; check `Alias.isUnderAnyScreen()` for non-text screens where the action still applies

### Variable system

`VarAlias` stores `Number` values keyed by name:

| Map | Purpose |
|-----|---------|
| `GENERAL_VARIABLES` | All variable name → Number (int or double) |
| `CONTAINER_SLOT_VARIABLES` | Variable name → container slot index (1-based), set by `cN` source; read only by `SwapSlotAlias` |

Sources: `hotbarSlot`/`selectedSlot`, `itemsOfSlot0`-`itemsOfSlot9`, `pitch`, `yaw`, `cN`, or a literal number.

Resolvers for other aliases: `resolveValue(input)`, `resolveInt(input)`, `resolveDouble(input)`.

CFG auto-load tracking: `CFG_VARIABLES` and `CFG_CONTAINER_SLOT_VARIABLES` track which variables were loaded from cfg, so `unloadCFGVars` can clean them.

### Screen-type helpers (Alias interface)

| Method | Returns true when |
|--------|-------------------|
| `isUnderTextInputScreen()` | Chat, sign, book, command-block screens open |
| `isUnderAnyScreen()` | Any screen open |
| `isInContainerScreen()` | AbstractContainerScreen open |
| `isInInventoryScreen()` | Player inventory screen open |
| `isInCreativeInventoryScreen()` | Creative inventory screen open |

### Mixin module

All mixins inject into vanilla Minecraft classes. Document the injection point (`@At`), method, and purpose.

| Mixin | Injects into | Purpose |
|-------|-------------|---------|
| `MinecraftClientMixin` | `Minecraft.tick()` | Tick driver: screen tracking, WaitAlias timer, continuous drop, MCP nap |
| `KeyboardInputMixin` | `KeyboardInput.tick()` | Inject `+forward`/`+back`/`+left`/`+right` into movement |
| `KeyBoardMixin` | `KeyboardHandler.keyPress()` | Route key events to `KEY_QUEUE` |
| `MouseMixin` | `MouseHandler` | Mouse event routing; freeCursor grab suppression |
| `ClientPacketListenerMixin` | `ClientPacketListener` | Server disconnect → clear locks |
| `AbstractContainerScreenMixin` | `AbstractContainerScreen` | Container screen slot management |
| `ChatComponentMixin` | Chat rendering | Silent mode chat suppression |
| `NativeImageMixin` | Native image | Screenshot capture hook |

### MCP module

HTTP JSON-RPC server running on localhost. Key classes:

| Class | Role |
|-------|------|
| `McpHttpServer` | HTTP server, request routing, nap tasks |
| `StateTracker` | Game state snapshot collection |
| `ScreenshotCapture` | Screenshot taking and encoding |
| `SoundCapture` | Sound event collection |
| `GameChannels` | MCP protocol channel definitions |
| `RecipeBookHelper` | Recipe lookup for `listRecipes`/`applyRecipe` |
| `GameStateCollector` | aggregates state from multiple trackers |

## Class / Interface / Enum / Record (`<Type>.md`)

Fill **Remarks**: purpose, lifecycle (singleton? per-call?), thread safety, key collaborators.
For abstract base classes, document the contract subclasses must fulfill.
For records, document what each component holds and the immutability guarantees.
Fill **See Also**: parent/implemented interfaces, subtypes, heavy users.

## Method (`<method>.md`)

Fill **Parameters** from the signature. Fill **Remarks**: algorithm (step-by-step), side effects (state mutations, logging, network calls, screen changes), callers, error handling. For non-void methods, describe the return value.

For alias `run()` methods specifically:
- Describe the args format (e.g. `+attack`/`-attack`, `slot\3`, `var\name\source`)
- Document screen suppression behavior (which screens block it)
- Note error logging patterns (what gets logged when args are invalid)
- For BooleanArgs aliases: describe the press (1) / release (0) behavior
- For reapply-enabled aliases: describe the `reapplyToGameKeyMapping()` behavior

## Field (`<field>.md`)

Only public/protected fields get a stub. Fill **Remarks**: what it stores, who reads/writes it, thread safety, default value.

For static mutable state (e.g. `GENERAL_VARIABLES`, `KEY_QUEUE`):
- Document the lifecycle (when populated, when cleared)
- Note thread constraints (game thread only)
- List readers and writers

## Static initializer (`static-init.md`)

Only generated when the source has an explicit `static { }` block. Document what is initialized, why a static block instead of field initializers, and failure modes. Common in mixins that use reflection (e.g. `McScreenHelper` branch detection).

## README.md (per-source-file overview)

Fill the **Fields** table (all fields, type, one-line description). Fill the **Methods** table (all public/protected methods, condensed signature, one-line description). Group by category (lifecycle, command handlers, utilities, etc.). Fill **See Also** with related types.

## Mapping branches

This project targets multiple Minecraft versions with different mappings. When a method/class name differs between branches, note the mapping in the doc's Syntax block or Remarks:

- **Mojang** (26.x): `MultiPlayerGameMode`, `AbstractContainerMenu`, `Component`, `Minecraft`
- **Yarn** (1.21.x): `ClientPlayerInteractionManager`, `ScreenHandler`, `Text`, `MinecraftClient`

## Commit footer

Every doc file ends with a commit-SHA footer. Do not remove or modify it — the generator uses it for staleness checks.
