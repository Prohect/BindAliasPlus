# onInitializeClient 方法（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public void onInitializeClient()
```

## 参数

_无。_

## 备注

由 Fabric 在客户端启动时调用一次。这是整个模组的唯一初始化入口点。它按顺序执行以下操作：

1. **注册约 35 个带参数的内置别名** —— 每个都通过构建器链（`new SomeAlias().putToAliasesWithArgs()`）注册，如 `slot`、`yaw`、`pitch`、`say`、`sendCommand`、`var`、`alias`、`bind`、`unbind`、`wait`、`swapSlot`、`setYaw`、`setPitch`、`log`、`lock`、`runAlias`、`applyRecipe` 等。`_notSuggested` 变体从命令建议中省略该别名：`+attack`、`+use`、`+forward`、`+back`、`+left`、`+right`、`+jump`、`+sneak`、`+sprint`、`+drop`、`+screenshot`、`+playerList`、`+freeCursor`、`esc`、`+advancements`、`+debugOverlay`、`+silent`、`FPS/TPS/TPS2`、`+openInventory`、`+lockKey`/`-lockKey`、`reapply`。

2. **注册约 5 个不带参数的内置别名** —— `cyclePerspective`、`swapHand`、`pickItem`、`toggleInventory`、`shutdown`、`reloadCFG`，以及各种 `unloadCFG*` / `unloadUser*` 清理别名。

3. **注册 `+`/`-` 开关包装器** —— 为每个布尔动作创建 `UserAlias` 条目（如 `+attack` → `builtinAttack\1`、`-attack` → `builtinAttack\0`）。这些才是用户实际通过按键绑定调用的别名。

4. **创建 CFG 文件** —— 若 `config/bindaliasplus.cfg` 不存在，则创建一个空文件。

5. **注册刻计数器** —— `ClientTickEvents.START_CLIENT_TICK` 使 `currentTick` 递增。

6. **注册加入钩子** —— `ClientPlayConnectionEvents.JOIN` 设置 `joinTick`，初始化 MCP 通道状态，重置跟踪器，注册 `SoundCapture`，并调用 `loadCFG()` 恢复持久化配置。

7. **注册断开连接钩子** —— 清除所有锁定，排空 `KEY_QUEUE`，重置 `silentMode`。

8. **注册客户端命令** —— `/alias`、`/bindByAliasName`、`/bind`、`/unbind`、`/reloadCFG`、`/unloadCFGAliases`、`/unloadCFGBinds`、`/unloadCFGVars`、`/unloadCFGAll`、`/unloadUserAliases`、`/unloadUserBinds`、`/unloadUserVars`、`/unloadUserAll`、`/var`、`/runAlias` —— 全部通过 `getSuggestions4aliasDefinitionCompletableFuture` 提供参数建议。

9. **启动 MCP HTTP 服务器** —— `McpHttpServer.start()`。

10. **注册关闭钩子** —— `ClientLifecycleEvents.CLIENT_STOPPING` 停止 MCP 服务器。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [Alias](../alias/Alias.java/Alias.md) | 在此填充的注册映射（`aliasesWithArgs`、`aliasesWithoutArgs` 等） |
| [McpHttpServer](../mcp/McpHttpServer.java/McpHttpServer.md) | 在初始化结束时启动 |
| [loadCFG](loadCFG.md) | 加入世界时调用以恢复持久化配置 |
| [getSuggestions4aliasDefinitionCompletableFuture](getSuggestions4aliasDefinitionCompletableFuture.md) | 为命令参数提供 tab 补全 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
