# tickNapTasks method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
public static void tickNapTasks()
```

## Remarks

Called each client tick from `MinecraftClientMixin.tick()`. Iterates the `NAP_TASKS` list and decrements each task's `ticksLeft` counter. When a counter reaches zero, completes the task's `CompletableFuture<String>` with the final envelope (captured via `StateTracker.finish` on the earlier begun envelope). Cancelled tasks (flag set during `stop()`) are also completed (with a cancellation response).

A safety timeout prevents hang: if any nap task has been pending longer than `NAP_TIMEOUT_MARGIN_MS` (5 minutes of wall-clock time), it is forcibly completed. Tasks whose `ticksLeft` reaches zero are removed from the list on completion.

## See Also

| Item | Description |
|------|-------------|
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | The caller each client tick |
| [handleRunAlias](handleRunAlias.md) | Creates nap tasks when the `nap` parameter is provided |
| [StateTracker.finish](StateTracker.java/finish.md) | Captures the final envelope for completed nap tasks |
