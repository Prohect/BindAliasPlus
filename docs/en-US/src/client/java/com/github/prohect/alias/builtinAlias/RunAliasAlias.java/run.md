# run method (src/client/java/com/github/prohect/alias/builtinAlias/RunAliasAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                |
| ------ | -------- | ------------------------------------------------------------------------------------------ |
| `args` | `String` | The alias name, optionally followed by `\` and extra arguments (e.g. `myAlias\arg1\arg2`). |

## Remarks

Looks up an alias by name in all four alias registries and executes it with any extra arguments.

**Algorithm**:

1. If `args` is null/blank, log warning and return.
2. Split at the first `Alias.divider4AliasArgs` occurrence to separate alias name from extra args.
3. Search in order: `aliasesWithoutArgs`, `aliasesWithoutArgs_notSuggested`, `aliasesWithArgs`, `aliasesWithArgs_notSuggested`.
4. If found, call `alias.run(extraArgs)`.
5. If not found in any registry, log a warning.

**Side effects**: Executes the target alias with the provided arguments. The effects depend on the target alias.

**Callers**: Invoked by the alias dispatch system and from autoload CFG on world join.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
