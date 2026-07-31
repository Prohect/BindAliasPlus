# SilentAlias (src/client/java/com/github/prohect/alias/builtinAlias/SilentAlias.java)

Switch alias (`+silent` / `-silent`) that toggles silent mode — suppresses or restores mod feedback messages in chat. Extends `BuiltinAliasWithBooleanArgs`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SilentAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.SilentAlias>
```

## Static Initializer

_None._

## Remarks

**Alias name:** `builtinSilent` (internal, exposed via `+silent` / `-silent`).

**Behavior:**
- `+silent` (flag=1): Enables silent mode — sets `BindAliasClient.silentMode = true`. Most mod feedback messages (info, warn) are suppressed in chat.
- `-silent` (flag=0): Disables silent mode — sets `BindAliasClient.silentMode = false`. Feedback messages resume normally.

**What gets suppressed:** The `silentMode` flag is checked by many builtin aliases (unload operations, slot, var, etc.) in their logging. When true, informational feedback messages skip logging. Error-level logs and the `log` alias output are typically NOT suppressed.

**No screen suppression:** This is a configuration setting, not a game input — it works on any screen, including text-input screens. The source comments explicitly note: "this is not a game operation, so we don't need to cancel press events from text input screen."

**Do not confuse with:** The `silent` tag on items or the vanilla `/stopsound` command. This silent mode only suppresses mod-builtin feedback messages in local chat.

**Typical use case:** Use `+silent` before running a chain of aliases that would otherwise spam chat with feedback messages, then `-silent` to restore normal feedback.

## See Also

| Item | Description |
|------|-------------|
| [LogAlias](../LogAlias.java/LogAlias.md) | Write to mod log (not suppressed by silent mode) |
| [BindAliasClient](../../../BindAliasClient.java/BindAliasClient.md) | Client entry point storing `silentMode` |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Base class for switch aliases |
| [FreeCursorAlias](../FreeCursorAlias.java/FreeCursorAlias.md) | Another non-game switch alias |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
