# decodeArgs2Alias 方法（src/client/java/com/github/prohect/alias/UserAlias.java）

## 语法

```java
private void decodeArgs2Alias(String args)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 原始定义字符串——以空格分隔的别名链（例如 `"+attack slot\1 wait\5 -attack"`） |

## 备注

将原始定义字符串解析为存储在 `this.aliases` 中的 `ArrayDeque<AliasRecord>`。这是别名链执行的第一步，在 `run()` 和 `runInternal()` 的开头调用。

**算法**：

1. **按 `divider4AliasDefinition`（空格）拆分**：调用 `Alias.getDefinitions(args)` 获取各条定义。
2. **对每条定义**：调用 `Alias.getDefinitionSplits(definition)` 拆分为名称 + 参数标记。
3. **统计非空白标记**：过滤掉空白拆分（由末尾反斜杠产生）。
4. **构建 `AliasRecord`**：
   - **计数为 0**：没有标记——跳过。
   - **计数为 1**：单个标记视为别名名称，参数为空（`AliasRecord("", name)`）。
   - **计数 >= 2**：第一个非空白标记是别名名称；其余标记用 `\` 拼接为参数字符串（`AliasRecord(argsStr, name)`）。

**示例**：

| 定义字符串 | 结果 |
|-------------------|--------|
| `"esc"` | `AliasRecord("", "esc")` |
| `"slot\3"` | `AliasRecord("3", "slot")` |
| `"swapSlot\1\c2"` | `AliasRecord("1\c2", "swapSlot")` |
| `"say\"hello world\""` | `AliasRecord("hello world", "say")` |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [Alias.getDefinitions](Alias.java/getDefinitions.md) | 第 1 步——按空格拆分链 |
| [Alias.getDefinitionSplits](Alias.java/getDefinitionSplits.md) | 第 2 步——按 `\` 拆分定义 |
| [AliasRecord](AliasRecord.java/AliasRecord.md) | 此方法填充的 record 类型 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
