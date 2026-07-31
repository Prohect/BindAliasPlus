# putToAliasesWithoutArgs method (src/client/java/com/github/prohect/alias/BuiltinAliasWithoutArgs.java)

## Syntax

```java
public T putToAliasesWithoutArgs()
```

## Return value

Returns `this` for fluent builder chaining.

## Remarks

Keyless overload — registers `this` into `Alias.aliasesWithoutArgs` using `this.builtinAliasName` as the key. This is the standard registration for builtin no-arg aliases like `esc`, `toggleInventory`, `swapHand`, etc.

## See Also

| Item | Description |
|------|-------------|
| [putToAliasesWithoutArgs_notSuggested](putToAliasesWithoutArgs_notSuggested.md) | Non-suggested variant |
| [builtinAliasName](builtinAliasName.md) | The key used for registration |
| [AliasWithoutArgs.putToAliasesWithoutArgs](AliasWithoutArgs.java/putToAliasesWithoutArgs.md) | Interface default method with explicit key |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
