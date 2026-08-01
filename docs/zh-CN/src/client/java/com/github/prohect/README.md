# prohect

BindAlias 模组的根包。包含客户端入口点（`BindAliasClient`）、按键绑定基础设施、别名执行引擎、MCP HTTP 服务器、mixin 和工具类。

## 推荐阅读顺序

| 顺序 | 条目 | 理由 |
|-------|------|--------|
| 1 | [alias](alias/README.md) | 核心抽象 —— 所有别名类型、注册映射、解析 |
| 2 | [BindAliasClient.java](BindAliasClient.java/README.md) | 客户端初始化 —— 连接别名、按键绑定、界面黑名单、CFG 加载 |
| 3 | [BindAliasKeyBinding.java](BindAliasKeyBinding.java/README.md) | 按键绑定包装 —— 按键按下如何变成别名调用 |
| 4 | [KeyPressed.java](KeyPressed.java/README.md) | 按键事件记录 —— 供刻循环使用的排队按键事件 |
| 5 | [mcp](mcp/README.md) | MCP HTTP JSON-RPC 服务器 —— 别名的外部控制 |
| 6 | [mixin](mixin/README.md) | Mixin —— 刻驱动、按键路由、界面跟踪、数据包钩子 |
| 7 | [util](util/README.md) | 工具 —— 颜色名称、槽位辅助、界面相关辅助 |

## 目录

| 名称 | 说明 |
|------|-------------|
| [alias](alias/README.md) | 别名类型层级、注册映射、解析工具 |
| [BindAliasClient.java](BindAliasClient.java/README.md) | 客户端模组初始化器 —— 注册所有内置别名和按键绑定 |
| [BindAliasDataGenerator.java](BindAliasDataGenerator.java/README.md) | 模组的数据生成入口点 |
| [BindAliasKeyBinding.java](BindAliasKeyBinding.java/README.md) | 自定义按键绑定 —— 将按键映射到要执行的别名名称 |
| [KeyPressed.java](KeyPressed.java/README.md) | 排队按键事件的记录（按键码、动作、修饰键） |
| [mcp](mcp/README.md) | MCP HTTP 服务器、状态跟踪、截图、配方辅助 |
| [mixin](mixin/README.md) | 注入 Minecraft 客户端类的 mixin |
| [util](util/README.md) | 工具类（颜色、界面辅助、槽位辅助） |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [com/github/prohect/alias](alias/README.md) | 别名执行引擎 —— 所有用户可见的命令 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
