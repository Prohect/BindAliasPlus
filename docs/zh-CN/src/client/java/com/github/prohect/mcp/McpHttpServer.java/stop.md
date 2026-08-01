# stop 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
public static void stop()
```

## 备注

以 2 秒宽限期停止 HTTP 服务器。通过把待处理 nap 任务的 `cancelled` 标志设为 `true` 取消它们。在模组关闭期间调用。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [start](start.md) | 服务器启动 |
