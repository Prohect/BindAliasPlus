# commandVarExecute 方法（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
private int commandVarExecute(java.lang.String, java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `varName` | `String` | 要创建或更新的变量名称 |
| `source` | `String` | 值来源 —— `hotbarSlot`、`itemsOfSlot0`-`itemsOfSlot9`、`yaw`、`pitch`、`cN` 或字面数字 |

## 备注

通过委托给 `VarAlias.run(varName + "\\" + source)` 来创建或更新模组变量。执行后，检查变量是否已成功存储在 `GENERAL_VARIABLES` 或 `CONTAINER_SLOT_VARIABLES` 中。若成功且不在静默模式下，则向玩家的聊天界面发送确认消息。若变量未存入任一映射，则报告失败。

成功返回 `1`，失败返回 `0`。私有的 3 参数重载增加 `fromAutoload` 以跟踪 CFG 来源的变量，供 `unloadCFGVars` 清理。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [VarAlias](../alias/builtinAlias/VarAlias.java/VarAlias.md) | 变量存储别名 |
| [silentMode](silentMode.md) | 为 `true` 时抑制成功/失败消息 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
