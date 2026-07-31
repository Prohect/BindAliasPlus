# getDefinitionSplits method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static java.util.ArrayList<java.lang.String> getDefinitionSplits(java.lang.String)
```

## Parameters

| Name         | Type     | Description                                                                                                   |
| ------------ | -------- | ------------------------------------------------------------------------------------------------------------- |
| `definition` | `String` | A single alias definition. The alias name and its arguments are separated by `divider4AliasArgs` (backslash). |

## Remarks

Splits a single definition into tokens using `divider4AliasArgs` as the delimiter.

Algorithm:

1. Iterate character by character.
2. Track whether the current position is inside double quotes (`coveredByDoubleQuotes`).
3. When encountering `divider4AliasArgs` (backslash):
   - If inside quotes: append the backslash to the current token literally.
   - Otherwise: submit the accumulated token and start a new one.
4. Consecutive dividers are collapsed (via `lastStepSubmit` flag).
5. The final accumulated token (if non-empty) is added at the end.
6. Blank tokens are removed with `removeIf(String::isBlank)`.

This is the second stage of the two-stage parser. The first non-blank token is the
alias name; subsequent tokens form the arguments string.

## Return value

A non-null list of non-blank tokens. For a definition like `"attack\1"`, returns `["attack", "1"]`.

## See Also

| Item                                                      | Description                                                |
| --------------------------------------------------------- | ---------------------------------------------------------- |
| [getDefinitions](getDefinitions.md)                       | First-stage parser that produces the input for this method |
| [divider4AliasArgs](divider4AliasArgs.md)                 | The delimiter used by this method                          |
| [decodeArgs2Alias](../UserAlias.java/decodeArgs2Alias.md) | Consumes the output                                        |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
