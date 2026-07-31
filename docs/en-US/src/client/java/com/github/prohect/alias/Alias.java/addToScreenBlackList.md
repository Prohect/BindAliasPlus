# addToScreenBlackList method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public default T addToScreenBlackList()
```

## Return value

Returns `this` (the alias instance) for fluent builder chaining.

## Remarks

Adds `this` alias to the static `blackList4Screen` list. When an alias is on the blacklist, `UserAlias.run()` and `runInternal()` suppress its execution while any screen is open — **except** for release events (`args.equals("0")`), which always pass through.

This prevents held keys (+attack, +use, +forward, etc.) from affecting gameplay while the player is in inventory, crafting, or other screens, but ensures keys don't get "stuck" by allowing the release to propagate.

Typical usage in `BindAliasClient.onInitializeClient()`:

```java
new AttackAlias()
    .putToAliasesWithArgs()
    .addToScreenBlackList();
```

Only `BuiltinAliasWithArgs` instances are subject to the blacklist check — the check in `UserAlias` first tests `alias instanceof BuiltinAliasWithArgs` before consulting `blackList4Screen`.

## See Also

| Item | Description |
|------|-------------|
| [blackList4Screen](blackList4Screen.md) | The list this method adds to |
| [isUnderAnyScreen](isUnderAnyScreen.md) | The screen check used in conjunction with the blacklist |
| [UserAlias.run](UserAlias.java/run.md) | Where the blacklist is checked during execution |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
