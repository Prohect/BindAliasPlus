# aliasName method (src/client/java/com/github/prohect/alias/AliasRecord.java)

## Syntax

```java
public java.lang.String aliasName()
```

## Remarks

Record accessor for the alias name component of this entry.

This is the key used to look up the alias implementation in the static registries
(`Alias.aliasesWithoutArgs`, `Alias.aliasesWithArgs`, etc.) during dispatch.

The value is never null (annotated `@NotNull`).

## Return value

The alias name string, never null.

## See Also

| Item                            | Description                                             |
| ------------------------------- | ------------------------------------------------------- |
| [args](args.md)                 | The paired accessor for the arguments                   |
| [Alias](../Alias.java/Alias.md) | Interface holding the registries looked up by this name |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
