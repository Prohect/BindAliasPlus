# getSuggestions4aliasDefinitionCompletableFuture method (src/client/java/com/github/prohect/BindAliasPlusClient.java)

## Syntax

```java
private static java.util.concurrent.CompletableFuture<com.mojang.brigadier.suggestion.Suggestions> getSuggestions4aliasDefinitionCompletableFuture(com.mojang.brigadier.suggestion.SuggestionsBuilder)
```

## Parameters

| Name      | Type                 | Description                                                                       |
| --------- | -------------------- | --------------------------------------------------------------------------------- |
| `builder` | `SuggestionsBuilder` | Brigadier suggestion builder; `getRemaining()` returns the current partial input. |

## Remarks

Provides tab-completion suggestions for alias definition strings in the `/alias`
and `/bind` commands. Static — no instance state needed.

Algorithm (three cases based on remaining input `soFar`):

### Case 1: `soFar` is blank

Suggest all registered alias names from `aliasesWithoutArgs` and `aliasesWithArgs`.

### Case 2: Inside an arg value (when `divider4AliasDefinition` position < `divider4AliasArgs` position)

Suggests values for the specific alias being edited:

- **`+lockKey` / `-lockKey`**: Suggest from `LockAlias.SUPPORTED_ACTIONS` plus `UserAlias` names.
- **`reapply`**: Suggest from `ReapplyAlias.SUPPORTED_ACTIONS`.
- **Numeric-arg aliases**: If the alias is a `BuiltinAliasWithIntegerArgs` or
  `BuiltinAliasWithDoubleArgs`, suggest variable names from `VarAlias.VARIABLES`
  (filtering by matching prefix and numeric type compatibility).

### Case 3: Typing an alias name (after the last `divider4AliasDefinition`)

Suggest alias names matching the current token, annotated as "alias without args"
or "alias with args".

In all cases, the builder offset is adjusted so suggestions replace the correct
portion of the input.

## See Also

| Item                                                                                     | Description                                   |
| ---------------------------------------------------------------------------------------- | --------------------------------------------- |
| [Alias.divider4AliasArgs](../alias/Alias.java/divider4AliasArgs.md)                      | Delimiter separating alias name from its args |
| [Alias.divider4AliasDefinition](../alias/Alias.java/divider4AliasDefinition.md)          | Delimiter between alias definitions           |
| [VarAlias.VARIABLES](../alias/builtinAlias/VarAlias.java/VARIABLES.md)                   | Variable names suggested for numeric args     |
| [LockAlias.SUPPORTED_ACTIONS](../alias/builtinAlias/LockAlias.java/SUPPORTED_ACTIONS.md) | Suggested actions for lock aliases            |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
