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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
