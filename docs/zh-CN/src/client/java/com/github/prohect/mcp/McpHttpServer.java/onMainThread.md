# onMainThread 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
private static <T> T onMainThread(CheckedSupplier<T> supplier) throws Exception
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `supplier` | `CheckedSupplier<T>` | 包装可能抛出 `Exception` 的操作的函数式接口 |

## 返回值

supplier 在主线程上计算出的值。

## 备注

确保给定操作在 Minecraft 主线程（渲染线程）上执行。如果调用线程已经是主线程，则 supplier 内联运行。否则，向 `MinecraftClient.getInstance()` 提交一个 `CompletableFuture` 并阻塞至完成。所有 HTTP 处理器都使用它以保证游戏状态访问的线程安全。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [handleState](handleState.md) | 使用 onMainThread 进行状态收集 |
| [handleRunAlias](handleRunAlias.md) | 使用 onMainThread 进行别名执行 |
