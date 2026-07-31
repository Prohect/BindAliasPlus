# alias

The alias execution engine — the core of BindAlias mod. Every command the user can invoke (or that the mod uses internally) is an **alias**: an object implementing the [`Alias`](Alias.java/Alias.md) interface.

Aliases are organized in a type hierarchy rooted at `Alias`, with two top-level branches:

- **`AliasWithArgs`** — aliases that accept arguments via the `\` divider (e.g. `slot\3`, `yaw\90`). Only builtin aliases may have args; user aliases never do.
- **`AliasWithoutArgs`** — aliases triggered by name alone (or key events). All user-defined aliases and several builtin actions fall here.

Every alias is registered into one of the static maps on `Alias` by calling a `putToAliases*` method during client initialization. The `UserAlias.run()` method looks up aliases from these maps by name when executing an alias chain.

## Recommended reading order

| Order | Item | Reason |
|-------|------|--------|
| 1 | [Alias](Alias.java/Alias.md) | The root interface — registration maps, screen helpers, parsing |
| 2 | [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) / [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | Marker interfaces — the split between arg-accepting and no-arg aliases |
| 3 | [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Abstract base for all args-accepting builtins |
| 4 | [BuiltinAliasWithBooleanArgs](BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) / [Integer](BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) / [Double](BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) / [String](BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | Typed argument parsing |
| 5 | [BuiltinAliasWithoutArgs](BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Abstract base for no-arg builtins (key-triggerable) |
| 6 | [UserAlias](UserAlias.java/UserAlias.md) | User-defined alias chains from CFG or `alias` command |
| 7 | [AliasRecord](AliasRecord.java/AliasRecord.md) | Immutable record — an alias invocation with its args, stored in the deferred queue |

## Contents

| Name | Description |
|------|-------------|
| [Alias.java](Alias.java/README.md) | Root interface — registration maps, dividers, screen helpers, parsing |
| [AliasRecord.java](AliasRecord.java/README.md) | Record (aliasName, args) — stored in WaitAlias deferral queue |
| [AliasWithArgs.java](AliasWithArgs.java/README.md) | Marker interface for aliases that accept arguments |
| [AliasWithoutArgs.java](AliasWithoutArgs.java/README.md) | Marker interface for aliases triggered by name only |
| [BuiltinAliasWithArgs.java](BuiltinAliasWithArgs.java/README.md) | Abstract base for all args-accepting builtin aliases |
| [BuiltinAliasWithBooleanArgs.java](BuiltinAliasWithBooleanArgs.java/README.md) | Base for `+`/`-` switch aliases (attack, use, movement, ...) |
| [BuiltinAliasWithDoubleArgs.java](BuiltinAliasWithDoubleArgs.java/README.md) | Base for double-arg aliases (setYaw, setPitch) |
| [BuiltinAliasWithIntegerArgs.java](BuiltinAliasWithIntegerArgs.java/README.md) | Base for integer-arg aliases (slot, wait, yaw, pitch) |
| [BuiltinAliasWithStringArgs.java](BuiltinAliasWithStringArgs.java/README.md) | Base for string-arg aliases (say, sendCommand, alias, ...) |
| [BuiltinAliasWithoutArgs.java](BuiltinAliasWithoutArgs.java/README.md) | Abstract base for no-arg builtin aliases |
| [UserAlias.java](UserAlias.java/README.md) | User-defined alias chains; the main alias execution entry point |
| [builtinAlias](builtinAlias/README.md) | All concrete builtin alias implementations |

## Architecture diagram

```
Alias (interface)
├── AliasWithArgs (marker interface)
│   └── BuiltinAliasWithArgs (abstract, stores builtinAliasName)
│       ├── BuiltinAliasWithBooleanArgs  ← +flag / -flag (attack, use, forward, …)
│       ├── BuiltinAliasWithIntegerArgs  ← slot, wait, yaw, pitch
│       ├── BuiltinAliasWithDoubleArgs   ← setYaw, setPitch
│       ├── BuiltinAliasWithStringArgs   ← say, sendCommand, alias, applyRecipe, …
│       ├── LockAlias                    ← builtinLock\action\flag
│       └── VarAlias                     ← var\name\source
├── AliasWithoutArgs (marker interface)
│   └── BuiltinAliasWithoutArgs (abstract, stores builtinAliasName)
│       ├── esc, toggleInventory, swapHand, pickItem, …
│       ├── LockAlias_OnLock / LockAlias_Unlock  ← +lockKey / -lockKey wrappers
│       └── UserAlias                   ← user-defined alias chains
└── (UserAlias implements AliasWithoutArgs directly)
```

## Key concepts

- **Alias chain**: A space-separated string of alias invocations, e.g. `+attack slot\1 wait\5 -attack`. Parsed by `Alias.getDefinitions()`.
- **Definition split**: A backslash-separated string within one alias invocation, e.g. `slot\3`. Parsed by `Alias.getDefinitionSplits()`.
- **Screen blacklist**: Aliases added via `addToScreenBlackList()` are suppressed (only release events `"0"` pass) when any screen is open. Checked in `UserAlias.run()`.
- **Suggested vs not-suggested**: The `_notSuggested` registration maps exist for internal aliases that should not appear in user-facing command suggestions.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
