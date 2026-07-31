# putToAliasesWithoutArgs_notSuggested method (src/client/java/com/github/prohect/alias/AliasWithoutArgs.java)

## Syntax

```java
public default T putToAliasesWithoutArgs_notSuggested(String key)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `key` | `String` | The alias name to register under |

## Return value

Returns `this` for fluent builder chaining.

## Remarks

Same as `putToAliasesWithoutArgs` but registers into `Alias.aliasesWithoutArgs_notSuggested` instead. Aliases in this map are **not** shown in command suggestions but are checked second in `UserAlias.run()` lookup order.

Use this for internal / view-switching aliases like `FPS`, `TPS`, `TPS2`, and lock wrappers.

## See Also

| Item | Description |
|------|-------------|
| [putToAliasesWithoutArgs](putToAliasesWithoutArgs.md) | Suggested variant |
| [BuiltinAliasWithoutArgs.putToAliasesWithoutArgs_notSuggested](BuiltinAliasWithoutArgs.java/putToAliasesWithoutArgs_notSuggested.md) | Keyless overload |
| [aliasesWithoutArgs_notSuggested](Alias.java/aliasesWithoutArgs_notSuggested.md) | The map this method writes to |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
