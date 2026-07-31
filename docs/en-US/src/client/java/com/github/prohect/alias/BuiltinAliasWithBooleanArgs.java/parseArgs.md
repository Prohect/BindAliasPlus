# parseArgs method (src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java)

## Syntax

```java
public void parseArgs(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                               |
| ------ | -------- | ------------------------------------------------------------------------- |
| `args` | `String` | The argument string. `"0"` for key-up / false, `"1"` for key-down / true. |

## Remarks

Parses the argument string into the `flag` field.

Algorithm:

- `"0"` → sets `flag` to `false` (key-up / release).
- `"1"` → sets `flag` to `true` (key-down / press).
- Any other value → logs a warning with the builtin alias name and leaves `flag` at `false`.

The local variable `flag` shadows the field — the field is explicitly set at the
end of each branch.

## See Also

| Item                                                  | Description                           |
| ----------------------------------------------------- | ------------------------------------- |
| [flag](flag.md)                                       | The field this method writes to       |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | Uses `flag` to conditionally re-apply |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
