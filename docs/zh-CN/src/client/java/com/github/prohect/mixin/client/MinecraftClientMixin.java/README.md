# MinecraftClientMixin

针对 `net.minecraft.client.Minecraft` 的 mixin。每刻集成的中心点：跟踪当前界面，驱动 WaitAlias 延迟任务，驱动持续丢弃，并倒计时 MCP nap 响应计时器。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [tick](tick.md) | `void tick(CallbackInfo ci)` | `Minecraft#tick()` 的 `HEAD` 处的 `@Inject` —— 按顺序运行界面跟踪、WaitAlias 计时器、DropAlias 刻和 MCP nap 倒计时 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [WaitAlias](../../../alias/builtinAlias/WaitAlias.java/README.md) | 在此刻进的延迟任务系统 |
| [DropAlias](../../../alias/builtinAlias/DropAlias.java/README.md) | 由此驱动的持续丢弃别名 |
| [McpHttpServer](../../../mcp/McpHttpServer.java/README.md) | 其 nap 任务在此倒计时的 MCP 服务器 |
| [McScreenHelper](../../../util/McScreenHelper.java/README.md) | 跨版本界面访问工具 |
