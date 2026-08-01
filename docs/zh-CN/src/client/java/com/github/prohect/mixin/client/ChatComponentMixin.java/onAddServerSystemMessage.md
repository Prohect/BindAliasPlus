# onAddServerSystemMessage 方法（src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java）

## 语法

```java
@Inject(method = "addServerSystemMessage(Lnet/minecraft/network/chat/Component;)V", at = @At("HEAD"))
private void onAddServerSystemMessage(Component message, CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `message` | `net.minecraft.network.chat.Component` | 正在添加到 HUD 的服务端系统消息 |
| `ci` | `CallbackInfo` | 未使用的回调 |

## 备注

注入于 `ChatComponent#addServerSystemMessage(Component)` 的 `HEAD`。通过 `message.getString()` 从组件中提取纯文本字符串，并发布到 [`GameChannels.CHAT`](../../../mcp/GameChannels.java/CHAT.md)。服务端系统消息包括加入/离开公告、死亡消息、命令方块输出和 `/say` 广播。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [capture](capture.md) | 发布到 channel 的私有辅助方法 |
| [GameChannels.CHAT](../../../mcp/GameChannels.java/CHAT.md) | 目标 channel 字段 |
