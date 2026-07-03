# ShutdownAlias

## Fields

| Name                             | Type     | Description                                                                   |
| -------------------------------- | -------- | ----------------------------------------------------------------------------- |
| _(inherited)_ `builtinAliasName` | `String` | The alias name `"builtinShutdown"`. Inherited from `BuiltinAliasWithoutArgs`. |

## Methods

| Name  | Signature                        | Description                                                         |
| ----- | -------------------------------- | ------------------------------------------------------------------- |
| `run` | `ShutdownAlias run(String args)` | Logs a shutdown message and calls `Minecraft.getInstance().stop()`. |

## See Also

| Item                                                                                     | Description                      |
| ---------------------------------------------------------------------------------------- | -------------------------------- |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Parent class — no-arg alias base |
| [BindAliasPlusClient](../../../BindAliasPlusClient.java/BindAliasPlusClient.md)          | Main class providing LOGGER      |
| [ReloadCFGAlias](../ReloadCFGAlias.java/ReloadCFGAlias.md)                               | Another no-arg utility alias     |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
