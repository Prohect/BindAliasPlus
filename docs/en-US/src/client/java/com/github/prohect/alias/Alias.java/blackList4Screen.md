# blackList4Screen field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final List<Alias<?>> blackList4Screen
```

List of aliases that are **suppressed** when any screen is open (except for release / `"0"` events). An alias is added to this list by calling `addToScreenBlackList()` during registration.

## Remarks

Checked in `UserAlias.run()` and `runInternal()`: if a builtin alias is an instance of `BuiltinAliasWithArgs` **and** is in this list, it only executes when `!isUnderAnyScreen()` or when its args are `"0"` (release). This prevents held keys (`+attack`, `+use`, etc.) from affecting the game while the player is in inventory or other screens, while still allowing the release event to propagate so keys don't get stuck.

Populated during `BindAliasClient.onInitializeClient()` via the builder pattern (e.g. `new AttackAlias().putToAliasesWithArgs().addToScreenBlackList()`).

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
