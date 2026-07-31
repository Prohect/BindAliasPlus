# BuiltinAliasWithStringArgs

Abstract base for builtin aliases receiving a free-form string argument. Overrides the alias-definition divider to `;` so space-containing args (say text, commands, alias definitions) don't break chain parsing.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [divider4AliasDefinition](divider4AliasDefinition.md) | `static final char` | Semicolon `';'` — shadows `Alias.divider4AliasDefinition` |

## Methods

_None at this level (no common `parseArgs`; each subclass handles its own arg parsing). Inherits registration methods from `BuiltinAliasWithArgs`._

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class |
| [Alias.divider4AliasDefinition](Alias.java/divider4AliasDefinition.md) | The default space divider, overridden here |
| [builtinAlias](builtinAlias/README.md) | Concrete string-arg implementations (say, sendCommand, alias, ...) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
