# SOUND 字段（src/client/java/com/github/prohect/mcp/GameChannels.java）

## 语法

```java
public static final String SOUND = "sound"
```

## 备注

声音事件的 channel 名称常量。由 [`SoundCapture`](SoundCapture.java/README.md) 供数，后者以 `SoundEventListener` 身份注册在客户端的 `SoundManager` 上——与原版字幕浮层使用的钩子相同。只上报听觉范围内、能出字幕的声音。消息格式：`[client_tick:N] SoundName [yaw±N pitch±N D.Dm]`。这是唯一的合并 channel：同名重复声音就地更新并附带 `" xN"` 计数，而非追加新条目。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SoundCapture](SoundCapture.java/README.md) | 本 channel 的供数方 |
| [postCoalescing](postCoalescing.md) | 本 channel 使用的合并发布方法 |
| [CHAT](CHAT.md) | 聊天 channel |
| [MOD](MOD.md) | 模组日志 channel |
| [RECIPE](RECIPE.md) | 配方解锁 channel |
