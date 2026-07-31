# putToAliasesWithoutArgs method (src/client/java/com/github/prohect/alias/AliasWithoutArgs.java)

## Syntax

```java
public default T putToAliasesWithoutArgs(String key)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `key` | `String` | The alias name to register under |

## Return value

Returns `this` for fluent builder chaining.

## Remarks

Registers `this` alias into the global `Alias.aliasesWithoutArgs` map under the given `key`. This is the **suggested** registration path — aliases registered here appear in command suggestions and are checked **first** during alias-chain execution in `UserAlias.run()`.

## See Also

| Item | Description |
|------|-------------|
| [putToAliasesWithoutArgs_notSuggested](putToAliasesWithoutArgs_notSuggested.md) | Non-suggested variant |
| [BuiltinAliasWithoutArgs.putToAliasesWithoutArgs](BuiltinAliasWithoutArgs.java/putToAliasesWithoutArgs.md) | Keyless overload using `builtinAliasName` |
| [aliasesWithoutArgs](Alias.java/aliasesWithoutArgs.md) | The map this method writes to |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
