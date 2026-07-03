# BuiltinAliasWithIntegerArgs

## Fields

| Name   | Type  | Description                                            |
| ------ | ----- | ------------------------------------------------------ |
| `flag` | `int` | Parsed integer value from the last `parseArgs()` call. |

## Methods

| Name        | Signature                | Description                                                                    |
| ----------- | ------------------------ | ------------------------------------------------------------------------------ |
| `parseArgs` | `void parseArgs(String)` | Parses args into an `int` value, with `VarAlias` variable resolution fallback. |

## See Also

| Item                                                                                           | Description                         |
| ---------------------------------------------------------------------------------------------- | ----------------------------------- |
| [BuiltinAliasWithArgs](../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md)                   | Parent class                        |
| [BuiltinAliasWithDoubleArgs](../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | Sibling for double flags            |
| [VarAlias](builtinAlias/VarAlias.java/VarAlias.md)                                             | Variable resolution used in parsing |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
