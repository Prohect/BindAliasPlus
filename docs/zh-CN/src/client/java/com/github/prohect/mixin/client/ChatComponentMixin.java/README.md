# ChatComponentMixin

针对 `net.minecraft.client.gui.hud.ChatHud` 的 mixin。注入到唯一的 `addMessage(Text)` 入口点，将所有聊天消息（系统、玩家、客户端侧）送入 MCP `CHAT` channel。在 1.21.x 上，早期版本中的三个独立 `add*Message` 方法已统一——单个注入即可捕获所有消息。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [captureMessage](captureMessage.md) | `void captureMessage(Text message, CallbackInfo ci)` | `addMessage(Text)` 的 `HEAD` 处 `@Inject`——捕获所有 HUD 聊天消息（系统、玩家、客户端侧） |
| [capture](capture.md) | `void capture(String text)`（static, private） | 将提取的消息文本发布到 `CHAT` channel |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels](../../../mcp/GameChannels.java/README.md) | 目标 channel 中心 |
| [StateTracker](../../../mcp/StateTracker.java/README.md) | 将 channel 排入 MCP 响应 envelope |
