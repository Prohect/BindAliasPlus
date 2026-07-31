# AliasRecord

Immutable record `(args, aliasName)` representing a single alias invocation. Stored in the deferred execution queue.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [aliasName](aliasName.md) | `String` | The name to look up in global alias maps |
| [args](args.md) | `String` | The arguments string (`""` for no-arg aliases) |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [equals](equals.md) | `boolean equals(Object)` | Component-wise equality |
| [hashCode](hashCode.md) | `int hashCode()` | Component-wise hash |
| [toString](toString.md) | `String toString()` | Debug string representation |

## See Also

| Item | Description |
|------|-------------|
| [UserAlias](UserAlias.java/UserAlias.md) | Decodes definition strings into `AliasRecord` queue |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md) | Stores deferred aliases as `AliasRecord` for tick-delayed execution |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
