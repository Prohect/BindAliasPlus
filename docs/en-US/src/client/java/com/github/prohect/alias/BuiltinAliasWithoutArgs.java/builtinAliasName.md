# builtinAliasName field (src/client/java/com/github/prohect/alias/BuiltinAliasWithoutArgs.java)

## Syntax

```java
public final java.lang.String builtinAliasName
```

## Remarks

The alias name set at construction via the constructor parameter.

Used as the key when registering in `Alias.aliasesWithoutArgs` or
`Alias.aliasesWithoutArgs_notSuggested`. Set once at construction, never null
(annotated `@NotNull`), never changes.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
