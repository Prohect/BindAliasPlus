# MOD 字段（src/client/java/com/github/prohect/mcp/GameChannels.java）

## 语法

```java
public static final String MOD = "mod"
```

## 备注

模组自身日志输出（别名反馈、错误、`log\` 别名消息）的 channel 名称常量。由通过 [`init()`](init.md) 注册在 `"bind-alias"` 日志器上的 Log4j appender 供数。该 appender 只捕获模组日志器的消息，跳过根日志器中 Fabric/mixin/渲染相关的噪音。不合并：每条日志消息都是独立条目。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [init](init.md) | 注册向本 channel 供数的 Log4j appender |
| [CHAT](CHAT.md) | 聊天 channel |
| [SOUND](SOUND.md) | 声音事件 channel |
| [RECIPE](RECIPE.md) | 配方解锁 channel |
