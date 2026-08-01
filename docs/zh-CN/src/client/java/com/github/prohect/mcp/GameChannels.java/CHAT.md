# CHAT 字段（src/client/java/com/github/prohect/mcp/GameChannels.java）

## 语法

```java
public static final String CHAT = "chat"
```

## 备注

游戏聊天消息（服务器、系统与玩家消息）的 channel 名称常量。由 [`ChatComponentMixin`](../../mixin/client/ChatComponentMixin.java/README.md) 供数，该 mixin 捕获 `ChatComponent` 的全部三个消息入口点。消息是 `Component.getString()` 返回的纯文本字符串。不合并：每条消息都是独立条目。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ChatComponentMixin](../../mixin/client/ChatComponentMixin.java/README.md) | 本 channel 的供数方 |
| [MOD](MOD.md) | 模组日志 channel |
| [SOUND](SOUND.md) | 声音事件 channel |
| [RECIPE](RECIPE.md) | 配方解锁 channel |
