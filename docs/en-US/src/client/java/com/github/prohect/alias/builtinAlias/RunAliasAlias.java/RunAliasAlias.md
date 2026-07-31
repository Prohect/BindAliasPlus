# RunAliasAlias (src/client/java/com/github/prohect/alias/builtinAlias/RunAliasAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.RunAliasAlias extends com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs<com.github.prohect.alias.builtinAlias.RunAliasAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to execute a registered alias by name. Registered as `builtinRunAlias`.

**Purpose**: Allows triggering aliases programmatically (e.g., from autoload CFG on world join) without pressing a bound key. The argument is the alias name, optionally followed by backslash-separated arguments.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only).

**Key collaborators**: Looks up aliases in all four alias registries: `Alias.aliasesWithoutArgs`, `Alias.aliasesWithoutArgs_notSuggested`, `Alias.aliasesWithArgs`, and `Alias.aliasesWithArgs_notSuggested`.

## See Also

| Item                                                                                                                | Description  |
| ------------------------------------------------------------------------------------------------------------------- | ------------ |
| [BuiltinAliasWithGreedyStringArgs](../../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) | Parent class |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
