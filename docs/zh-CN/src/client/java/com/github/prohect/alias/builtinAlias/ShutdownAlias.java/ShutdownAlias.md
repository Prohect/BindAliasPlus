# ShutdownAlias (src/client/java/com/github/prohect/alias/builtinAlias/ShutdownAlias.java)

干净地关闭游戏的一次性别名。继承自 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.ShutdownAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.ShutdownAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `builtinShutdown`（内部，以 `builtinShutdown` 暴露）。

**行为：** 记录关闭消息并调用 `Minecraft.getInstance().stop()` 安排优雅地停止游戏。游戏在当前刻完成后关闭。

**用例：** 专为使用 CFG 自动加载功能的自动化测试流程设计——在配置中定义测试别名、运行它们，然后在结尾调用 `builtinShutdown` 干净地退出。对 MCP agent 终止游戏会话也很有用。

**无界面抑制：** 在任何界面上均有效（这是系统操作，不是游戏输入）。

**要求：** 无——即使玩家为 null 也能工作。

**安全性：** 这是干净的关闭（`stop()`），不是强制退出（`System.exit()`）。它让游戏正确保存状态并关闭资源。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ReloadCFGAlias](../ReloadCFGAlias.java/ReloadCFGAlias.md) | 重新加载配置（另一个系统级别名） |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | 一次性别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
