# args field (src/client/java/com/github/prohect/alias/AliasRecord.java)

## Syntax

```java
public String args()
```

## Return value

The arguments string for this alias invocation, or `""` for no-arg aliases. Already split by `Alias.divider4AliasArgs` — does not include the alias name or backslash dividers.

## Remarks

This is a record component accessor. For a definition like `swapSlot\1\c2`, the `aliasName` is `"swapSlot"` and `args` is `"1\c2"` (the raw remaining tokens joined by `\`). For a no-arg alias like `esc`, `args` is `""`.

Only `AliasWithArgs` instances use this value. When an `AliasWithoutArgs` is looked up, `UserAlias.run()` always passes `""`.

When `UserAlias` reconstructs deferred chains after a `WaitAlias`, it re-joins `aliasName` and `args` with the `Alias.divider4AliasArgs` backslash.

## See Also

| Item | Description |
|------|-------------|
| [aliasName](aliasName.md) | The corresponding alias name |
| [AliasRecord](AliasRecord.md) | The enclosing record type |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
