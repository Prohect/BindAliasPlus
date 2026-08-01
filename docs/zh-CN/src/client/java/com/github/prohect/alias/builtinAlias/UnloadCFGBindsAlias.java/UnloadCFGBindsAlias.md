# UnloadCFGBindsAlias（src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGBindsAlias.java）

移除所有从配置文件（CFG）加载的按键绑定的一次性别名。继承 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.UnloadCFGBindsAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadCFGBindsAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `unloadCFGBinds`（内部，以 `unloadCFGBinds` 形式暴露）。

**行为：** 遍历 `BindAliasClient.BINDING_PLUS`，移除每个 `fromCFG()` 返回 true 的绑定。同时从 `Alias.aliasesWithoutArgs_fromBindCommand` 中清理关联的别名——绑定存储的 `aliasNameOnKeyPressed()` 和 `aliasNameOnKeyReleased()` 中的任何别名名称都会从绑定命令注册表中移除。

**不受影响的内容：**
- 运行时创建的绑定（通过 `/bind` 或 `/bindByAliasName` 命令）——它们的 `fromCFG() == false`。
- `aliasesWithoutArgs` 中的别名（使用 `unloadCFGAliases`）。
- 变量（使用 `unloadCFGVars`）。

**静默模式：** 当 `BindAliasClient.silentMode` 为 false 时，记录 `"[unloadCFGBinds] Removed {count} autoloaded keybinding(s)"`（无刻前缀）。静默模式会抑制此日志。

**清理逻辑：** `aliasesWithoutArgs_fromBindCommand` 映射存储绑定按键时自动创建的别名。移除 CFG 绑定时，应同时清理其关联的自动创建别名，以避免残留孤立条目。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md) | 移除所有 CFG 加载的条目 |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md) | 移除 CFG 加载的别名 |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md) | 移除 CFG 加载的变量 |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/UnloadUserBindsAlias.md) | 移除运行时创建的绑定（反向操作） |
| [UnbindAlias](../UnbindAlias.java/UnbindAlias.md) | 基于服务器命令的解绑 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
