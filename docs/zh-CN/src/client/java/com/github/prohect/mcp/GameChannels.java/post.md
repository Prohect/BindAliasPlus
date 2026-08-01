# post 方法（src/client/java/com/github/prohect/mcp/GameChannels.java）

## 语法

```java
public static void post(String channel, String message)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `channel` | `String` | channel 名称：`CHAT`、`MOD`、`SOUND` 或 `RECIPE` 之一 |
| `message` | `String` | 要发布的消息文本；`null` 和空字符串被静默丢弃 |

## 备注

把消息作为全新独立条目发布到 channel。线程安全（在内部锁上同步）。当 channel 的缓冲区超过 `MAX_BUFFER`（100）时，最旧条目被淘汰。这是 `CHAT`、`MOD` 与 `RECIPE` channel 的发布模式，这些场景下每个事件相互独立。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [postCoalescing](postCoalescing.md) | 用于 `SOUND` channel 的合并变体 |
| [drain](drain.md) | 取回自上次 drain 以来的新消息 |
| [CHAT](CHAT.md) | channel 常量 |
| [MOD](MOD.md) | channel 常量 |
| [RECIPE](RECIPE.md) | channel 常量 |
