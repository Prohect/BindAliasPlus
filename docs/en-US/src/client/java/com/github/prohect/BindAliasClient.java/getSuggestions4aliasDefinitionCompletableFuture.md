# getSuggestions4aliasDefinitionCompletableFuture method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
private static java.util.concurrent.CompletableFuture<com.mojang.brigadier.suggestion.Suggestions> getSuggestions4aliasDefinitionCompletableFuture(com.mojang.brigadier.suggestion.SuggestionsBuilder)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `builder` | `SuggestionsBuilder` | Brigadier suggestions builder with the current input so far |

## Return value

A `CompletableFuture<Suggestions>` for tab-completion in command argument fields.

## Remarks

Provides context-aware tab-completion suggestions for alias definition strings (used in `/alias`, `/bind`, `/runAlias` commands). The behavior depends on the current input position:

1. **Blank input**: Suggests all known `aliasesWithoutArgs` and `aliasesWithArgs` names.

2. **Inside an arg value** (cursor is after a `\` within an alias invocation and not in a new alias name position):
   - For `+lockKey` / `-lockKey`: suggests lockable action names from `LockAlias.SUPPORTED_ACTIONS` plus existing `UserAlias` names.
   - For `reapply`: suggests supported actions from `ReapplyAlias.SUPPORTED_ACTIONS`.
   - For aliases accepting numeric args (`BuiltinAliasWithIntegerArgs`, `BuiltinAliasWithDoubleArgs`, `SwapSlotAlias`): suggests matching variable names from `GENERAL_VARIABLES` and/or `CONTAINER_SLOT_VARIABLES`, filtering by numeric type compatibility.

3. **In a new alias name position** (cursor is after a space separator): suggests all known alias names filtered by the partial token.

## See Also

| Item | Description |
|------|-------------|
| [Alias](../alias/Alias.java/Alias.md) | Registration maps providing the suggestion lists |
| [VarAlias](../alias/builtinAlias/VarAlias.java/VarAlias.md) | Variable maps for numeric arg suggestions |
| [LockAlias](../alias/builtinAlias/LockAlias.java/LockAlias.md) | Lockable action list for `+lockKey` suggestions |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
