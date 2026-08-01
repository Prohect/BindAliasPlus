# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

标准运行时重载——存储变量且不进行 CFG 自动加载跟踪。

## 语法

```java
public com.github.prohect.alias.builtinAlias.VarAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 格式：`"varName\source"`——例如 `"mySlot\hotbarSlot"`、`"count\itemsOfSlot2"` |

## 备注

**算法：**

1. 通过 `getDefinitionSplits()` 按 `\`（反斜杠）拆分参数。
2. 验证至少有 2 个部分（varName 和 source）。如果不是，记录错误。
3. 提取并去除 `varName` 和 `source` 的空白。
4. 通过 `isValidVarName()` 验证 `varName`。以数字开头则拒绝。
5. 调用 `getValueFromSource(source)` 将源解析为 `Number`。
6. 存储在 `GENERAL_VARIABLES.put(varName, value)` 中。
7. 处理容器槽位源：如果 source 是 `cN`，存储在 `CONTAINER_SLOT_VARIABLES` 中；否则，移除该名称的任何现有容器槽位条目。
8. 记录 info：`"Variable '{name}' set to {value}"`。

**返回值：** `this`（流畅式返回）。

**副作用：** 在 `GENERAL_VARIABLES` 以及可能的 `CONTAINER_SLOT_VARIABLES` 中存储变量。**不**添加到 CFG 跟踪集合。

**无界面抑制：** 在任何界面上都能工作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [VarAlias](VarAlias.md) | 类概览 |
| [run（自动加载重载）](run.md) | fromAutoload 变体 |
| [getValueFromSource](getValueFromSource.md) | 源解析 |
| [isValidVarName](isValidVarName.md) | 名称验证 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
