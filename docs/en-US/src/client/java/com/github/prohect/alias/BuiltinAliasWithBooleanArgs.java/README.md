# BuiltinAliasWithBooleanArgs

Abstract base for `+`/`-` switch aliases (attack, use, forward, back, left, right, jump, sneak, sprint, drop, playerList, advancements, silent, freeCursor).

## Fields

| Name | Type | Description |
|------|------|-------------|
| [flag](flag.md) | `boolean` | Current state: `true` when held, `false` when released |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [parseArgs](parseArgs.md) | `void parseArgs(String args)` | Parse `"0"`/`"1"` into `flag` |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | `void reapplyToGameKeyMapping()` | Re-sync held key after screen transitions |

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class |
| [ReapplyAlias](builtinAlias/ReapplyAlias.java/ReapplyAlias.md) | Triggers reapply on all boolean aliases |
| [builtinAlias](builtinAlias/README.md) | Concrete `+attack`, `+use`, `+forward`, ... implementations |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
