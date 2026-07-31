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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
