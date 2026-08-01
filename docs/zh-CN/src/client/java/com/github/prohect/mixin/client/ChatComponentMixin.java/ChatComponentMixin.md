# ChatComponentMixin（src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java）

## 语法

```java
@Mixin(ChatHud.class)
public class com.github.prohect.mixin.client.ChatComponentMixin
```

## 静态初始化器

_无。_

## 备注

混入 `net.minecraft.client.gui.hud.ChatHud` 以拦截唯一的 `addMessage(Text)` 方法——这是 Minecraft 1.21.x 中所有 HUD 聊天消息的统一入口点。在该版本中，早期 Minecraft 版本的三个独立 `add*Message` 方法（`addClientSystemMessage`、`addServerSystemMessage`、`addPlayerMessage`）已合并为单个 `addMessage(Text)` 重写，因此在 `HEAD` 处注入单个 `@Inject` 即可捕获所有消息类型：服务端系统消息（加入/离开、命令反馈）、客户端系统消息（浮层文本、动作条）以及玩家聊天消息。纯文本消息通过 `Text#getString()` 提取并发布到 [`GameChannels.CHAT`](../../../mcp/GameChannels.java/CHAT.md)。这是 MCP 响应 envelope 中 `chat` 数组的唯一来源。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels](../../../mcp/GameChannels.java/README.md) | 捕获消息的目标 channel 中心 |
| [captureMessage](captureMessage.md) | 捕获所有消息类型的 `@Inject` |
| [capture](capture.md) | 发布到 channel 的私有辅助方法 |
