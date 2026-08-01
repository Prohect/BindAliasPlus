# BindAliasClient（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public class com.github.prohect.BindAliasClient implements net.fabricmc.api.ClientModInitializer
```

## 静态初始化

_无。_

## 备注

`BindAliasClient` 是模组的**核心**类。它是单例（`INSTANCE`），实现了 `ClientModInitializer`，因此 Fabric 会在客户端启动时调用一次 `onInitializeClient()`。每个别名、每个按键绑定、每个聊天命令以及每个生命周期钩子都从这里发起。

生命周期：
1. **静态初始化** —— 字段被急切初始化（队列、映射、日志器、路径）。
2. **`onInitializeClient()`** —— 注册约 60 个内置别名、7 个客户端命令、刻计数器、加入时自动加载 CFG、断开连接清理，并启动 MCP HTTP 服务器。
3. **刻循环** —— `ClientTickEvents.START_CLIENT_TICK` 使 `currentTick` 递增。`MinecraftClientMixin` 每刻消费 `KEY_QUEUE`，将按键事件分发给对应的别名。
4. **加入世界** —— 调用 `loadCFG()`，MCP 通道被初始化。
5. **断开连接** —— 清除锁定、排空队列、重置静默模式。
6. **关闭** —— `ClientLifecycleEvents.CLIENT_STOPPING` 停止 MCP 服务器。

该类以 public static 字段持有所有全局模组状态（`KEY_QUEUE`、`BINDING_PLUS`、`currentScreen`、`silentMode`、刻计数器），使 mixin 和别名无需注入即可访问它们。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [Alias](../alias/Alias.java/Alias.md) | 别名接口 —— `onInitializeClient` 填充的注册映射 |
| [UserAlias](../alias/UserAlias.java/UserAlias.md) | 用户别名执行器 —— 当按键绑定和命令执行别名链时被调用 |
| [BindAliasKeyBinding](BindAliasKeyBinding.md) | 存储在 `BINDING_PLUS` 中的按键→别名映射 |
| [KeyPressed](KeyPressed.md) | 排队在 `KEY_QUEUE` 中的事件 |
| [McpHttpServer](../mcp/McpHttpServer.java/McpHttpServer.md) | 在 `onInitializeClient` 中启动的 MCP 服务器 |
| [MinecraftClientMixin](../mixin/MinecraftClientMixin.java/MinecraftClientMixin.md) | 消费 `KEY_QUEUE` 的刻驱动者 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
