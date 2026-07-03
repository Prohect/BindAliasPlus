# aliasesWithArgs field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final java.util.HashMap<java.lang.String, com.github.prohect.alias.AliasWithArgs<?>> aliasesWithArgs
```

## Remarks

Primary registry for aliases that accept arguments.

Populated by `AliasWithArgs.putToAliasesWithArgs()` and
`BuiltinAliasWithArgs.putToAliasesWithArgs()` during mod initialization.
Looked up fourth (last) in `UserAlias.run()` and `UserAlias.runInternal()`
dispatch logic, after both `aliasesWithoutArgs_*` registries and
`aliasesWithArgs_notSuggested`.

Aliases in this map are included in command completion suggestions.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
