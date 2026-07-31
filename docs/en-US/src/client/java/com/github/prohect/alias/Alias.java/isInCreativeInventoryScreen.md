# isInCreativeInventoryScreen method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static boolean isInCreativeInventoryScreen()
```

## Return value

`true` if the current screen is a `CreativeModeInventoryScreen` (the creative-mode item selection screen). Otherwise `false`.

## Remarks

Used by aliases that need to distinguish creative-mode inventory behavior from survival-mode. Creative inventory has different slot layouts and mechanics (e.g. the destroy-item slot).

## See Also

| Item | Description |
|------|-------------|
| [isInInventoryScreen](isInInventoryScreen.md) | Survival-mode inventory variant |
| [isInContainerScreen](isInContainerScreen.md) | Broader — any container screen |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
