# args method (src/client/java/com/github/prohect/alias/AliasRecord.java)

## Syntax

```java
public java.lang.String args()
```

## Remarks

Record accessor for the arguments component of this alias entry.

For built-in aliases with args, this is the parsed parameter string (e.g., `"1"`
for key-down, `"0"` for key-up). For aliases without args and user aliases,
this is an empty string.

This value is passed directly to `Alias.run(String)` during dispatch. The value
is never null (annotated `@NotNull`).

## Return value

The arguments string, never null.

## See Also

| Item                              | Description                            |
| --------------------------------- | -------------------------------------- |
| [aliasName](aliasName.md)         | The paired accessor for the alias name |
| [Alias.run](../Alias.java/run.md) | Where this value is consumed           |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
