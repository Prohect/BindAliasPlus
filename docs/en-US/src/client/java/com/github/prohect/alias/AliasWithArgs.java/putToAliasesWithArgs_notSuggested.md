# putToAliasesWithArgs_notSuggested method (src/client/java/com/github/prohect/alias/AliasWithArgs.java)

## Syntax

```java
public default T putToAliasesWithArgs_notSuggested(String key)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `key` | `String` | The alias name to register under |

## Return value

Returns `this` for fluent builder chaining.

## Remarks

Same as `putToAliasesWithArgs` but registers into `Alias.aliasesWithArgs_notSuggested` instead. Aliases in this map are **not** shown in command suggestions but are fully executable — `UserAlias.run()` checks this map during alias-chain execution (after `aliasesWithoutArgs_notSuggested`, before `aliasesWithArgs`).

Use this for internal aliases that users should not see in autocomplete (e.g. `builtinDrop`, `builtinLock`).

## See Also

| Item | Description |
|------|-------------|
| [putToAliasesWithArgs](putToAliasesWithArgs.md) | Suggested variant |
| [BuiltinAliasWithArgs.putToAliasesWithArgs_notSuggested](BuiltinAliasWithArgs.java/putToAliasesWithArgs_notSuggested.md) | Keyless overload |
| [aliasesWithArgs_notSuggested](Alias.java/aliasesWithArgs_notSuggested.md) | The map this method writes to |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
