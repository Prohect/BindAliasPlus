# isInInventoryScreen method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static boolean isInInventoryScreen()
```

## Return value

`true` if the current screen is specifically a `InventoryScreen` (the player's survival-mode inventory). Otherwise `false`.

## Remarks

A narrower check than `isInContainerScreen()` — this only matches the player's own inventory screen, not external container screens like chests or furnaces. Used by aliases that need to distinguish the player inventory from other container GUIs.

## See Also

| Item | Description |
|------|-------------|
| [isInContainerScreen](isInContainerScreen.md) | Broader — any container screen including this one |
| [isInCreativeInventoryScreen](isInCreativeInventoryScreen.md) | Creative-mode inventory variant |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
