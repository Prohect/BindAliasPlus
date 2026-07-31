# getDefinitionString method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public java.lang.String getDefinitionString()
```

## Remarks

Returns the raw definition string that was passed to the constructor.

This string contains the space-delimited alias definitions that define this
alias chain. For example: `"attack\1 use\1"`.

## Return value

The raw definition string used to create this alias.

## See Also

| Item                                    | Description                                   |
| --------------------------------------- | --------------------------------------------- |
| [decodeArgs2Alias](decodeArgs2Alias.md) | Parses this string into `AliasRecord` entries |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
