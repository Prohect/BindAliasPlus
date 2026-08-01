# ChatComponentMixin（src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java）

## 语法

```java
@Mixin(ChatComponent.class)
public class com.github.prohect.mixin.client.ChatComponentMixin
```

## 静态初始化器

_无。_

## 备注

混入 `net.minecraft.client.gui.components.ChatComponent`，拦截全部三个公共消息入口点（客户端系统、服务端系统、玩家消息），并将提取的纯文本消息送入 [`GameChannels.CHAT`](../../../mcp/GameChannels.java/CHAT.md) channel。这是 MCP 响应 envelope 中 `chat` 数组的唯一来源。每个注入都运行在目标方法的 `HEAD`，在原版渲染前通过 `Component.getString()` 捕获消息。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels](../../../mcp/GameChannels.java/README.md) | 捕获消息的目标 channel 集线器 |
| [onAddClientSystemMessage](onAddClientSystemMessage.md) | 注入 `addClientSystemMessage` |
| [onAddServerSystemMessage](onAddServerSystemMessage.md) | 注入 `addServerSystemMessage` |
| [onAddPlayerMessage](onAddPlayerMessage.md) | 注入 `addPlayerMessage` |
| [capture](capture.md) | 发布到 channel 的私有辅助方法 |
