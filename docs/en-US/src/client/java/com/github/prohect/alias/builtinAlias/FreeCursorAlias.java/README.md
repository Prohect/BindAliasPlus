# FreeCursorAlias

Switch alias that toggles a free-cursor mode for developer convenience. When active, the OS cursor stays free while game mouse logic (hold-to-mine, camera rotation) continues normally.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [freeCursor](freeCursor.md) | `public static boolean` | Flag read by `MouseMixin`; when true, skips OS-level cursor grab |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `FreeCursorAlias run(String args)` | Enable/disable free cursor mode with safe grab-state transitions |

## See Also

| Item | Description |
|------|-------------|
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | Reads `freeCursor` to skip OS-level grab calls |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Base class for switch aliases |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
