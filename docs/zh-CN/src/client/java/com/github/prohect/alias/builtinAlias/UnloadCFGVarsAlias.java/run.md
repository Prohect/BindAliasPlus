# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGVarsAlias.java）

移除所有从配置文件加载的变量。

## 语法

```java
public com.github.prohect.alias.builtinAlias.UnloadCFGVarsAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 从 `VarAlias.CFG_VARIABLES` 中的所有名称创建 `toRemove` 列表。
2. 对每个名称：先从 `GENERAL_VARIABLES` 中移除，然后从 `CFG_VARIABLES` 中移除。
3. 如果不在静默模式，记录数量。

**返回值：** `this`（流畅式返回）。

**副作用：** 从变量存储中移除 CFG 加载的通用变量。容器槽位变量**不会**被清理——它们使用单独的跟踪集合（`CFG_CONTAINER_SLOT_VARIABLES`）。

**限制：** 只清理 `GENERAL_VARIABLES` 和 `CFG_VARIABLES`。**不**清理 `CONTAINER_SLOT_VARIABLES` 或 `CFG_CONTAINER_SLOT_VARIABLES`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGVarsAlias](UnloadCFGVarsAlias.md) | 类概览 |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/run.md) | 移除运行时变量（反向操作） |
| [VarAlias](../VarAlias.java/VarAlias.md) | 变量系统 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
