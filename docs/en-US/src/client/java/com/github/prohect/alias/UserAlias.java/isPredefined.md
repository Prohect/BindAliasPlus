# isPredefined method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public boolean isPredefined()
```

## Return value

`true` if this alias is protected and cannot be overwritten by a new `alias` definition. `false` otherwise.

## Remarks

Predefined aliases are those created with the 3-arg constructor `UserAlias(args, fromCFG, predefined=true)`. They are typically set up by the mod during initialization to provide default behavior that users should not accidentally override.

The `AliasAlias` builtin checks this flag before overwriting an existing alias — if `isPredefined()` returns `true`, the overwrite is rejected.

## See Also

| Item | Description |
|------|-------------|
| [isFromCFG](isFromCFG.md) | Related tracking flag |
| [AliasAlias](builtinAlias/AliasAlias.java/AliasAlias.md) | Checks this flag before overwriting |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
