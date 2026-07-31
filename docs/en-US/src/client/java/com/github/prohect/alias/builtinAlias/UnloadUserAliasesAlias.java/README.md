# UnloadUserAliasesAlias

One-shot alias that removes runtime-created user aliases. Usage: `unloadUserAliases`. CFG-loaded and predefined aliases persist.

## Fields

_No public/protected fields._

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | Remove `UserAlias` instances where `!isFromCFG() && !isPredefined()` |

## See Also

| Item | Description |
|------|-------------|
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/README.md) | Remove all runtime-created items |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/README.md) | Remove CFG-loaded aliases (opposite) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
