# isUnderAnyScreen method (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static boolean isUnderAnyScreen()
```

## Remarks

Checks whether any screen is currently open.

Returns `true` if `getCurrentScreen()` returns a non-null value. This includes
all GUI screens: inventories, chat, menus, etc.

Used by the screen blacklist logic in `UserAlias.run()` and `UserAlias.runInternal()`
to suppress blacklisted aliases when the player is not in the game world.

## Return value

`true` if any screen is open; `false` if the player is in the game world.

## See Also

| Item                                    | Description                                  |
| --------------------------------------- | -------------------------------------------- |
| [getCurrentScreen](getCurrentScreen.md) | Provides the current screen instance         |
| [blackList4Screen](blackList4Screen.md) | The blacklist checked against this condition |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
