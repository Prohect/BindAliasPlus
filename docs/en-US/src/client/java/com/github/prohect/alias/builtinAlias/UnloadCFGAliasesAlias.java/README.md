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

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
