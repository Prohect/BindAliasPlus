# aliasesWithoutArgs_notSuggested field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final java.util.HashMap<java.lang.String, com.github.prohect.alias.AliasWithoutArgs<?>> aliasesWithoutArgs_notSuggested
```

## Remarks

Registry for aliases without args that are hidden from command completion suggestions.

Populated by `AliasWithoutArgs.putToAliasesWithoutArgs_notSuggested()` and
`BuiltinAliasWithoutArgs.putToAliasesWithoutArgs_notSuggested()`.
Looked up second in `UserAlias.run()` and `UserAlias.runInternal()` dispatch,
after the suggested registry but before `aliasesWithArgs_*` registries.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
