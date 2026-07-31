# UnloadCFGAliasesAlias

One-shot alias that removes CFG-loaded user aliases. Usage: `unloadCFGAliases`. Runtime-created aliases are not affected.

## Fields

_No public/protected fields._

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | Remove all `UserAlias` instances from `aliasesWithoutArgs` where `isFromCFG() == true` |

## See Also

| Item | Description |
|------|-------------|
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/README.md) | Remove all CFG-loaded items |
| [UnloadUserAliasesAlias](../UnloadUserAliasesAlias.java/README.md) | Remove runtime-created aliases (opposite) |
| [ReloadCFGAlias](../ReloadCFGAlias.java/README.md) | Reload CFG after unloading |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
