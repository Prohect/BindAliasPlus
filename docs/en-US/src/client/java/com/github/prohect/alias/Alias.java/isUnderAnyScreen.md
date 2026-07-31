# isUnderAnyScreen method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static boolean isUnderAnyScreen()
```

## Return value

`true` if any screen is currently open (i.e., `getCurrentScreen() != null`). Otherwise `false`.

## Remarks

Used in conjunction with the screen blacklist (`blackList4Screen`) to suppress aliases when a GUI is open. Aliases also use this independently — for example, `+freeCursor` bypasses the vanilla mouse grab logic only when a screen is open, and `toggleInventory` checks this to decide whether to open or close the inventory.

**Key distinction from `isUnderTextInputScreen()`**: This returns `true` for *all* screens including inventory, crafting, and container screens. `isUnderTextInputScreen()` is a stricter subset that only catches text-entry screens.

## See Also

| Item | Description |
|------|-------------|
| [isUnderTextInputScreen](isUnderTextInputScreen.md) | Stricter — only text-input screens |
| [blackList4Screen](blackList4Screen.md) | Aliases suppressed when this returns `true` |
| [isInContainerScreen](isInContainerScreen.md) | Narrower — only container screens |
| [isInInventoryScreen](isInInventoryScreen.md) | Narrower — only player inventory screen |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
