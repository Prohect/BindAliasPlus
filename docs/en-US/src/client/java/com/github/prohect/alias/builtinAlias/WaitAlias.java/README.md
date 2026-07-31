# WaitAlias

Builtin alias that defers alias chain execution. Usage: `wait\N` where N is tick count. Two-arg overload used by `UserAlias` for chain deferral.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [tasksWaiting](tasksWaiting.md) | `ArrayList<WaitAliasRecord>` | Static list of all pending deferred alias tasks |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | Deprecated one-arg: validates tick count only |
| [run](run.md) | `run(String args, String definition)` | Active two-arg: schedules or immediately executes definition |

## See Also

| Item | Description |
|------|-------------|
| [WaitAliasRecord](../WaitAliasRecord.java/README.md) | Deferred task record |
| [UserAlias](../../UserAlias.java/README.md) | Chain executor that calls the two-arg overload |
| [MinecraftClientMixin](../../../mixin/MinecraftClientMixin.java/README.md) | Tick driver for wait tasks |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
