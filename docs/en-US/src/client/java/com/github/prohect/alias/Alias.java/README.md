# Alias

## Fields

| Name                                 | Type                                   | Description                                                                                         |
| ------------------------------------ | -------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `blackList4Screen`                   | `List<Alias<?>>`                       | Aliases restricted when any screen is open. Checked by `UserAlias.run()` / `runInternal()`.         |
| `aliasesWithoutArgs`                 | `HashMap<String, AliasWithoutArgs<?>>` | Primary registry for aliases that take no arguments (suggested in command completion).              |
| `aliasesWithoutArgs_notSuggested`    | `HashMap<String, AliasWithoutArgs<?>>` | Registry for aliases without args that are not suggested in command completion.                     |
| `aliasesWithoutArgs_fromBindCommand` | `HashMap<String, AliasWithoutArgs<?>>` | Registry for aliases created via the `/alias bind` command.                                         |
| `aliasesWithArgs`                    | `HashMap<String, AliasWithArgs<?>>`    | Primary registry for aliases that take arguments (suggested in command completion).                 |
| `aliasesWithArgs_notSuggested`       | `HashMap<String, AliasWithArgs<?>>`    | Registry for aliases with args that are not suggested in command completion.                        |
| `divider4AliasDefinition`            | `char`                                 | Delimiter between alias definitions (space `' '`). Used by `getDefinitions()`.                      |
| `divider4AliasArgs`                  | `char`                                 | Delimiter between alias name and its arguments (backslash `'\\'`). Used by `getDefinitionSplits()`. |

## Methods

| Name                          | Signature                                              | Description                                                                        |
| ----------------------------- | ------------------------------------------------------ | ---------------------------------------------------------------------------------- |
| `getOppositeDefinition`       | `static String getOppositeDefinition(String)`          | Flips `+`/`-` prefixes across all definitions in a string.                         |
| `getDefinitions`              | `static ArrayList<String> getDefinitions(String)`      | Splits args into individual alias definitions using `divider4AliasDefinition`.     |
| `getDefinitionSplits`         | `static ArrayList<String> getDefinitionSplits(String)` | Splits a single definition into tokens using `divider4AliasArgs`.                  |
| `run`                         | `T run(String)`                                        | Abstract: executes the alias with the given arguments string.                      |
| `addToScreenBlackList`        | `default T addToScreenBlackList()`                     | Registers this alias in `blackList4Screen`.                                        |
| `getCurrentScreen`            | `static Screen getCurrentScreen()`                     | Returns the current screen from `BindAliasClient.currentScreen`.               |
| `isUnderTextInputScreen`      | `static boolean isUnderTextInputScreen()`              | Checks if the current screen accepts text input (chat, command block, sign, book). |
| `isUnderAnyScreen`            | `static boolean isUnderAnyScreen()`                    | Checks if any screen is currently open.                                            |
| `isInContainerScreen`         | `static boolean isInContainerScreen()`                 | Checks if the current screen is an `AbstractContainerScreen`.                      |
| `isInInventoryScreen`         | `static boolean isInInventoryScreen()`                 | Checks if the current screen is the player inventory.                              |
| `isInCreativeInventoryScreen` | `static boolean isInCreativeInventoryScreen()`         | Checks if the current screen is the creative inventory.                            |

## See Also

| Item                                                             | Description                                                       |
| ---------------------------------------------------------------- | ----------------------------------------------------------------- |
| [AliasRecord](../AliasRecord.java/AliasRecord.md)                | Record type for parsed alias definitions                          |
| [AliasWithArgs](../AliasWithArgs.java/AliasWithArgs.md)          | Sub-interface for aliases with arguments                          |
| [AliasWithoutArgs](../AliasWithoutArgs.java/AliasWithoutArgs.md) | Sub-interface for aliases without arguments                       |
| [UserAlias](../UserAlias.java/UserAlias.md)                      | User-defined alias chain that dispatches against these registries |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
