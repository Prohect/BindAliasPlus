# ChatComponentMixin

Mixin targeting `net.minecraft.client.gui.components.ChatComponent`. Intercepts all three public message-entry points to feed the `CHAT` channel of the MCP message hub.

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [onAddClientSystemMessage](onAddClientSystemMessage.md) | `void onAddClientSystemMessage(Component message, CallbackInfo ci)` | `@Inject` at `HEAD` of `addClientSystemMessage` — captures client-side system messages |
| [onAddServerSystemMessage](onAddServerSystemMessage.md) | `void onAddServerSystemMessage(Component message, CallbackInfo ci)` | `@Inject` at `HEAD` of `addServerSystemMessage` — captures server-side system messages |
| [onAddPlayerMessage](onAddPlayerMessage.md) | `void onAddPlayerMessage(Component message, MessageSignature sig, GuiMessageTag tag, CallbackInfo ci)` | `@Inject` at `HEAD` of `addPlayerMessage` — captures player chat messages |
| [capture](capture.md) | `void capture(String text)` (static, private) | Posts the extracted message text to the `CHAT` channel |

## See Also

| Item | Description |
|------|-------------|
| [GameChannels](../../../mcp/GameChannels.java/README.md) | Destination channel hub |
| [StateTracker](../../../mcp/StateTracker.java/README.md) | Drains channels into the MCP response envelope |
