# UnloadUserBindsAlias（src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserBindsAlias.java）

移除所有在运行时创建（非 CFG）的按键绑定的一次性别名。继承 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.UnloadUserBindsAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadUserBindsAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `unloadUserBinds`（内部，以 `unloadUserBinds` 形式暴露）。

**行为：** 遍历 `BindAliasClient.BINDING_PLUS`，移除每个 `fromCFG() == false` 的绑定（即运行时通过 `/bind` 或 `/bindByAliasName` 命令创建的）。同时从 `Alias.aliasesWithoutArgs_fromBindCommand` 中清理关联的别名。

**不受影响的内容：**
- CFG 加载的绑定（`fromCFG() == true`）。
- `aliasesWithoutArgs` 中的别名（使用 `unloadUserAliases`）。
- 变量（使用 `unloadUserVars`）。

**静默模式：** 当 `BindAliasClient.silentMode` 为 false 时，记录 `"[unloadUserBinds] Removed {count} runtime keybinding(s)"`（无刻前缀）。

**清理：** 绑定命令关联的自动创建别名也会从 `aliasesWithoutArgs_fromBindCommand` 中移除。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/UnloadUserAllAlias.md) | 移除所有运行时创建的条目 |
| [UnloadUserAliasesAlias](../UnloadUserAliasesAlias.java/UnloadUserAliasesAlias.md) | 移除运行时创建的别名 |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/UnloadUserVarsAlias.md) | 移除运行时创建的变量 |
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | 移除 CFG 加载的绑定（反向操作） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
