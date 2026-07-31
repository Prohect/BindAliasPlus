# putToAliasesWithArgs_notSuggested method (src/client/java/com/github/prohect/alias/BuiltinAliasWithArgs.java)

## Syntax

```java
public T putToAliasesWithArgs_notSuggested()
```

## Return value

Returns `this` for fluent builder chaining.

## Remarks

Keyless overload — registers `this` into `Alias.aliasesWithArgs_notSuggested` using `this.builtinAliasName` as the key. Aliases registered here are executable but hidden from user suggestions.

## See Also

| Item | Description |
|------|-------------|
| [putToAliasesWithArgs](putToAliasesWithArgs.md) | Suggested variant |
| [builtinAliasName](builtinAliasName.md) | The key used for registration |
| [aliasesWithArgs_notSuggested](Alias.java/aliasesWithArgs_notSuggested.md) | The target map |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
