# postCoalescing 方法（src/client/java/com/github/prohect/mcp/GameChannels.java）

## 语法

```java
public static void postCoalescing(String channel, String key, String message)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `channel` | `String` | channel 名称；设计用于 `SOUND` |
| `key` | `String` | 合并键（声音名称）；相同 key 的未排空条目就地更新 |
| `message` | `String` | 消息文本；若更新现有条目，则替换文本，且当 N > 1 时追加 `" xN"` 计数 |

## 备注

以合并行为发布消息。分两种情况：

1. **新 key**：若不存在给定 `key` 的未排空条目，则像 `post()` 一样追加新条目。
2. **已有 key**：若存在相同 `key` 的未排空条目，则就地更新其 `text` 并递增 `count`——条目在插入顺序中的位置保持不变。当 `count` > 1 时，消息附带 `" xN"` 后缀。

这样，频繁重复的声音（脚步声、环境噼啪声）即使与其他声音交错出现，也能按声音类型折叠成一行持续更新的条目。合并仅作用于未排空的消息——一旦 `drain()` 排空消息，旧 key 即被遗忘，新发布从头开始。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [post](post.md) | 非合并变体 |
| [SOUND](SOUND.md) | 本方法设计服务的 channel |
| [drain](drain.md) | 排空消息并重置合并状态 |
| [SoundCapture.onPlaySound](SoundCapture.java/onPlaySound.md) | 发布声音事件的调用方 |
