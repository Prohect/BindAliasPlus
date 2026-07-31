# getDefinitionSplits method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static @NotNull ArrayList<String> getDefinitionSplits(String definition)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `definition` | `String` | A single alias definition — e.g. `"slot\3"` or `"swapSlot\1\c2"` |

## Return value

A list of tokens split by `divider4AliasArgs` (backslash `\`). The first token is the alias name; subsequent tokens are arguments. Empty/blank tokens (from trailing backslashes) are removed via `removeIf(String::isBlank)`.

## Remarks

This is the second parsing step after `getDefinitions()`. Takes a single definition and splits it into its name and arguments.

The algorithm mirrors `getDefinitions()`: it walks the string character by character, splitting on backslash while respecting double-quoted blocks. Unlike `getDefinitions()`, it additionally **filters out blank tokens** after splitting — this prevents trailing backslashes from producing empty strings.

**Examples**:

| Input | Output |
|-------|--------|
| `"slot\3"` | `["slot", "3"]` |
| `"swapSlot\1\c2"` | `["swapSlot", "1", "c2"]` |
| `"esc"` | `["esc"]` |
| `"say\"hello world\""` | `["say", "hello world"]` (quotes stripped) |

Called by `UserAlias.decodeArgs2Alias()` to extract alias name and args from each definition token. Also used by `BindAliasClient.loadCFG()` when parsing `alias` and `var` lines.

## See Also

| Item | Description |
|------|-------------|
| [getDefinitions](getDefinitions.md) | First parsing step — splits chains by space |
| [divider4AliasArgs](divider4AliasArgs.md) | The divider character used (backslash `\`) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
