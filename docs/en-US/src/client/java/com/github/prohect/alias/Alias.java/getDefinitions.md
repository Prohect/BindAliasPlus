# getDefinitions method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static java.util.ArrayList<java.lang.String> getDefinitions(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                     |
| ------ | -------- | ----------------------------------------------------------------------------------------------- |
| `args` | `String` | Raw definition string. Multiple definitions are separated by `divider4AliasDefinition` (space). |

## Remarks

Splits a raw args string into a list of individual alias definitions.

Algorithm:

1. Iterate character by character.
2. Track whether the current position is inside double quotes (`coveredByDoubleQuotes`).
3. When encountering `divider4AliasDefinition` (space):
   - If inside quotes: append the space to the current definition literally.
   - Otherwise: submit the accumulated definition and start a new one.
4. Consecutive dividers are collapsed (via `lastStepSubmit` flag) to avoid empty definitions.
5. The final accumulated definition (if non-empty) is added at the end.

This is the first stage of the two-stage parser. The output is consumed by
`UserAlias.decodeArgs2Alias()` and `getOppositeDefinition()`.

## Return value

A non-null list of definition strings. May be empty if the input is blank.

## See Also

| Item                                                      | Description                                    |
| --------------------------------------------------------- | ---------------------------------------------- |
| [getDefinitionSplits](getDefinitionSplits.md)             | Second-stage parser for individual definitions |
| [divider4AliasDefinition](divider4AliasDefinition.md)     | The delimiter used by this method              |
| [decodeArgs2Alias](../UserAlias.java/decodeArgs2Alias.md) | Consumes the output                            |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
