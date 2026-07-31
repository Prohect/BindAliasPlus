# putToAliasesWithArgs method (src/client/java/com/github/prohect/alias/BuiltinAliasWithArgs.java)

## Syntax

```java
public T putToAliasesWithArgs()
```

## Return value

Returns `this` for fluent builder chaining.

## Remarks

Keyless overload — registers `this` into `Alias.aliasesWithArgs` using `this.builtinAliasName` as the key. Equivalent to calling the parent interface's `AliasWithArgs.putToAliasesWithArgs(this.builtinAliasName)`.

This is the preferred registration method for concrete builtin aliases because the name is known at construction time and doesn't need to be repeated.

## See Also

| Item | Description |
|------|-------------|
| [putToAliasesWithArgs_notSuggested](putToAliasesWithArgs_notSuggested.md) | Non-suggested variant |
| [builtinAliasName](builtinAliasName.md) | The key used for registration |
| [AliasWithArgs.putToAliasesWithArgs](AliasWithArgs.java/putToAliasesWithArgs.md) | Interface default method with explicit key |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
