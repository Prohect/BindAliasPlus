# getDefinitions method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static @NotNull ArrayList<String> getDefinitions(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | An alias chain string — space-separated alias invocations |

## Return value

A list of individual alias definition strings, split by `divider4AliasDefinition` (space `' '`). Returns an empty list for blank input.

## Remarks

This is the first parsing step when executing an alias chain. The algorithm walks through the string character by character:

1. **Double-quote handling**: When a `"` is encountered, a `coveredByDoubleQuotes` flag toggles. While inside quotes, spaces are treated as literal characters — not dividers.
2. **Divider skipping**: Consecutive dividers are collapsed — a `lastStepSubmit` flag prevents submitting empty strings on repeated divider characters.
3. **Terminal fragment**: After the loop, any remaining non-empty `currentDefinition` is added as the final item.

**Examples**:

| Input | Output |
|-------|--------|
| `"+attack slot\1"` | `["+attack", "slot\1"]` |
| `"say\"hello world\" wait\5"` | `["say\"hello world\"", "wait\5"]` |
| `"esc  toggleInventory"` | `["esc", "toggleInventory"]` (double space collapsed) |

Called by `UserAlias.decodeArgs2Alias()`, `getOppositeDefinition()`, `BindAliasClient.loadCFG()`, and the `bind`/`alias` commands.

## See Also

| Item | Description |
|------|-------------|
| [getDefinitionSplits](getDefinitionSplits.md) | Second parsing step — splits a definition by `\` into name + args |
| [divider4AliasDefinition](divider4AliasDefinition.md) | The divider character used (space `' '`) |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
