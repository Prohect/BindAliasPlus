# builtinAliasName field (src/client/java/com/github/prohect/alias/BuiltinAliasWithArgs.java)

## Syntax

```java
public final java.lang.String builtinAliasName
```

## Remarks

The alias name set at construction via the constructor parameter.

Used as:

- The key when registering in `Alias.aliasesWithArgs` or `Alias.aliasesWithArgs_notSuggested`.
- A log prefix in subclasses that emit warnings (e.g., `"[" + this.builtinAliasName + "]Invalid arguments"`).

Set once at construction, never null (annotated `@NotNull`), never changes.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
