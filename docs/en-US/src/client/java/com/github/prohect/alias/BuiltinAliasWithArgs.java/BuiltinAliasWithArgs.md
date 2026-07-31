# BuiltinAliasWithArgs (src/client/java/com/github/prohect/alias/BuiltinAliasWithArgs.java)

## Syntax

```java
public abstract class BuiltinAliasWithArgs<T extends BuiltinAliasWithArgs<T>> implements AliasWithArgs<T>
```

Abstract base class for all builtin aliases that accept arguments. Stores the `builtinAliasName` used for registration and provides no-argument `putToAliasesWithArgs()` / `putToAliasesWithArgs_notSuggested()` overloads that use `this.builtinAliasName` as the registration key.

## Remarks

Every concrete builtin alias that accepts args extends one of the typed subclasses (`BuiltinAliasWithBooleanArgs`, `BuiltinAliasWithIntegerArgs`, `BuiltinAliasWithDoubleArgs`, `BuiltinAliasWithStringArgs`), but the registration itself happens at this level.

The constructor takes a `String builtinAliasName` — the name that goes into the global `Alias.aliasesWithArgs` map. This is what `UserAlias.run()` matches against when it encounters `aliasName` in an alias chain.

**Subclass contract**: Concrete subclasses must implement `run(String args)`. They typically call `parseArgs(args)` first to set their typed `flag` field, then perform the alias action. Subclasses defining their own `divider4AliasDefinition` (like `BuiltinAliasWithStringArgs` at `;`) override the default space divider when constructing alias chains.

## See Also

| Item | Description |
|------|-------------|
| [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) | The marker interface this class implements |
| [BuiltinAliasWithBooleanArgs](BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Subclass for `+`/`-` switch aliases |
| [BuiltinAliasWithIntegerArgs](BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | Subclass for integer-arg aliases (slot, wait, yaw, pitch) |
| [BuiltinAliasWithDoubleArgs](BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | Subclass for double-arg aliases (setYaw, setPitch) |
| [BuiltinAliasWithStringArgs](BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | Subclass for string-arg aliases with `;` definition divider |
| [builtinAlias](builtinAlias/README.md) | Concrete implementations |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
