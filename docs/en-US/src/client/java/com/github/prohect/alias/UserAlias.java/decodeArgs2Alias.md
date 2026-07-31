# decodeArgs2Alias method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
private void decodeArgs2Alias(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | The raw definition string — a space-separated alias chain (e.g. `"+attack slot\1 wait\5 -attack"`) |

## Remarks

Parses the raw definition string into an `ArrayDeque<AliasRecord>` stored in `this.aliases`. This is the first step of alias-chain execution, called at the top of both `run()` and `runInternal()`.

**Algorithm**:

1. **Split by `divider4AliasDefinition` (space)**: Calls `Alias.getDefinitions(args)` to get individual definitions.
2. **For each definition**: Calls `Alias.getDefinitionSplits(definition)` to split into name + arg tokens.
3. **Count non-blank tokens**: Filters out blank splits (from trailing backslashes).
4. **Build `AliasRecord`**:
   - **Count == 0**: No tokens — skip.
   - **Count == 1**: Single token treated as alias name with empty args (`AliasRecord("", name)`).
   - **Count >= 2**: First non-blank token is the alias name; remaining tokens are joined with `\` as the args string (`AliasRecord(argsStr, name)`).

**Examples**:

| Definition string | Result |
|-------------------|--------|
| `"esc"` | `AliasRecord("", "esc")` |
| `"slot\3"` | `AliasRecord("3", "slot")` |
| `"swapSlot\1\c2"` | `AliasRecord("1\c2", "swapSlot")` |
| `"say\"hello world\""` | `AliasRecord("hello world", "say")` |

## See Also

| Item | Description |
|------|-------------|
| [Alias.getDefinitions](Alias.java/getDefinitions.md) | Step 1 — split chain by space |
| [Alias.getDefinitionSplits](Alias.java/getDefinitionSplits.md) | Step 2 — split definition by `\` |
| [AliasRecord](AliasRecord.java/AliasRecord.md) | The record type populated by this method |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
