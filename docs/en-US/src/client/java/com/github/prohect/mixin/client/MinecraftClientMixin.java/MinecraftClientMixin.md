# MinecraftClientMixin (src/client/java/com/github/prohect/mixin/client/MinecraftClientMixin.java)

## Syntax

```java
@Mixin(value = MinecraftClient.class)
public class com.github.prohect.mixin.client.MinecraftClientMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `net.minecraft.client.MinecraftClient` at `tick()`. This is the central per-client-tick integration point that drives multiple subsystems in a well-defined order:

1. **Screen tracking**: updates `BindAliasClient.currentScreen` via [`McScreenHelper.getCurrentScreen()`](../../../util/McScreenHelper.java/getCurrentScreen.md), providing cross-version screen access for all alias screen-type checks.
2. **WaitAlias timer**: iterates `WaitAlias.tasksWaiting`, calling `tick()` on each deferred task and compacting completed tasks out of the list.
3. **Continuous drop**: drives `DropAlias.tickDrop()` — handles per-tick drop logic when the `+drop` alias is held, covering both container screens (via `onMouseClick`) and 3D game view (via `timesPressed`).
4. **MCP nap countdown**: calls `McpHttpServer.tickNapTasks()` — decrements the remaining tick counters for MCP nap (deferred HTTP response) tasks so that responses fire after the requested client-tick delay.

The ordering is intentional: screen tracking runs first so subsequent operations see the correct screen, WaitAlias chains execute next, DropAlias runs third, and MCP nap fires last so the deferred envelope capture reflects all state changes from the current tick.

## See Also

| Item | Description |
|------|-------------|
| [tick](tick.md) | The `@Inject` method |
| [WaitAlias.tasksWaiting](../../../alias/builtinAlias/WaitAlias.java/tasksWaiting.md) | The deferred-task list ticked here |
| [DropAlias.tickDrop](../../../alias/builtinAlias/DropAlias.java/tickDrop.md) | The continuous drop driver called here |
| [McpHttpServer.tickNapTasks](../../../mcp/McpHttpServer.java/tickNapTasks.md) | The nap task countdown called here |
| [McScreenHelper](../../../util/McScreenHelper.java/README.md) | Cross-version screen access utility |
