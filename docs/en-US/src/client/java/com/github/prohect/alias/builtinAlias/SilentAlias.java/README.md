# SilentAlias

## Fields

| Name                             | Type      | Description                                                                                                         |
| -------------------------------- | --------- | ------------------------------------------------------------------------------------------------------------------- |
| _(inherited)_ `flag`             | `boolean` | Whether silent mode should be enabled (`true`) or disabled (`false`). Inherited from `BuiltinAliasWithBooleanArgs`. |
| _(inherited)_ `builtinAliasName` | `String`  | The alias name `"builtinSilent"`. Inherited from `BuiltinAliasWithArgs`.                                            |

## Methods

| Name  | Signature                      | Description                                      |
| ----- | ------------------------------ | ------------------------------------------------ |
| `run` | `SilentAlias run(String args)` | Enables (`"1"`) or disables (`"0"`) silent mode. |

## See Also

| Item                                                                                                 | Description                              |
| ---------------------------------------------------------------------------------------------------- | ---------------------------------------- |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Parent class — boolean arg parsing       |
| [BindAliasPlusClient](../../../BindAliasPlusClient.java/BindAliasPlusClient.md)                      | Main class owning `silentMode`           |
| [UserAlias](../../UserAlias.java/UserAlias.md)                                                       | Checks `silentMode` to suppress feedback |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
