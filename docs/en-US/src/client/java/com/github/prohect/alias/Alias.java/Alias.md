# Alias (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public interface Alias<T extends Alias<T>>
```

Central abstraction of the alias execution engine. Every command — builtin or user-defined — is an object implementing this interface. The interface declares the global registration maps, alias-chain parsing utilities, screen-type helpers, and the `run` contract all aliases must fulfill.

All concrete alias types implement either `AliasWithArgs` or `AliasWithoutArgs`, both of which extend `Alias`.

## Remarks

The interface serves three roles:

1. **Registration hub**: Static maps (`aliasesWithArgs`, `aliasesWithoutArgs`, `aliasesWithoutArgs_fromBindCommand`, plus `_notSuggested` variants) store every alias by name. Builtin aliases register themselves during `BindAliasClient.onInitializeClient()`. User aliases are registered via the `alias` command or CFG auto-load. Lookup order in `UserAlias.run()`: `aliasesWithoutArgs` → `aliasesWithoutArgs_notSuggested` → `aliasesWithArgs_notSuggested` → `aliasesWithArgs`.

2. **Parsing utilities**: `getDefinitions(...)` splits an alias chain string by `divider4AliasDefinition` (space `' '`), respecting double-quoted blocks. `getDefinitionSplits(...)` splits a single definition by `divider4AliasArgs` (backslash `\`), also quote-aware. `getOppositeDefinition(...)` flips `+` / `-` prefixes for switch aliases.

3. **Screen guards**: Static helpers query `BindAliasClient.currentScreen` to decide whether certain aliases should execute. `isUnderTextInputScreen()` (chat, sign, book, command block) blocks key-input aliases from firing while the user is typing. `isUnderAnyScreen()` blocks blacklisted aliases from firing when any screen is open, except for release (`"0"`) events.

The generic type parameter `<T extends Alias<T>>` enables fluent builder-style registration (e.g. `new SomeAlias().putToAliasesWithArgs().addToScreenBlackList()`).

## See Also

| Item | Description |
|------|-------------|
| [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) | Marker sub-interface for aliases that accept args |
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | Marker sub-interface for aliases triggered by name only |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Abstract base for all args-accepting builtin aliases |
| [UserAlias](UserAlias.java/UserAlias.md) | User-defined alias chains; the primary executor |
| [builtinAlias](builtinAlias/README.md) | Concrete builtin alias implementations |
| [BindAliasClient](BindAliasClient.java/BindAliasClient.md) | Where aliases are registered at client init |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
