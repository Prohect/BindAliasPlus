# UnloadCFGAliasesAlias（src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAliasesAlias.java）

移除所有从配置文件（CFG）加载的用户别名的一次性别名。继承 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.UnloadCFGAliasesAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadCFGAliasesAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `unloadCFGAliases`（内部，以 `unloadCFGAliases` 形式暴露）。

**行为：** 遍历 `Alias.aliasesWithoutArgs`，移除每个 `isFromCFG()` 返回 true 的 `UserAlias`。CFG 加载的别名是在 `loadCFG()` 处理配置文件期间加载的。

**不受影响的内容：**
- 运行时创建的别名（通过 `alias` 内置别名或 `/alias` 命令）——它们的 `isFromCFG() == false`。
- 预定义/内置别名——它们不是 `UserAlias` 实例。
- 按键绑定（使用 `unloadCFGBinds`）或变量（使用 `unloadCFGVars`）。

**静默模式：** 当 `BindAliasClient.silentMode` 为 false（正常）时，记录包含已移除别名数量的 info 消息。静默模式激活时，不记录任何反馈。

**日志格式：** `"[unloadCFGAliases] Removed {count} autoloaded alias(es)"`（无刻前缀）。

**用例：** 在重新加载 CFG 前使用以确保干净状态，或临时移除 CFG 定义的行为而不影响运行时创建的别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md) | 移除所有 CFG 加载的条目（别名 + 绑定 + 变量） |
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | 移除 CFG 加载的按键绑定 |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md) | 移除 CFG 加载的变量 |
| [UnloadUserAliasesAlias](../UnloadUserAliasesAlias.java/UnloadUserAliasesAlias.md) | 移除运行时创建的别名（反向操作） |
| [ReloadCFGAlias](../ReloadCFGAlias.java/ReloadCFGAlias.md) | 卸载后重新加载 CFG |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
