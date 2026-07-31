# currentScreen field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static net.minecraft.client.gui.screens.Screen currentScreen
```

## Remarks

Cached reference to the currently open `Screen`, or `null` when none is open. Updated every tick by `MinecraftClientMixin` via `Minecraft.getInstance().screen`. Read by alias run logic to decide screen suppression behavior, and by `Alias.isUnderAnyScreen()` / `Alias.isUnderTextInputScreen()` / `Alias.isInContainerScreen()`. Must only be accessed on the game thread.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
