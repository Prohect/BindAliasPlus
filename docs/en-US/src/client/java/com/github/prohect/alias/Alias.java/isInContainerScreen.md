# isInContainerScreen method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static boolean isInContainerScreen()
```

## Return value

`true` if the current screen is an `AbstractContainerScreen` (any container GUI — chests, furnaces, crafting tables, player inventory, etc.). Otherwise `false`.

## Remarks

Used by `VarAlias` and `SwapSlotAlias` to determine whether container-slot operations are valid. The `cN` argument syntax (container slot N) is only valid when this returns `true`. Also used by `PickItemAlias` and `+drop` to adjust behavior for container screens.

## See Also

| Item | Description |
|------|-------------|
| [isInInventoryScreen](isInInventoryScreen.md) | Narrower — only the player's inventory screen |
| [isInCreativeInventoryScreen](isInCreativeInventoryScreen.md) | Narrower — creative inventory screen |
| [isUnderAnyScreen](isUnderAnyScreen.md) | Broadest — any screen at all |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
