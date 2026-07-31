# chatError method (src/client/java/com/github/prohect/alias/builtinAlias/ApplyRecipeAlias.java)

Sends an error message to the player's local game chat (client-side only, visible on HUD and in chat channel).

## Syntax

```java
private static void chatError(net.minecraft.client.player.LocalPlayer, java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `player` | `LocalPlayer` | The local player to send the message to |
| `message` | `String` | The error message text |

## Remarks

Uses `player.sendSystemMessage(Component.literal(message))` to display the message client-side only — it is **not** sent to the server. This is appropriate for alias-level errors (invalid args, missing menus, unknown recipes) that only the local player needs to see.

All `ApplyRecipeAlias` errors are routed through this helper. The message prefix `[applyRecipe]` is included by the callers.

## See Also

| Item | Description |
|------|-------------|
| [ApplyRecipeAlias.run()](run.md) | All error paths use this helper |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
