# isInCreativeInventoryScreen method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static boolean isInCreativeInventoryScreen()
```

## Remarks

Checks whether the current screen is the creative-mode inventory screen
(`CreativeModeInventoryScreen`).

This screen has a different slot layout than the survival inventory, so aliases
that manipulate inventory slots may need to distinguish between them.

## Return value

`true` if the creative inventory screen is open.

## See Also

| Item                                          | Description                             |
| --------------------------------------------- | --------------------------------------- |
| [getCurrentScreen](getCurrentScreen.md)       | Provides the current screen instance    |
| [isInInventoryScreen](isInInventoryScreen.md) | Check for the survival inventory screen |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
