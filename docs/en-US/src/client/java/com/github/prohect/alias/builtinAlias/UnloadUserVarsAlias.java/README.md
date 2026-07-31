# UnloadUserVarsAlias

One-shot alias that removes runtime-created variables (both general and container slot). Usage: `unloadUserVars`.

## Fields

_No public/protected fields._

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | Remove vars not in CFG tracking sets from both `GENERAL_VARIABLES` and `CONTAINER_SLOT_VARIABLES` |

## See Also

| Item | Description |
|------|-------------|
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/README.md) | Remove all runtime-created items |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/README.md) | Remove CFG-loaded vars (opposite) |
| [VarAlias](../VarAlias.java/README.md) | Variable system |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
