# ReloadCFGAlias（src/client/java/com/github/prohect/alias/builtinAlias/ReloadCFGAlias.java）

在运行时重新加载配置文件（CFG）的一次性别名。继承 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.ReloadCFGAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.ReloadCFGAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `reloadCFG`（内部，以 `reloadCFG` 形式暴露）。

**行为：** 调用 `BindAliasClient.INSTANCE.loadCFG()`，重新读取并处理配置文件。这会加载 CFG 中定义的别名、按键绑定和变量到运行中的游戏，无需重启。

**用例：** 允许实时编辑配置文件——修改后运行 `reloadCFG` 即可在游戏中应用。MCP 服务器的 `writeCFG` 工具也会使用它，该工具写入新的 CFG 内容并重新加载。

**无界面抑制：** 即使在文本输入界面或任何其他界面打开时，此别名也能工作——它是配置操作，不是游戏输入。

**与卸载别名的关系：** 要在重新加载前完全重置 CFG 加载的状态，请使用 `unloadCFGAll` 后跟 `reloadCFG`。`writeCFG` MCP 工具会在重新加载前自动卸载。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md) | 移除所有 CFG 加载的条目 |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md) | 仅移除 CFG 加载的别名 |
| [BindAliasClient](../../../BindAliasClient.java/BindAliasClient.md) | 提供 `loadCFG()` 的客户端入口点 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
