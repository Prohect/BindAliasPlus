# BuiltinAliasWithBooleanArgs (src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java)

## Syntax

```java
public abstract class BuiltinAliasWithBooleanArgs<T extends BuiltinAliasWithBooleanArgs<T>> extends BuiltinAliasWithArgs<T>
```

Abstract base class for builtin aliases that operate as two-state switches — responding to `+name` (press / enable) and `-name` (release / disable). All movement keys, action keys, and toggle keys (`+attack`, `+use`, `+forward`, `+back`, `+left`, `+right`, `+jump`, `+sneak`, `+sprint`, `+drop`, `+playerList`, `+advancements`, `+silent`, `+freeCursor`) extend this class.

## Remarks

The `flag` field stores the current boolean state. `parseArgs(args)` sets it from `"0"` (off/release) or `"1"` (on/press). Invalid args log a warning via `BindAliasClient.LOGGER`.

**Press/release behavior**:
- `run("1")` — the key is pressed / held; concrete subclasses inject into vanilla key mappings.
- `run("0")` — the key is released; concrete subclasses release the vanilla key mapping.

**Reapply after screen transitions**: `reapplyToGameKeyMapping()` is called after a screen closes (triggered by `reapply` alias). If `flag` is `true`, it re-runs with `"1"` to re-sync the held key state into the game's key mappings. This counters Minecraft's `releaseAll()` behavior on `setScreen()`.

**Screen suppression**: Most boolean aliases check `Alias.isUnderTextInputScreen()` at the top of `run()` to avoid injecting key presses while the user is typing in chat, sign, book, or command-block screens. Some also check `Alias.isUnderAnyScreen()` for non-text screens where the action should still apply.

Concrete subclasses typically call `parseArgs(args)` at the start of `run()`, then act based on `this.flag`.

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class — registration and builtinAliasName |
| [Alias.isUnderTextInputScreen](Alias.java/isUnderTextInputScreen.md) | Screen guard used by boolean-arg aliases |
| [reapply](builtinAlias/ReapplyAlias.java/ReapplyAlias.md) | Triggers `reapplyToGameKeyMapping()` across all boolean aliases |
| [builtinAlias](builtinAlias/README.md) | All +attack, +use, +forward, ... implementations |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
