# BuiltinAliasWithStringArgs (src/client/java/com/github/prohect/alias/BuiltinAliasWithStringArgs.java)

## Syntax

```java
public abstract class BuiltinAliasWithStringArgs<T extends BuiltinAliasWithStringArgs<T>> extends BuiltinAliasWithArgs<T>
```

Abstract base class for builtin aliases that receive a free-form string as their argument. Overrides the default alias-definition divider with `;` (semicolon) instead of `' '` (space), because these aliases' string arguments may themselves contain spaces.

## Remarks

The `divider4AliasDefinition` field is redeclared here as `';'`. This affects how `UserAlias` reconstructs alias chains when deferring via `WaitAlias` — aliases like `alias`, `bind`, and `unbind` use this semicolon divider in their definition strings so that their space-containing args don't break the chain parsing.

**Concrete subclasses**: `SayAlias` (`say\"text"`), `BindAlias` (`bind\key\alias`), `SendCommandAlias` (`sendCommand\cmd`), `LogAlias` (`log\text`), `LocalSayAlias` (`localSay\text`), `ReapplyAlias` (`reapply\action`), `ReloadCFGAlias` (`reloadCFG`), `AliasAlias` (`alias\name;definition`), `RunAlias` (`runAlias\name`), `ApplyRecipeAlias` (`applyRecipe\query`).

**No `parseArgs` override**: Unlike the numeric/boolean subclasses, `BuiltinAliasWithStringArgs` does not provide a `parseArgs` method. Each concrete subclass handles its arg parsing inline in `run()`.

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class — registration and builtinAliasName |
| [Alias.divider4AliasDefinition](Alias.java/divider4AliasDefinition.md) | The default divider (`' '`) that this subclass overrides |
| [builtinAlias](builtinAlias/README.md) | Concrete string-arg implementations |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
