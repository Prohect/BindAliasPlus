# putToAliasesWithoutArgs_notSuggested method (src/client/java/com/github/prohect/alias/BuiltinAliasWithoutArgs.java)

## Syntax

```java
public T putToAliasesWithoutArgs_notSuggested()
```

## Return value

Returns `this` for fluent builder chaining.

## Remarks

Keyless overload — registers `this` into `Alias.aliasesWithoutArgs_notSuggested` using `this.builtinAliasName` as the key. Aliases registered here are executable but hidden from user suggestions. Used for internal / view-switching aliases like `FPS`, `TPS`, `TPS2`, and lock wrappers.

## See Also

| Item | Description |
|------|-------------|
| [putToAliasesWithoutArgs](putToAliasesWithoutArgs.md) | Suggested variant |
| [builtinAliasName](builtinAliasName.md) | The key used for registration |
| [aliasesWithoutArgs_notSuggested](Alias.java/aliasesWithoutArgs_notSuggested.md) | The target map |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
