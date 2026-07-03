# aliasesWithArgs_notSuggested field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final java.util.HashMap<java.lang.String, com.github.prohect.alias.AliasWithArgs<?>> aliasesWithArgs_notSuggested
```

## Remarks

Registry for aliases with args that are hidden from command completion suggestions.

Populated by `AliasWithArgs.putToAliasesWithArgs_notSuggested()` and
`BuiltinAliasWithArgs.putToAliasesWithArgs_notSuggested()`.
Looked up third in `UserAlias.run()` and `UserAlias.runInternal()` dispatch,
after both `aliasesWithoutArgs_*` registries but before the suggested
`aliasesWithArgs` registry.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
