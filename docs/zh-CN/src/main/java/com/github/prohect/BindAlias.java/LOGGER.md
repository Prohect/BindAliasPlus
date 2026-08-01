# LOGGER 字段（src/main/java/com/github/prohect/BindAlias.java）

## 语法

```java
public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MOD_ID)
```

## 备注

模组的 SLF4J 日志器，名为 `"bind-alias"`。用于向 Minecraft 控制台和日志文件写入消息。按照惯例，模组 ID 用作日志器名称，使输出可明确归属。此日志器的输出也被 `GameChannels` 的 Log4j appender（注册在同一个日志器名称上）捕获，并送入 MCP 的 `mod` 通道。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [MOD_ID](MOD_ID.md) | 日志器的名称 |
| [GameChannels.init](../../client/java/com/github/prohect/mcp/GameChannels.java/init.md) | 在此日志器上注册 Log4j appender |
| [GameChannels.MOD](../../client/java/com/github/prohect/mcp/GameChannels.java/MOD.md) | 接收此日志器输出的 MCP 通道 |
