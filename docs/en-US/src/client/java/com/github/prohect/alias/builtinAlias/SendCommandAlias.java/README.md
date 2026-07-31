# SendCommandAlias

## Fields

| Name                                    | Type     | Description                                                                                 |
| --------------------------------------- | -------- | ------------------------------------------------------------------------------------------- |
| _(inherited)_ `builtinAliasName`        | `String` | The alias name `"sendCommand"`. Inherited from `BuiltinAliasWithArgs`.                      |
| _(inherited)_ `divider4AliasDefinition` | `char`   | The `';'` divider for alias definitions. Inherited from `BuiltinAliasWithGreedyStringArgs`. |

## Methods

| Name  | Signature                           | Description                                            |
| ----- | ----------------------------------- | ------------------------------------------------------ |
| `run` | `SendCommandAlias run(String args)` | Sends `args` as a server command (without `/` prefix). |

## See Also

| Item                                                                                                                | Description                       |
| ------------------------------------------------------------------------------------------------------------------- | --------------------------------- |
| [BuiltinAliasWithGreedyStringArgs](../../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) | Parent class — greedy string args |
| [SayAlias](../SayAlias.java/SayAlias.md)                                                                            | Sends chat to server              |
| [LocalSayAlias](../LocalSayAlias.java/LocalSayAlias.md)                                                             | Client-side message alias         |
| [LogAlias](../LogAlias.java/LogAlias.md)                                                                            | Logs to mod logger                |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
