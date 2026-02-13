# BindAliasPlus — Developer Notes

This document is for contributors and maintainers. It explains how to build/run the mod, how the alias/bind system works internally, and where to make changes.

## Project overview

BindAliasPlus is a **Fabric client mod** that provides:

- Client commands: `/alias`, `/bind`, `/bindByAliasName`, `/unbind`, `/reloadCFG`
- An alias system that can execute **action sequences**
- A key-binding layer that maps **keyboard/mouse buttons** to **aliases**, including press/release behavior
- A simple config file loader (`config/bind-alias-plus.cfg`)

Key idea: *keys trigger only “aliases without args”*, while “aliases with args” are used as primitives inside a user alias definition.

---

## Prerequisites

- Java **21**
- A working Gradle environment (use the included Gradle wrapper scripts)
- A Fabric-compatible dev setup (the project uses `fabric-loom`)

Minecraft compatibility is declared in the mod metadata (`fabric.mod.json`) and Gradle properties.

---

## Build

From the repository root:

- On Windows:
  - `gradlew.bat build`
- On macOS/Linux:
  - `./gradlew build`

The built JAR(s) will be created under `build/libs/`.

Notes:
- The project uses Fabric Loom and Java release level 21.
- Resources are processed so `${version}` in `fabric.mod.json` is expanded from Gradle inputs.

---

## Run (dev)

Typical Fabric Loom tasks:
- `gradlew runClient`
- `gradlew runClient --info` (more logging)
- `gradlew genSources` (if you need sources generated / IDE indexing help)

If you’re using an IDE, import the Gradle project and run the Loom run configurations/tasks.

---

## Repository structure

High-level structure:

- `src/main/java/...`
  - `BindAliasPlus` (mod initializer; minimal logic)
- `src/client/java/...`
  - `BindAliasPlusClient` (main mod logic: builtin aliases registration, command registration, config loading)
  - Mixins (input capture, tick hook)
  - Alias system (`alias/`)
- `src/main/resources/`
  - `fabric.mod.json`
  - mixin configs
  - assets (icon, etc.)
- `src/client/resources/`
  - client mixin config
  - access widener

---

## Runtime lifecycle

### Entry points

`fabric.mod.json` declares entrypoints:
- `main`: `com.github.prohect.BindAliasPlus`
- `client`: `com.github.prohect.BindAliasPlusClient`

In practice, almost everything happens in the **client initializer**:
- registers builtin aliases
- loads config file
- registers client commands

### Tick hook

A mixin into `MinecraftClient.tick` drives waiting tasks:
- `WaitAlias.tasksWaiting` is ticked each client tick
- each waiting task decrements and eventually resumes execution of remaining definitions

---

## Alias system (internals)

### Core types

- `Alias<T>` (interface)
  - global registries:
    - `aliasesWithoutArgs`
    - `aliasesWithArgs`
    - `aliasesWithArgs_notSuggested`
    - `aliasesWithoutArgs_fromBindCommand` (auto-generated aliases created by `/bind <key> <definition>`)
  - parsing helpers:
    - `divider4AliasDefinition` = space `' '`
    - `divider4AliasArgs` = backslash `'\'`
    - `getDefinitions(...)`: splits a full alias definition into tokens by space, respecting `"double quotes"`
    - `getDefinitionSplits(...)`: splits a single token into `name\arg1\arg2...`, respecting `"double quotes"`
    - `getOppositeDefinition(...)`: generates a best-effort “release” definition by flipping leading `+`/`-` tokens (also respects quotes)

- `AliasWithoutArgs`
  - registers into `Alias.aliasesWithoutArgs`

- `AliasWithArgs`
  - registers into `Alias.aliasesWithArgs` or `aliasesWithArgs_notSuggested`

- `UserAlias` (implements `AliasWithoutArgs`)
  - stores a *compiled queue of `AliasRecord`* built from its definition string
  - runs definitions sequentially
  - handles recursion with loop detection
  - has special handling for `wait` to suspend/resume the remaining queue

- `AliasRecord`
  - `(args, aliasName)` pair
  - for aliases without args, `args` is empty

### How parsing works

A *user alias definition* is a string like:

- `equipElytra jump wait\1 jump swapSlot\19 +use -use`

Parsing steps:
1. Split into **definitions** by spaces (`divider4AliasDefinition`), but keep spaces inside `"..."`.
2. For each definition token, split by backslash (`divider4AliasArgs`) into:
   - alias name
   - optional args (joined back using backslash between segments)
3. Enqueue as `AliasRecord(args, aliasName)` and execute in order.

### How execution works

When running a `UserAlias`:
1. Dequeue `AliasRecord`
2. Resolve the alias by lookup order:
   - `aliasesWithoutArgs`
   - `aliasesWithArgs_notSuggested`
   - `aliasesWithArgs`
3. If found:
   - if it’s another `UserAlias`, run it recursively (with call-chain loop detection)
   - if it’s `WaitAlias`, schedule a waiting task with the remaining definition string and return immediately
   - otherwise, call `alias.run(args)`

---

## Builtin aliases (internals)

Builtins are registered in `BindAliasPlusClient.onInitializeClient()`.

There are two broad categories:

### 1) Builtin *with args* (primitives)

Examples:
- `swapSlot\slot1\slot2` — inventory swapping logic; supports hotbar/equipment/offhand semantics
- `slot\1..9` — selects hotbar index and sends `UpdateSelectedSlotC2SPacket`
- `wait\ticks` — scheduling primitive; integrates with tick mixin
- `yaw\deg`, `pitch\deg`, `setYaw\deg`, `setPitch\deg` — view rotation
- `say\text`, `sendCommand\command` — emits chat message / command

Special: “greedy string” builtins for nested scripting:
- `alias\payload`, `bind\payload`, `unbind\payload`, `say\payload`, `sendCommand\payload`
- These are implemented as `BuiltinAliasWithGreedyStringArgs`, which defines an internal divider `divider4AliasDefinition = ';'`.
- Why `;` exists: it lets you keep the entire nested payload as **one outer token** (outer parsing splits by spaces), while still expressing multiple parts inside the payload.

Important behavioral difference (how `;` is handled):
- `AliasAlias`, `BindAlias`, `UnbindAlias`:
  - they **replace `;` with a space** in their payload before sending the final chat command line.
  - This is why a user can write `alias\jump;+jump;wait\1;-jump` (no quotes) and it becomes the normal command: `alias jump +jump wait\1 -jump`.
- `SayAlias`, `SendCommandAlias`:
  - they **do not replace `;`**.
  - Their payload is sent as-is, so `;` remains a literal character in the chat message / command string.

Quoting note for nested payloads:
- Quotes are only necessary when the payload contains spaces that would otherwise be split by the outer alias parser.
- If you want to avoid quotes, structure the payload as a single token and use `;` (for `alias/bind/unbind`, `;` will be converted to spaces).

### 2) Builtin *without args* (direct actions)

Examples:
- `swapHand`, `cyclePerspective`, `reloadCFG`

Plus many “shortcut” user aliases without args are pre-registered:
- `+attack` → `builtinAttack\1`
- `-attack` → `builtinAttack\0`
- `+use` / `-use`, `+forward` / `-forward`, etc.
- `+silent` / `-silent` for toggling silent mode

---

## Key binding system (internals)

### Data model

- `KeyBindingPlus` record:
  - `aliasNameOnKeyPressed`
  - `aliasNameOnKeyReleased`

- `BindAliasPlusClient.BINDING_PLUS`:
  - `Map<InputUtil.Key, KeyBindingPlus>`

- `BindAliasPlusClient.KEY_QUEUE`:
  - a queue of `{ key, pressed }` events (`KeyPressed` record)

### Input capture

Mixins capture real keyboard/mouse events:
- `Keyboard.onKey(...)` mixin
- `Mouse.onMouseButton(...)` mixin

Behavior notes:
- Ignores input while typing in chat / editing text (Chat, book, sign, command block screens)
- Enqueues only *press* (action=1) and *release* (action=0)
- Filters out “held repeat” events (action=2), because they would spam triggers

### Event consumption

A mixin into `KeyboardInput.tick(...)` consumes the queue:
1. Pop `{key, pressed}` from `KEY_QUEUE`
2. Look up `KeyBindingPlus`
3. Resolve the alias name for press or release
4. Run an `AliasWithoutArgs` with empty args

Important constraint:
- Key events only trigger `AliasWithoutArgs`.
- `/bind <key> <definition>` works by generating random alias names and storing them into `aliasesWithoutArgs_fromBindCommand`, then binding the key to those generated ids.

### Cursor lock edge case

When the game locks the cursor after closing a UI screen, vanilla essentially “re-syncs” key pressed states.

The mixin on `Mouse.lockCursor()` re-applies pressed-state builtins:
- it iterates builtin boolean-arg aliases
- if their internal `flag` says “pressed”, it re-runs them with `"1"`
- some aliases are excluded via `Alias.blackList4lockCursor` (e.g., dropping items)

This prevents stuck/un-stuck states after UI transitions.

---

## Commands (developer view)

All commands are registered as **client commands**.

- `/alias <name> <definition>`
  - Creates or replaces a `UserAlias` in `aliasesWithoutArgs`
  - Refuses to replace builtin names (both suggested and non-suggested registries)
  - Also refuses to replace a non-`UserAlias` alias without args

- `/bind <key> <definition>`
  - First tries to interpret `<definition>` as an alias name (via bind-by-alias-name logic)
  - If that fails, it:
    - creates two random alias names
    - stores the pressed-definition alias in `aliasesWithoutArgs_fromBindCommand`
    - computes and stores an opposite definition alias (release) if possible
    - binds those ids to the key

- `/bindByAliasName <key> <alias>`
  - Binds directly to existing aliases in `aliasesWithoutArgs`
  - Supports `+name` / `-name` patterns and will attach the opposite alias on release if it exists

- `/unbind <key>`
  - Removes from `BINDING_PLUS`

- `/reloadCFG`
  - Calls config loader again

### Silent mode

`BindAliasPlusClient.silentMode` suppresses **feedback messages** sent by command execution, but does not suppress logs.

---

## Config file loader

- Config path: `config/bind-alias-plus.cfg`
- Loaded during client init, and via `/reloadCFG` or builtin alias `reloadCFG`.
- Supported lines:
  - `alias <name> <definition>`
  - `bind <key> <definition>`
  - `bindByAliasName <key> <aliasName>`
  - `unbind <key>`
- Lines can optionally begin with `/` (it will be stripped).
- Blank lines and `#...` comments are ignored.

---

## Adding a new builtin alias

1. Create a class in `com.github.prohect.alias.builtinAlias`.
2. Decide if it’s:
   - `BuiltinAliasWithoutArgs`
   - `BuiltinAliasWithArgs`
   - or one of the typed helpers (`...WithBooleanArgs`, `...WithIntegerArgs`, `...WithDoubleArgs`, `...WithGreedyStringArgs`)
3. Implement `run(String args)`.
4. Register it in `BindAliasPlusClient.onInitializeClient()` using:
   - `.putToAliasesWithArgs("name")`
   - `.putToAliasesWithoutArgs("name")`
   - optionally `.putToAliasesWithArgs_notSuggested("internalName")`

Optional:
- If it shouldn’t be re-triggered during cursor lock syncing, call `.addToLockCursorBlackList()` during registration.

---

## Debugging tips

- Most “why didn’t it run” issues are:
  - wrong parsing because of spaces vs quotes
  - missing opposite alias for release behavior
  - alias name mismatch (especially with `+`/`-` forms)
  - key parsing differences (`mouse5` maps to a specific internal code)
- Add logs where resolution happens:
  - `UserAlias.run(...)` around alias lookups
  - `KeyboardInputMixin.tick(...)` on key event consumption
  - config loader’s per-line parsing path

---

## Contributing checklist

- Keep user-facing docs in `README.md` only; keep developer details here.
- Maintain backward compatibility of parsing rules (`space` for definition split, `\` for args split, and quote handling).
- Prefer adding new capabilities as builtin aliases rather than adding special cases to `UserAlias`.
