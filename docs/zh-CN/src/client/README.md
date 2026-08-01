# client

**仅客户端**源集。这里的所有代码都只在 Fabric 客户端上运行 —— 绝不在专用服务器上运行。别名系统、按键绑定基础设施、MCP HTTP 服务器和 Fabric mixin 都位于此处。

## 目录

| 名称 | 说明 |
|------|-------------|
| [java/](java/README.md) | Java 源码根 —— `com.github.prohect` 包树 |

## 关键子系统

| 子系统 | 包 | 说明 |
|-----------|---------|-------------|
| **别名引擎** | `alias/` | 接口层级（`Alias`、`AliasWithArgs`、`AliasWithoutArgs`）和具体的内置别名 —— 核心命令宏系统 |
| **按键绑定** | `prohect/` | `BindAliasKeyBinding`、`KeyPressed`，以及连接按键与别名的核心协调者 `BindAliasClient` |
| **MCP 服务器** | `mcp/` | 用于 AI 代理控制的本地 HTTP JSON-RPC 服务器 —— 游戏状态快照、截图、配方书和声音捕获 |
| **Mixin** | `mixin/` | 注入原版 Minecraft 类的刻驱动、按键路由、界面跟踪、移动和静默模式 |
| **工具** | `util/` | 辅助 —— 锁定管理、界面类型检查和其他共享便捷方法 |

## 入口点

**`BindAliasClient.onInitializeClient()`** 是唯一的客户端初始化点。它注册约 60 个内置别名、7 个客户端命令、生命周期事件处理器，并启动 MCP HTTP 服务器。

*Documented for Commit: [7af96e2e0fb4d49cea99ff20bbd36dcfa659ffa3](https://github.com/Prohect/BindAlias/tree/7af96e2e0fb4d49cea99ff20bbd36dcfa659ffa3)*
