# start 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
public static void start()
```

## 备注

在守护线程上启动 HTTP 服务器。端口选择：

1. 读取 `"bind-alias.mcp.port"` 系统属性，默认 `8095`。
2. 尝试绑定该端口；若被占用，递增并重试，最多 `MAX_PORT_ATTEMPTS`（10）次。
3. 成功后，把实际端口存入静态 `port` 字段并记录日志。
4. 注册所有 endpoint（端点）处理器并启动执行器。

服务器配置了读超时（`TIMEOUT_SECONDS` = 120）以防连接挂起。在客户端模组初始化期间由 `BindAliasClient.onInitializeClient()` 调用。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [stop](stop.md) | 服务器关闭 |
| [port](port.md) | 返回实际绑定的端口 |
