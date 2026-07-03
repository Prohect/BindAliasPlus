# isInContainerScreen method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static boolean isInContainerScreen()
```

## Remarks

Checks whether the current screen is an `AbstractContainerScreen`.

This covers chests, furnaces, crafting tables, anvils, and any other block-entity
or entity-based container GUI. Does not include the player inventory screen
(which extends `AbstractContainerScreen` but is checked separately via
`isInInventoryScreen()`).

Used by aliases that interact with container slots (e.g., slot-switching aliases)
to verify the correct screen context.

## Return value

`true` if the current screen is an `AbstractContainerScreen`.

## See Also

| Item                                          | Description                             |
| --------------------------------------------- | --------------------------------------- |
| [getCurrentScreen](getCurrentScreen.md)       | Provides the current screen instance    |
| [isInInventoryScreen](isInInventoryScreen.md) | Specific check for the player inventory |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
