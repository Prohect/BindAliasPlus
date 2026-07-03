# getCurrentScreen method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static net.minecraft.client.gui.screens.Screen getCurrentScreen()
```

## Remarks

Returns the current screen from `BindAliasPlusClient.currentScreen`.

This field is updated by the mod's screen-tracking mixin. Returns `null` when
no screen is open (i.e., the player is in the game world).

Used by all the screen-type helper methods (`isUnderTextInputScreen`, `isUnderAnyScreen`,
`isInContainerScreen`, etc.) as well as indirectly by `UserAlias.run()` to check
the screen blacklist.

## Return value

The current `Screen` instance, or `null` if no screen is open.

## See Also

| Item                                                | Description                   |
| --------------------------------------------------- | ----------------------------- |
| [isUnderAnyScreen](isUnderAnyScreen.md)             | Checks if any screen is open  |
| [isUnderTextInputScreen](isUnderTextInputScreen.md) | Checks for text-input screens |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
