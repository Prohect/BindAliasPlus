# BuiltinAliasWithBooleanArgs

## Fields

| Name   | Type      | Description                                                                                 |
| ------ | --------- | ------------------------------------------------------------------------------------------- |
| `flag` | `boolean` | Parsed boolean value from the last `parseArgs()` call. `true` = key-down, `false` = key-up. |

## Methods

| Name                      | Signature                        | Description                                                                                                  |
| ------------------------- | -------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| `parseArgs`               | `void parseArgs(String)`         | Parses `"0"` as `false` (key-up) and `"1"` as `true` (key-down).                                             |
| `reapplyToGameKeyMapping` | `void reapplyToGameKeyMapping()` | If `flag` is `true`, re-runs the alias with `"1"` to restore the key-down state into the game's key mapping. |

## See Also

| Item                                                                                              | Description               |
| ------------------------------------------------------------------------------------------------- | ------------------------- |
| [BuiltinAliasWithArgs](../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md)                      | Parent class              |
| [BuiltinAliasWithIntegerArgs](../BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | Sibling for integer flags |
| [BuiltinAliasWithDoubleArgs](../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md)    | Sibling for double flags  |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
