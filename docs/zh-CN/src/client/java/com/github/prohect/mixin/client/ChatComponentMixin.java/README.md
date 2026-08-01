# ChatComponentMixin

针对 `net.minecraft.client.gui.components.ChatComponent` 的 mixin。拦截全部三个公共消息入口点，为 MCP 消息集线器的 `CHAT` channel 供数。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [onAddClientSystemMessage](onAddClientSystemMessage.md) | `void onAddClientSystemMessage(Component message, CallbackInfo ci)` | `@Inject` 于 `addClientSystemMessage` 的 `HEAD` —— 捕获客户端系统消息 |
| [onAddServerSystemMessage](onAddServerSystemMessage.md) | `void onAddServerSystemMessage(Component message, CallbackInfo ci)` | `@Inject` 于 `addServerSystemMessage` 的 `HEAD` —— 捕获服务端系统消息 |
| [onAddPlayerMessage](onAddPlayerMessage.md) | `void onAddPlayerMessage(Component message, MessageSignature sig, GuiMessageTag tag, CallbackInfo ci)` | `@Inject` 于 `addPlayerMessage` 的 `HEAD` —— 捕获玩家聊天消息 |
| [capture](capture.md) | `void capture(String text)`（静态，私有） | 将提取的消息文本发布到 `CHAT` channel |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels](../../../mcp/GameChannels.java/README.md) | 目标 channel 集线器 |
| [StateTracker](../../../mcp/StateTracker.java/README.md) | 将 channel 排空到 MCP 响应 envelope |
