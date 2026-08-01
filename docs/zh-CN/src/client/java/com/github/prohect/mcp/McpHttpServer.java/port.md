# port 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
public static int port()
```

## 返回值

服务器实际绑定的端口；服务器启动失败时返回 `-1`。

## 备注

返回 `start()` 期间分配的端口。若初始端口被占用而使用了回退端口，可能与配置的默认值不同。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [start](start.md) | 分配端口的地方 |
