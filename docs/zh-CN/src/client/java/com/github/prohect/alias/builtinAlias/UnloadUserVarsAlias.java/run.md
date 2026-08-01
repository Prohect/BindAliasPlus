# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserVarsAlias.java）

从通用变量和容器槽位变量映射中移除所有运行时创建的变量。

## 语法

```java
public com.github.prohect.alias.builtinAlias.UnloadUserVarsAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 创建 `toRemoveGeneral` 列表：`GENERAL_VARIABLES` 中所有不在 `CFG_VARIABLES` 中的键。
2. 创建 `toRemoveContainer` 列表：`CONTAINER_SLOT_VARIABLES` 中所有不在 `CFG_CONTAINER_SLOT_VARIABLES` 中的键。
3. 从 `GENERAL_VARIABLES` 中移除每个 `toRemoveGeneral` 名称——计数 `generalCount`。
4. 从 `CONTAINER_SLOT_VARIABLES` 中移除每个 `toRemoveContainer` 名称——计数 `containerCount`。
5. 如果不在静默模式，记录：`"Removed {total} runtime variable(s) ({generalCount} general, {containerCount} container_slot)"`。

**返回值：** `this`（流畅式返回）。

**副作用：** 移除运行时创建的通用变量和容器槽位引用。两个映射中的 CFG 加载变量被保留。

**关键行为：** 与 `UnloadCFGVarsAlias` 不同，此别名也会清理 `CONTAINER_SLOT_VARIABLES`，是唯一直接接触容器槽位引用的卸载别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadUserVarsAlias](UnloadUserVarsAlias.md) | 类概览 |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/run.md) | 移除 CFG 加载的变量（反向操作，仅通用） |
| [VarAlias](../VarAlias.java/VarAlias.md) | 变量系统 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
