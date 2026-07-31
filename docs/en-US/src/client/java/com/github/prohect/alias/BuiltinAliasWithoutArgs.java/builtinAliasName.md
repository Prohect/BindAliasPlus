# builtinAliasName field (src/client/java/com/github/prohect/alias/BuiltinAliasWithoutArgs.java)

## Syntax

```java
public final @NotNull String builtinAliasName
```

The name used for registration and lookup. Set by the constructor and never changes. Annotated `@NotNull`.

## Remarks

Same role as `BuiltinAliasWithArgs.builtinAliasName` — used by `putToAliasesWithoutArgs()` (no-arg overload) as the registration key.

For example, `EscAlias` passes `"esc"`, so `new EscAlias().putToAliasesWithoutArgs()` registers it as `aliasesWithoutArgs["esc"]`.

## See Also

| Item | Description |
|------|-------------|
| [putToAliasesWithoutArgs](putToAliasesWithoutArgs.md) | Uses this field as the registration key |
| [BuiltinAliasWithArgs.builtinAliasName](BuiltinAliasWithArgs.java/builtinAliasName.md) | Same field in the with-args base class |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
