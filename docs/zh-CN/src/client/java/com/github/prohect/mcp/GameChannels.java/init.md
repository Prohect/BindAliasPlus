# init 方法（src/client/java/com/github/prohect/mcp/GameChannels.java）

## 语法

```java
public static void init()
```

## 备注

在 `"bind-alias"` 日志器上注册 Log4j appender，将模组自身日志输出送入 [`MOD`](MOD.md) channel。可安全地多次调用（内部通过 `synchronized` 块和 `initialized` 标志去重）。

该 appender 只捕获 `"bind-alias"` 日志器的消息，跳过根日志器中所有 Fabric/mixin/渲染噪音。子 `LoggerConfig` 以 `additive = true` 构建，因此正常的日志输出（控制台、`latest.log`）保持不变——appender 是对现有日志管道的补充而非替代。失败时（例如 Log4j 内部实现变动）异常被静默吞掉——日志捕获是尽力而为的。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [MOD](MOD.md) | 本 appender 供数的 channel |
| [post](post.md) | 日志消息如何发布到 channel |
