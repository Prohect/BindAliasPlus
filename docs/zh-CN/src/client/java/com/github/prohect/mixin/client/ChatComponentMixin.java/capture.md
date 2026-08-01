# capture 方法（src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java）

## 语法

```java
private static void capture(String text)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `text` | `String` | 要发布到 CHAT channel 的纯文本消息 |

## 备注

本 mixin 中三个 `@Inject` 方法共同调用的私有静态辅助方法。通过 `GameChannels.post(CHAT, text)` 将提取的消息文本发布到 [`GameChannels.CHAT`](../../../mcp/GameChannels.java/CHAT.md)。不过滤也不转换文本 —— `Component.getString()` 的原始输出按原样转发。线程安全，因为 `GameChannels.post` 内部同步。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels.post](../../../mcp/GameChannels.java/post.md) | channel 发布方法 |
| [ChatComponentMixin](ChatComponentMixin.md) | 所属 mixin 类 |
