# Alias (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public interface com.github.prohect.alias.Alias<T extends com.github.prohect.alias.Alias<T>>
```

## Static Initializer

_None._

## Remarks

Core interface for all alias types in BindAliasPlus. Defines the static registries that
hold all registered aliases, the definition-parsing utilities, and screen-type helper
methods.

The type parameter `T` enables a fluent API where registration methods return `this`
with the correct subtype.

Five static registries are maintained:

- **Suggested** registries (`aliasesWithoutArgs`, `aliasesWithArgs`) — aliases that appear
  in command completion suggestions.
- **Not-suggested** registries (`aliasesWithoutArgs_notSuggested`,
  `aliasesWithArgs_notSuggested`) — aliases hidden from suggestions but still dispatchable.
- **Bind-command** registry (`aliasesWithoutArgs_fromBindCommand`) — aliases created by
  the `/alias bind` command, tracked separately.

The two static parsing methods (`getDefinitions`, `getDefinitionSplits`) form a
two-stage parser:

1. `getDefinitions` splits a raw args string by `divider4AliasDefinition` (space),
   respecting double-quoted blocks.
2. `getDefinitionSplits` splits each definition by `divider4AliasArgs` (backslash),
   separating the alias name from its arguments.

The `blackList4Screen` list controls which aliases are blocked when a screen is open.
Aliases add themselves via `addToScreenBlackList()`. The blacklist is checked in
`UserAlias.run()` and `UserAlias.runInternal()`, which allow key-up (`"0"`) to pass
through even when a screen is open.

## See Also

| Item                                                             | Description                                     |
| ---------------------------------------------------------------- | ----------------------------------------------- |
| [AliasWithArgs](../AliasWithArgs.java/AliasWithArgs.md)          | Sub-interface for aliases that accept arguments |
| [AliasWithoutArgs](../AliasWithoutArgs.java/AliasWithoutArgs.md) | Sub-interface for aliases without arguments     |
| [AliasRecord](../AliasRecord.java/AliasRecord.md)                | Record produced by parsing                      |
| [UserAlias](../UserAlias.java/UserAlias.md)                      | Consumes the registries and parsing utilities   |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
