# isUnderTextInputScreen method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static boolean isUnderTextInputScreen()
```

## Return value

`true` if the current screen is a text-input screen: `ChatScreen`, `CommandBlockEditScreen`, `SignEditScreen`, or `BookEditScreen`. Otherwise `false`.

## Remarks

Used by boolean-arg aliases (movement, attack, use) to suppress key injection while the player is typing. If the player presses `W` while the chat is open, the `+forward` alias checks this method first and does **not** inject the forward movement into the game's `KeyboardInput`.

**Callers**: All `+`/`-` switch aliases that interact with game controls (`+attack`, `+use`, `+forward`, `+back`, `+left`, `+right`, `+jump`, `+sneak`, `+sprint`, `+drop`).

## See Also

| Item | Description |
|------|-------------|
| [isUnderAnyScreen](isUnderAnyScreen.md) | Less restrictive — returns `true` for any screen |
| [getCurrentScreen](getCurrentScreen.md) | Underlying screen query |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
