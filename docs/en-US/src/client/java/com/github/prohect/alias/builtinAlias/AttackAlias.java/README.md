# AttackAlias

## Fields

| Name                             | Type      | Description                                                                                         |
| -------------------------------- | --------- | --------------------------------------------------------------------------------------------------- |
| _(inherited)_ `flag`             | `boolean` | Whether the key is currently pressed (`true` = down). Inherited from `BuiltinAliasWithBooleanArgs`. |
| _(inherited)_ `builtinAliasName` | `String`  | The alias name `"builtinAttack"`. Inherited from `BuiltinAliasWithArgs`.                            |

## Methods

| Name  | Signature                      | Description                                         |
| ----- | ------------------------------ | --------------------------------------------------- |
| `run` | `AttackAlias run(String args)` | Presses (`"1"`) or releases (`"0"`) the attack key. |

## See Also

| Item                                                                                                 | Description                        |
| ---------------------------------------------------------------------------------------------------- | ---------------------------------- |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Parent class — boolean arg parsing |
| [BackAlias](../BackAlias.java/BackAlias.md)                                                          | Same pattern for backward movement |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md)                                                 | Same pattern for forward movement  |
| [JumpAlias](../JumpAlias.java/JumpAlias.md)                                                          | Same pattern for jump              |
| [LeftAlias](../LeftAlias.java/LeftAlias.md)                                                          | Same pattern for left movement     |
| [RightAlias](../RightAlias.java/RightAlias.md)                                                       | Same pattern for right movement    |
| [SneakAlias](../SneakAlias.java/SneakAlias.md)                                                       | Same pattern for sneak             |
| [SprintAlias](../SprintAlias.java/SprintAlias.md)                                                    | Same pattern for sprint            |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
