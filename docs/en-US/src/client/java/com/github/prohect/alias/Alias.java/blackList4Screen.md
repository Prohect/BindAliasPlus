# blackList4Screen field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final java.util.List<com.github.prohect.alias.Alias<?>> blackList4Screen
```

## Remarks

List of aliases that are restricted when any screen is open.

When a blacklisted alias is dispatched in `UserAlias.run()` or `UserAlias.runInternal()`:

- If no screen is open: executes normally.
- If a screen is open and `args` is `"0"` (key-up): executes (allows releasing a key).
- If a screen is open and `args` is not `"0"` (key-down): skipped (suppressed).

Aliases add themselves via `addToScreenBlackList()` during registration.
Only checked for aliases that are `instanceof BuiltinAliasWithArgs` at dispatch time.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
