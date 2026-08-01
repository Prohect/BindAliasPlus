# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAliasesAlias.java）

移除所有从配置文件加载的用户别名。

## 语法

```java
public com.github.prohect.alias.builtinAlias.UnloadCFGAliasesAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 创建 `toRemove` 列表。
2. 遍历 `Alias.aliasesWithoutArgs`：对于每个别名为 `isFromCFG() == true` 的 `UserAlias` 的条目，将其名称添加到 `toRemove`。
3. 从 `aliasesWithoutArgs` 中移除每个名称。
4. 如果不在静默模式，记录数量：`"Removed {count} autoloaded alias(es)"`。

**返回值：** `this`（流畅式返回）。

**副作用：** 从全局 `aliasesWithoutArgs` 注册表中移除 CFG 加载的 `UserAlias` 实例。运行时创建的别名和内置别名不受影响。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGAliasesAlias](UnloadCFGAliasesAlias.md) | 类概览 |
| [UnloadUserAliasesAlias](../UnloadUserAliasesAlias.java/run.md) | 移除运行时别名（反向操作） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
