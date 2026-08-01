# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserAllAlias.java）

移除所有运行时创建的别名、按键绑定和变量，并记录一条汇总。

## 语法

```java
public com.github.prohect.alias.builtinAlias.UnloadUserAllAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 保存当前 `silentMode` 并将其设置为 `true`。
2. **卸载运行时别名：** 实例化并运行 `UnloadUserAliasesAlias`。通过过滤注册表中 `!isFromCFG() && !isPredefined()` 的条目统计移除的别名数量。
3. **卸载运行时绑定：** 实例化并运行 `UnloadUserBindsAlias`。通过过滤 `BINDING_PLUS` 中 `!fromCFG()` 的条目统计移除的绑定数量。
4. **卸载运行时变量：** 统计用户变量（不在 `CFG_VARIABLES` 且不在 `CFG_CONTAINER_SLOT_VARIABLES` 中的），然后实例化并运行 `UnloadUserVarsAlias`。总数同时包含通用变量和容器槽位变量。
5. 恢复原来的 `silentMode`。
6. 如果原本不在静默模式，记录汇总。

**返回值：** `this`（流畅式返回）。

**副作用：** 从各自注册表中移除所有运行时创建的条目。CFG 加载的条目和内置条目被保留。

**变量计数：** 卸载前的计数结合了：
- `GENERAL_VARIABLES` 中不在 `CFG_VARIABLES` 跟踪内的通用变量。
- `CONTAINER_SLOT_VARIABLES` 中不在 `CFG_CONTAINER_SLOT_VARIABLES` 跟踪内的容器槽位变量。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadUserAllAlias](UnloadUserAllAlias.md) | 类概览 |
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/run.md) | 移除 CFG 加载的条目（反向操作） |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
