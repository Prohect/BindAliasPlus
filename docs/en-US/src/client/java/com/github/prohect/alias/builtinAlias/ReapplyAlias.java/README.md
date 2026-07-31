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

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
