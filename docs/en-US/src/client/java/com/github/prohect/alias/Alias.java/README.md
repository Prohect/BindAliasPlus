# Alias

Central interface of the alias execution engine. Declares global registration maps, parsing utilities, screen guards, and the `run` contract.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [aliasesWithArgs](aliasesWithArgs.md) | `HashMap<String, AliasWithArgs<?>>` | Suggested builtin aliases that accept arguments |
| [aliasesWithArgs_notSuggested](aliasesWithArgs_notSuggested.md) | `HashMap<String, AliasWithArgs<?>>` | Internal / non-suggested aliases that accept arguments |
| [aliasesWithoutArgs](aliasesWithoutArgs.md) | `HashMap<String, AliasWithoutArgs<?>>` | Suggested aliases that take no arguments (builtin + user) |
| [aliasesWithoutArgs_notSuggested](aliasesWithoutArgs_notSuggested.md) | `HashMap<String, AliasWithoutArgs<?>>` | Internal / non-suggested aliases without args |
| [aliasesWithoutArgs_fromBindCommand](aliasesWithoutArgs_fromBindCommand.md) | `HashMap<String, AliasWithoutArgs<?>>` | Aliases created by the `bind` command for key-binding lookup |
| [blackList4Screen](blackList4Screen.md) | `List<Alias<?>>` | Aliases suppressed when any screen is open (except release events) |
| [divider4AliasDefinition](divider4AliasDefinition.md) | `char` | Separator between alias invocations in a chain (space `' '`) |
| [divider4AliasArgs](divider4AliasArgs.md) | `char` | Separator between alias name and args (backslash `\`) |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `T run(String args)` | Core contract — execute the alias with given args; returns `this` for chaining |
| [addToScreenBlackList](addToScreenBlackList.md) | `default T addToScreenBlackList()` | Builder method — add this alias to the screen blacklist |
| [getOppositeDefinition](getOppositeDefinition.md) | `static String getOppositeDefinition(String)` | Flip `+`/`-` prefixes in a chain for lock/release |
| [getDefinitions](getDefinitions.md) | `static @NotNull ArrayList<String> getDefinitions(String)` | Split alias chain by space, respecting double-quoted blocks |
| [getDefinitionSplits](getDefinitionSplits.md) | `static @NotNull ArrayList<String> getDefinitionSplits(String)` | Split single definition by `\` into name + args |
| [getCurrentScreen](getCurrentScreen.md) | `static Screen getCurrentScreen()` | Accessor for `BindAliasClient.currentScreen` |
| [isUnderTextInputScreen](isUnderTextInputScreen.md) | `static boolean isUnderTextInputScreen()` | Chat / sign / book / command-block open |
| [isUnderAnyScreen](isUnderAnyScreen.md) | `static boolean isUnderAnyScreen()` | Any screen open |
| [isInContainerScreen](isInContainerScreen.md) | `static boolean isInContainerScreen()` | `AbstractContainerScreen` open |
| [isInInventoryScreen](isInInventoryScreen.md) | `static boolean isInInventoryScreen()` | Player inventory screen open |
| [isInCreativeInventoryScreen](isInCreativeInventoryScreen.md) | `static boolean isInCreativeInventoryScreen()` | Creative inventory screen open |

## See Also

| Item | Description |
|------|-------------|
| [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) | Sub-interface for aliases with args |
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | Sub-interface for aliases without args |
| [UserAlias](UserAlias.java/UserAlias.md) | The primary caller — parses chains and dispatches to aliases via these maps |
| [BindAliasClient](BindAliasClient.java/BindAliasClient.md) | Where aliases are registered into these maps |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
