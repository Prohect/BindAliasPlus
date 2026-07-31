# currentScreen field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static net.minecraft.client.gui.screens.Screen currentScreen
```

## Remarks

Cached reference to the currently open `Screen`, updated by `GuiMixin` on every
screen change event. `null` when no screen is open (in-game).

Used by `Alias.getCurrentScreen()` to implement screen-aware alias blacklisting —
when a screen is open, aliases in `Alias.blackList4Screen` that receive key-down
(`"1"` or non-"0") args are suppressed, preventing e.g. attack/use aliases from
firing while in inventory.

Thread safety: read/written on render thread only.

## See Also

| Item                                                              | Description                              |
| ----------------------------------------------------------------- | ---------------------------------------- |
| [Alias.getCurrentScreen](../alias/Alias.java/getCurrentScreen.md) | Reads this field                         |
| [Alias.blackList4Screen](../alias/Alias.java/blackList4Screen.md) | Aliases suppressed when a screen is open | [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)* |
