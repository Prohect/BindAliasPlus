# UnloadUserAliasesAlias（src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserAliasesAlias.java）

移除所有在运行时创建（非 CFG 且非预定义）的用户别名的一次性别名。继承 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.UnloadUserAliasesAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadUserAliasesAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `unloadUserAliases`（内部，以 `unloadUserAliases` 形式暴露）。

**行为：** 遍历 `Alias.aliasesWithoutArgs`，移除每个 `isFromCFG() == false` 且 `isPredefined() == false` 的 `UserAlias`。这意味着只移除真正在运行时创建的别名——CFG 加载的别名和内置预定义别名被保留。

**不受影响的内容：**
- CFG 加载的别名（`isFromCFG() == true`）。
- 预定义别名（`isPredefined() == true`——这些是以用户可见别名形式暴露的内置别名）。
- 按键绑定（使用 `unloadUserBinds`）或变量（使用 `unloadUserVars`）。

**静默模式：** 当 `BindAliasClient.silentMode` 为 false 时，记录 `"Removed {count} runtime alias(es)"`。

**用例：** 清理测试会话期间创建的别名，而不影响 CFG 中永久配置的别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/UnloadUserAllAlias.md) | 移除所有运行时创建的条目 |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/UnloadUserBindsAlias.md) | 移除运行时创建的按键绑定 |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/UnloadUserVarsAlias.md) | 移除运行时创建的变量 |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md) | 移除 CFG 加载的别名（反向操作） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
