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

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
