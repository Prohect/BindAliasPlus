# GameChannels

基于 channel（消息通道）的消息中心，为 MCP 响应 envelope（状态信封）供数。维护四个命名 channel（`chat`、`mod`、`sound`、`recipe`），各自拥有独立缓冲区，支持线程安全的发布，并对重复声音进行合并（coalescing）。挂在 `"bind-alias"` 日志器上的 Log4j appender 向 `mod` channel 供数。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [CHAT](CHAT.md) | `String`（静态，`"chat"`） | 游戏聊天消息（服务器/系统/玩家）的 channel |
| [MOD](MOD.md) | `String`（静态，`"mod"`） | 模组自身日志输出的 channel |
| [SOUND](SOUND.md) | `String`（静态，`"sound"`） | 声音事件的 channel（合并） |
| [RECIPE](RECIPE.md) | `String`（静态，`"recipe"`） | 新解锁配方通知的 channel |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [post](post.md) | `static void post(String channel, String message)` | 向 channel 发布一条全新的、独立的消息条目 |
| [postCoalescing](postCoalescing.md) | `static void postCoalescing(String channel, String key, String message)` | 按键合并发布消息——若存在同 key 的未排空条目，则就地更新 |
| [drain](drain.md) | `static Map<String, List<String>> drain()` | 返回自上次 drain 以来的新消息；无新消息时零开销 |
| [resetAll](resetAll.md) | `static void resetAll()` | 将所有 channel 标记为已读（在加入世界时调用） |
| [init](init.md) | `static void init()` | 为 `MOD` channel 注册 Log4j appender |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [StateTracker](StateTracker.java/README.md) | 将 channel 排空进 MCP 响应 envelope |
| [ChatComponentMixin](../../mixin/client/ChatComponentMixin.java/README.md) | 为 `CHAT` 供数 |
| [SoundCapture](SoundCapture.java/README.md) | 为 `SOUND` 供数 |
| [ClientPacketListenerMixin](../../mixin/client/ClientPacketListenerMixin.java/README.md) | 为 `RECIPE` 供数 |
