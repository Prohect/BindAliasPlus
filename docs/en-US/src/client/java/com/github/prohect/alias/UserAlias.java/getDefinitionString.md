# getDefinitionString method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public String getDefinitionString()
```

## Return value

The raw definition string used to construct this `UserAlias` — a space-separated alias chain like `"+attack slot\1 wait\5 -attack"`.

## Remarks

Returns the `args` field set at construction time. This is the original, unparsed definition string. It is never modified after construction.

Used by the `AliasAlias` builtin to retrieve the current definition of an existing alias (for display or modification purposes).

## See Also

| Item | Description |
|------|-------------|
| [AliasAlias](builtinAlias/AliasAlias.java/AliasAlias.md) | The `alias` builtin that reads this to show existing definitions |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
