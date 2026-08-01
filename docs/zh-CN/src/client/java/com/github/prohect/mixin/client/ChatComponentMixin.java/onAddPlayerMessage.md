# onAddPlayerMessage 方法（src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java）

## 语法

```java
@Inject(method = "addPlayerMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V", at = @At("HEAD"))
private void onAddPlayerMessage(Component message, MessageSignature signature, GuiMessageTag tag, CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `message` | `net.minecraft.network.chat.Component` | 正在添加到 HUD 的玩家聊天消息 |
| `signature` | `net.minecraft.network.chat.MessageSignature` | 消息的加密签名（未使用） |
| `tag` | `net.minecraft.client.multiplayer.chat.GuiMessageTag` | GUI 消息标签（未使用） |
| `ci` | `CallbackInfo` | 未使用的回调 |

## 备注

注入于 `ChatComponent#addPlayerMessage(Component, MessageSignature, GuiMessageTag)` 的 `HEAD`。通过 `message.getString()` 从组件中提取纯文本字符串，并发布到 [`GameChannels.CHAT`](../../../mcp/GameChannels.java/CHAT.md)。这是捕获服务器上发送的实际玩家聊天消息的注入点。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [capture](capture.md) | 发布到 channel 的私有辅助方法 |
| [GameChannels.CHAT](../../../mcp/GameChannels.java/CHAT.md) | 目标 channel 字段 |
