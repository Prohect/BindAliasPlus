# SayAlias

## Fields

| Name                                    | Type     | Description                                                                                 |
| --------------------------------------- | -------- | ------------------------------------------------------------------------------------------- |
| _(inherited)_ `builtinAliasName`        | `String` | The alias name `"say"`. Inherited from `BuiltinAliasWithArgs`.                              |
| _(inherited)_ `divider4AliasDefinition` | `char`   | The `';'` divider for alias definitions. Inherited from `BuiltinAliasWithGreedyStringArgs`. |

## Methods

| Name  | Signature                   | Description                                   |
| ----- | --------------------------- | --------------------------------------------- |
| `run` | `SayAlias run(String args)` | Sends `args` as a chat message to the server. |

## See Also

| Item                                                                                                                | Description                       |
| ------------------------------------------------------------------------------------------------------------------- | --------------------------------- |
| [BuiltinAliasWithGreedyStringArgs](../../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) | Parent class — greedy string args |
| [LocalSayAlias](../LocalSayAlias.java/LocalSayAlias.md)                                                             | Client-side message alias         |
| [SendCommandAlias](../SendCommandAlias.java/SendCommandAlias.md)                                                    | Sends commands to server          |
| [LogAlias](../LogAlias.java/LogAlias.md)                                                                            | Logs to mod logger                |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
