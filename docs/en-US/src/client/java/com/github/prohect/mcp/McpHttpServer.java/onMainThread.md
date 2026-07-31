# onMainThread method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static <T> T onMainThread(CheckedSupplier<T> supplier) throws Exception
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `supplier` | `CheckedSupplier<T>` | A functional interface wrapping an operation that may throw `Exception` |

## Return value

The value computed by the supplier on the main thread.

## Remarks

Ensures the given operation executes on the Minecraft main thread (the render thread). If the calling thread is already the main thread, the supplier runs inline. Otherwise, submits a `CompletableFuture` to `Minecraft.getInstance()` and blocks until completion. Used by all HTTP handlers to guarantee thread-safety for game-state access.

## See Also

| Item | Description |
|------|-------------|
| [handleState](handleState.md) | Uses onMainThread for state collection |
| [handleRunAlias](handleRunAlias.md) | Uses onMainThread for alias execution |
