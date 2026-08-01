# onMainThread 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
private static <T> T onMainThread(CheckedSupplier<T> supplier) throws Exception
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `supplier` | `CheckedSupplier<T>` | 包装可能抛出 `Exception` 操作的功能接口 |

## 返回值

supplier 在主线程上计算出的值。

## 备注

确保给定操作在 Minecraft 主线程（渲染线程）上执行。若调用线程已是主线程，则 supplier 内联运行。否则向 `Minecraft.getInstance()` 提交 `CompletableFuture` 并阻塞至完成。所有 HTTP 处理器都用它来保证游戏状态访问的线程安全。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [handleState](handleState.md) | 使用 onMainThread 进行状态收集 |
| [handleRunAlias](handleRunAlias.md) | 使用 onMainThread 执行别名 |
