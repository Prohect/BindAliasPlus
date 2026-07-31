# getCurrentScreen method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static Screen getCurrentScreen()
```

## Return value

The current `Screen` instance from `BindAliasClient.currentScreen`, or `null` if no screen is open.

## Remarks

A convenience accessor for `BindAliasClient.currentScreen`, which is set each tick by the `MinecraftClientMixin` mixin. All other screen-type helpers delegate to this method.

## See Also

| Item | Description |
|------|-------------|
| [isUnderAnyScreen](isUnderAnyScreen.md) | Returns `true` when this method returns non-null |
| [isUnderTextInputScreen](isUnderTextInputScreen.md) | Checks specific screen types |
| [BindAliasClient](BindAliasClient.java/BindAliasClient.md) | Where `currentScreen` is updated |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
