# AliasWithoutArgs (src/client/java/com/github/prohect/alias/AliasWithoutArgs.java)

## Syntax

```java
public interface AliasWithoutArgs<T extends AliasWithoutArgs<T>> extends Alias<T>
```

Marker interface for aliases that are triggered by name alone — no explicit arguments passed at invocation time. Both builtin single-action aliases (e.g. `esc`, `toggleInventory`, `swapHand`) and user-defined aliases (`UserAlias`) implement this interface.

Provides `putToAliasesWithoutArgs` / `putToAliasesWithoutArgs_notSuggested` default methods that register the alias into the global `Alias.aliasesWithoutArgs` or `Alias.aliasesWithoutArgs_notSuggested` maps.

## Remarks

This is the default registration path for most aliases. Key-event-driven aliases (those bound to keyboard keys via `BindAliasKeyBinding`) must be `AliasWithoutArgs` because key presses cannot supply arguments.

The `_notSuggested` variant (`Alias.aliasesWithoutArgs_notSuggested`) is for internal aliases that should not appear in user-facing command suggestions.

An additional map, `Alias.aliasesWithoutArgs_fromBindCommand`, stores aliases created by the `bind` command — these are user aliases registered for key-binding lookup.

## See Also

| Item | Description |
|------|-------------|
| [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) | The counterpart — aliases that accept explicit args |
| [BuiltinAliasWithoutArgs](BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Abstract base providing the no-arg `putToAliasesWithoutArgs()` overload |
| [UserAlias](UserAlias.java/UserAlias.md) | The only `AliasWithoutArgs` that is not a builtin |
| [Alias](Alias.java/Alias.md) | Root interface declaring the registration maps |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
