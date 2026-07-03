# isInInventoryScreen method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static boolean isInInventoryScreen()
```

## Remarks

Checks whether the current screen is specifically the player inventory screen
(`InventoryScreen`).

Note that `InventoryScreen` extends `AbstractContainerScreen`, so both
`isInContainerScreen()` and `isInInventoryScreen()` return `true` when the
inventory is open.

## Return value

`true` if the player inventory screen is open.

## See Also

| Item                                          | Description                            |
| --------------------------------------------- | -------------------------------------- |
| [getCurrentScreen](getCurrentScreen.md)       | Provides the current screen instance   |
| [isInContainerScreen](isInContainerScreen.md) | Broader check for any container screen |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
