# silentMode field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static boolean silentMode
```

## Remarks

When `true`, suppresses all alias feedback messages in chat (the mod's `sendFeedback` calls and `log` alias output). Toggled by the `+silent` / `-silent` switch aliases. Checked before every `context.getSource().sendFeedback(...)` call in command handlers. Reset to `false` on disconnect. Used by `ChatComponentMixin` to filter chat rendering.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
