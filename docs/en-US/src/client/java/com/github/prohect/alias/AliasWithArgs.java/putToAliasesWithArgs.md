# putToAliasesWithArgs method (src/client/java/com/github/prohect/alias/AliasWithArgs.java)

## Syntax

```java
public default T putToAliasesWithArgs(String key)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `key` | `String` | The alias name to register under |

## Return value

Returns `this` for fluent builder chaining.

## Remarks

Registers `this` alias into the global `Alias.aliasesWithArgs` map under the given `key`. This is the **suggested** registration path — aliases registered here appear in command suggestions.

This method is a **raw key** variant: it takes an explicit key parameter. The `BuiltinAliasWithArgs` subclass provides a keyless overload that uses `this.builtinAliasName`. This raw-key variant exists for flexibility (e.g. if an alias needs to register under a different name than its builtin name).

## See Also

| Item | Description |
|------|-------------|
| [putToAliasesWithArgs_notSuggested](putToAliasesWithArgs_notSuggested.md) | Non-suggested variant |
| [BuiltinAliasWithArgs.putToAliasesWithArgs](BuiltinAliasWithArgs.java/putToAliasesWithArgs.md) | Keyless overload using `builtinAliasName` |
| [aliasesWithArgs](Alias.java/aliasesWithArgs.md) | The map this method writes to |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
