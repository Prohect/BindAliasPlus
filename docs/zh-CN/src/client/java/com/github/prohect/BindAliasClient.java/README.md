# BindAliasClient

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [INSTANCE](INSTANCE.md) | `BindAliasClient` | 单例实例——急切初始化 |
| [cfgPath](cfgPath.md) | `Path` | CFG 文件的路径（`config/bindaliasplus.cfg`） |
| [KEY_QUEUE](KEY_QUEUE.md) | `ArrayDeque<KeyPressed>` | mixin 产生的待处理按键事件的 FIFO 队列 |
| [BINDING_PLUS](BINDING_PLUS.md) | `Map<InputUtil.Key, BindAliasKeyBinding>` | 所有活动的按键→别名绑定 |
| [LOGGER](LOGGER.md) | `Logger` | 名为 `"bind-alias"` 的 SLF4J 日志器 |
| [currentTick](currentTick.md) | `long` | 单调递增的 tick 计数器，每个客户端刻递增 |
| [joinTick](joinTick.md) | `long` | 玩家上次加入世界的 tick（从未加入则为 `-1`） |
| [silentMode](silentMode.md) | `boolean` | 为 true 时抑制聊天中的模组反馈消息 |
| [currentScreen](currentScreen.md) | `Screen` | 缓存的当前界面，每个 tick 更新 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [onInitializeClient](onInitializeClient.md) | `void onInitializeClient()` | **重点入口**——注册所有内置别名、命令、生命周期钩子，并启动 MCP 服务器 |
| [tickPrefix](tickPrefix.md) | `static String tickPrefix()` | 返回 `[client_tick:N]` 日志前缀（自加入以来的刻数） |
| [loadCFG](loadCFG.md) | `void loadCFG()` | 读取并执行 CFG 文件中的所有行 |
| [commandVarExecute](commandVarExecute.md) | `int commandVarExecute(String, String)` | 处理 `var\name\source`——创建/更新模组变量 |
| [commandUnbindExecute](commandUnbindExecute.md) | `int commandUnbindExecute(String)` | 处理 `unbind\key`——移除按键绑定 |
| [commandBindExecute](commandBindExecute.md) | `int commandBindExecute(String, String)` | 处理 `bind\key\definition`——创建内联别名并绑定按键 |
| [commandAliasExecute](commandAliasExecute.md) | `int commandAliasExecute(String, String)` | 处理 `alias\name_with_definition`——创建/重定义用户别名 |
| [commandBindByAliasNameExecute](commandBindByAliasNameExecute.md) | `int commandBindByAliasNameExecute(String, String)` | 处理 `bindByAliasName\key\aliasName`——将按键绑定到已存在的别名 |
| [getSuggestions4aliasDefinitionCompletableFuture](getSuggestions4aliasDefinitionCompletableFuture.md) | `static CompletableFuture<Suggestions> ...` | 为别名定义参数提供 Tab 补全建议 |
| [parseKey](parseKey.md) | `InputUtil.Key parseKey(String)` | 将按键名字符串转换为 Minecraft 的 `InputUtil.Key` |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [Alias](../alias/Alias.java/Alias.md) | 别名接口——在 `onInitializeClient` 中填充的注册表 |
| [UserAlias](../alias/UserAlias.java/UserAlias.md) | 由命令处理器调用的用户定义别名运行器 |
| [BindAliasKeyBinding](BindAliasKeyBinding.md) | 由 `commandBindExecute` 存入 `BINDING_PLUS` 的记录 |
| [KeyPressed](KeyPressed.md) | 由 mixin 排入 `KEY_QUEUE` 的记录 |
| [McpHttpServer](../mcp/McpHttpServer.java/McpHttpServer.md) | 在 `onInitializeClient` 中启动的 MCP HTTP 服务器 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
