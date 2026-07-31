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

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
