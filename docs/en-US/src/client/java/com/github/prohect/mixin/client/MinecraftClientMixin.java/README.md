# MinecraftClientMixin

Mixin targeting `net.minecraft.client.Minecraft`. The central per-tick integration point: tracks the current screen, drives WaitAlias deferred tasks, drives continuous drop, and counts down MCP nap response timers.

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [tick](tick.md) | `void tick(CallbackInfo ci)` | `@Inject` at `HEAD` of `Minecraft#tick()` — runs screen tracking, WaitAlias timer, DropAlias tick, and MCP nap countdown in order |

## See Also

| Item | Description |
|------|-------------|
| [WaitAlias](../../../alias/builtinAlias/WaitAlias.java/README.md) | The deferred-task system ticked here |
| [DropAlias](../../../alias/builtinAlias/DropAlias.java/README.md) | The continuous-drop alias driven here |
| [McpHttpServer](../../../mcp/McpHttpServer.java/README.md) | The MCP server whose nap tasks are counted down here |
| [McScreenHelper](../../../util/McScreenHelper.java/README.md) | Cross-version screen access utility |
