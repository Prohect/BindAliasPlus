# ChatComponentMixin

Mixin targeting `net.minecraft.client.gui.hud.ChatHud`. Injects into the single `addMessage(Text)` entry point to feed all chat messages (system, player, client-side) into the MCP `CHAT` channel. On 1.21.x, the three separate `add*Message` methods from earlier versions have been unified — a single injection captures everything.

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [captureMessage](captureMessage.md) | `void captureMessage(Text message, CallbackInfo ci)` | `@Inject` at `HEAD` of `addMessage(Text)` — captures all HUD-bound chat messages (system, player, client-side) |
| [capture](capture.md) | `void capture(String text)` (static, private) | Posts the extracted message text to the `CHAT` channel |

## See Also

| Item | Description |
|------|-------------|
| [GameChannels](../../../mcp/GameChannels.java/README.md) | Destination channel hub |
| [StateTracker](../../../mcp/StateTracker.java/README.md) | Drains channels into the MCP response envelope |
