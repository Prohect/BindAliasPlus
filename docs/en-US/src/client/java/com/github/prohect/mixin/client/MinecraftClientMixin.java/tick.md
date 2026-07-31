# tick method (src/client/java/com/github/prohect/mixin/client/MinecraftClientMixin.java)

## Syntax

```java
@Inject(at = @At("HEAD"), method = "tick")
private void tick(CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `ci` | `CallbackInfo` | Unused callback |

## Remarks

Injected at `HEAD` of `MinecraftClient#tick()`. Executes four subsystems in order each client tick:

1. **Screen tracking**: `BindAliasClient.currentScreen = McScreenHelper.getCurrentScreen(MinecraftClient.getInstance())` — updates the global current-screen reference for alias screen-type checks.

2. **WaitAlias deferred tasks**: iterates `WaitAlias.tasksWaiting` from index 0 to `size`. For each task at index `i`, calls `task.tick()` which returns `1` if the task completed (should be removed) or `0` if still pending. Completed tasks are compacted out by decrementing `size`. Uses a manual index loop with `size` adjustment to handle removal correctly.

3. **Continuous drop**: looks up the `"builtinDrop"` alias from `Alias.aliasesWithArgs_notSuggested`, casts to `DropAlias`, and calls `dropAlias.tickDrop()`. This drives per-tick drop logic for both container screens and 3D gameplay when the drop alias is held.

4. **MCP nap countdown**: calls `McpHttpServer.tickNapTasks()` — decrements nap task counters and completes futures when they reach zero. Placed last so the deferred envelope capture reflects all state changes from WaitAlias chains and drop ticks.

## See Also

| Item | Description |
|------|-------------|
| [WaitAlias.tasksWaiting](../../../alias/builtinAlias/WaitAlias.java/tasksWaiting.md) | The list of pending wait tasks |
| [DropAlias.tickDrop](../../../alias/builtinAlias/DropAlias.java/tickDrop.md) | The continuous drop driver |
| [McpHttpServer.tickNapTasks](../../../mcp/McpHttpServer.java/tickNapTasks.md) | The nap task countdown |
| [McScreenHelper.getCurrentScreen](../../../util/McScreenHelper.java/getCurrentScreen.md) | Cross-version screen access |
