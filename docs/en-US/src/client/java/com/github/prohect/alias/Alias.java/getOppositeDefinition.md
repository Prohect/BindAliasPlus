# getOppositeDefinition method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static java.lang.String getOppositeDefinition(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                          |
| ------ | -------- | ------------------------------------------------------------------------------------ |
| `args` | `String` | A space-delimited definition string where each definition may start with `+` or `-`. |

## Remarks

Flips the `+`/`-` prefix of each definition in the input string.

Algorithm:

1. Call `getDefinitions(args)` to split into individual definitions.
2. For each definition starting with `+`, replace with `-` (and vice versa).
3. Reassemble into a space-delimited string.

Definitions that do not start with `+` or `-` are silently omitted.

Used to compute the "opposite" (toggle-off) alias string from a toggle-on definition.

## Return value

A space-delimited string with `+`/`-` prefixes flipped. May be empty if no prefixed definitions were found.

## See Also

| Item                                | Description                                         |
| ----------------------------------- | --------------------------------------------------- |
| [getDefinitions](getDefinitions.md) | Used to split the input into individual definitions |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
