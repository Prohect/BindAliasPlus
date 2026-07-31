# ReapplyAlias

Builtin alias that re-asserts a single held key after a screen transition. Usage: `reapply\action` (e.g., `reapply\forward`).

## Fields

| Name | Type | Description |
|------|------|-------------|
| [SUPPORTED_ACTIONS](SUPPORTED_ACTIONS.md) | `List<String>` | Supported action names for command suggestions |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | Resolve action name → builtin alias, call `reapplyToGameKeyMapping()` if held |

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/README.md) | Base class providing `reapplyToGameKeyMapping()` |
| [WaitAlias](../WaitAlias.java/README.md) | Deferred execution for scheduling reapply |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
