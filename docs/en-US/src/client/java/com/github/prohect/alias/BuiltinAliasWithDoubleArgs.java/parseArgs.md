# parseArgs method (src/client/java/com/github/prohect/alias/BuiltinAliasWithDoubleArgs.java)

## Syntax

```java
public void parseArgs(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                               |
| ------ | -------- | ----------------------------------------------------------------------------------------- |
| `args` | `String` | The argument string. Can be a numeric literal or a `VarAlias` variable name (e.g., `$x`). |

## Remarks

Parses the argument string into the `flag` field as a `double`.

Algorithm:

1. Try `VarAlias.resolveDouble(args)` to resolve a variable reference.
2. If resolved (non-null), use the resolved value.
3. Otherwise, attempt `Double.parseDouble(args)` for a literal number.
4. On `NumberFormatException`, log the error and leave `flag` at `0.0`.

## See Also

| Item                                                                  | Description                           |
| --------------------------------------------------------------------- | ------------------------------------- |
| [flag](flag.md)                                                       | The field this method writes to       |
| [VarAlias.resolveDouble](builtinAlias/VarAlias.java/resolveDouble.md) | Variable resolution for double values |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
