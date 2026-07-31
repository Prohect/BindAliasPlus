# WaitAliasRecord

Deferred task record: holds an alias chain definition, executes it after N ticks elapse. Used by `WaitAlias` and the `MinecraftClientMixin` tick driver.

## Fields

| Name | Type | Description |
|------|------|-------------|
| `ticks` | `int` | Remaining ticks until execution (same unit as game ticks) |
| `definition` | `String` (final) | Alias chain definition string to execute on expiry |
| `reapplyToGameKeyMapping` | `boolean` | If true, definition is a builtin alias name for key reapplication |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [tick](tick.md) | `tick()` | Decrement counter; execute definition when it reaches 0 |

## See Also

| Item | Description |
|------|-------------|
| [WaitAlias](../WaitAlias.java/README.md) | Creator and task list owner |
| [UserAlias](../../UserAlias.java/README.md) | Chain executor called on expiry |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/README.md) | Tick driver |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
