# AliasWithArgs (src/client/java/com/github/prohect/alias/AliasWithArgs.java)

## Syntax

```java
public interface AliasWithArgs<T extends AliasWithArgs<T>> extends Alias<T>
```

Marker interface for aliases that accept arguments. Only **builtin** aliases should implement this interface; user-defined aliases (`UserAlias`) always implement `AliasWithoutArgs` instead.

Provides `putToAliasesWithArgs` / `putToAliasesWithArgs_notSuggested` default methods that register the alias into the global `Alias.aliasesWithArgs` or `Alias.aliasesWithArgs_notSuggested` maps.

## Remarks

The distinction between `AliasWithArgs` and `AliasWithoutArgs` determines which registration map an alias lives in and how `UserAlias.run()` looks it up. During execution, `UserAlias` first checks the `withoutArgs` maps, then the `withArgs` maps. If an alias is found in a `withArgs` map, the `args` string from the `AliasRecord` is passed to `run(args)`.

The `_notSuggested` variant (`Alias.aliasesWithArgs_notSuggested`) is for internal aliases (e.g. `builtinDrop`, `builtinLock`) that should not appear in user-facing command suggestions.

**Constraint**: User aliases must not implement this interface. They always implement `AliasWithoutArgs`, embedding their args within the definition string instead.

## See Also

| Item | Description |
|------|-------------|
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | The counterpart — aliases without explicit args |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Abstract base providing the no-arg `putToAliasesWithArgs()` overload |
| [Alias](Alias.java/Alias.md) | Root interface declaring the registration maps |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
