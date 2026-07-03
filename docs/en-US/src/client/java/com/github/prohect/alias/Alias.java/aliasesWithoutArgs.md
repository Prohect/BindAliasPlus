# aliasesWithoutArgs field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final java.util.HashMap<java.lang.String, com.github.prohect.alias.AliasWithoutArgs<?>> aliasesWithoutArgs
```

## Remarks

Primary registry for aliases that take no arguments.

Populated by `AliasWithoutArgs.putToAliasesWithoutArgs()` and
`BuiltinAliasWithoutArgs.putToAliasesWithoutArgs()` during mod initialization.
Looked up first in `UserAlias.run()` and `UserAlias.runInternal()` dispatch logic
(before falling back to the `_notSuggested` variants).

Aliases in this map are included in command completion suggestions.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
