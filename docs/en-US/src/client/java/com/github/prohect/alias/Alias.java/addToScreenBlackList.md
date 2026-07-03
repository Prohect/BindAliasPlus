# addToScreenBlackList method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public default T addToScreenBlackList()
```

## Remarks

Registers this alias instance in the `blackList4Screen` list.

Aliases on the blacklist are suppressed when a screen is open (except for key-up
events, which are allowed so keys can be released). Typically called during
alias registration in mod initialization.

Returns `this` cast to `T` for fluent chaining.

## See Also

| Item                                      | Description                                    |
| ----------------------------------------- | ---------------------------------------------- |
| [blackList4Screen](blackList4Screen.md)   | The list this method adds to                   |
| [UserAlias.run](../UserAlias.java/run.md) | Where the blacklist is checked during dispatch |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
