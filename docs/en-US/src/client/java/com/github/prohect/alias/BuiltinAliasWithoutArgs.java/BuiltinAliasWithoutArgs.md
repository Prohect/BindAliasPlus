# BuiltinAliasWithoutArgs (src/client/java/com/github/prohect/alias/BuiltinAliasWithoutArgs.java)

## Syntax

```java
public abstract class BuiltinAliasWithoutArgs<T extends BuiltinAliasWithoutArgs<T>> implements AliasWithoutArgs<T>
```

Abstract base class for builtin aliases that take no arguments. Stores the `builtinAliasName` used for registration and provides no-argument `putToAliasesWithoutArgs()` / `putToAliasesWithoutArgs_notSuggested()` overloads that use `this.builtinAliasName` as the registration key.

## Remarks

This is the registration base for all single-action builtin aliases that are triggered by key events or by name in an alias chain. Since they take no args, they can be bound to keyboard keys via `BindAliasKeyBinding`.

The constructor takes a `String builtinAliasName` — the name that goes into the global `Alias.aliasesWithoutArgs` map. This is what `UserAlias.run()` matches against when it encounters the name in an alias chain.

**Concrete subclasses**: `EscAlias`, `ToggleInventoryAlias`, `SwapHandAlias`, `PickItemAlias`, `CyclePerspectiveAlias`, `FpsAlias`, `TpsAlias`, `Tps2Alias`, `CloseScreenAlias`, `ScreenshotAlias`, `DebugOverlayAlias`, `ShutdownAlias`, `OpenInventoryAlias`, `LockAlias_OnLock`, `LockAlias_Unlock`, and others.

**Note**: `UserAlias` implements `AliasWithoutArgs` directly — it does **not** extend this class. User aliases are not builtin.

## See Also

| Item | Description |
|------|-------------|
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | The marker interface this class implements |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | The with-args counterpart |
| [UserAlias](UserAlias.java/UserAlias.md) | Non-builtin AliasWithoutArgs — user-defined alias chains |
| [builtinAlias](builtinAlias/README.md) | Concrete implementations |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
