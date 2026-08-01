# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserAliasesAlias.java）

移除所有在运行时创建（非 CFG、非预定义）的用户别名。

## 语法

```java
public com.github.prohect.alias.builtinAlias.UnloadUserAliasesAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 创建 `toRemove` 列表。
2. 遍历 `Alias.aliasesWithoutArgs`：对每个 `isFromCFG() == false` 且 `isPredefined() == false` 的 `UserAlias`，将名称添加到 `toRemove`。
3. 从 `aliasesWithoutArgs` 中移除每个名称。
4. 如果不在静默模式，记录数量。

**返回值：** `this`（流畅式返回）。

**副作用：** 从全局注册表中移除运行时创建的用户别名。CFG 加载的别名和预定义别名被保留。

**过滤标准：** 只有既非 CFG 又非预定义的别名才会被移除。这确保：
- CFG 加载的别名保留（受保护，不被运行时清理）。
- 以 `isPredefined() == true` 的 `UserAlias` 暴露的内置别名保留。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadUserAliasesAlias](UnloadUserAliasesAlias.md) | 类概览 |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/run.md) | 移除 CFG 加载的别名（反向操作） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
