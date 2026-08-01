# getDefinitions 方法（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static @NotNull ArrayList<String> getDefinitions(String args)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 别名链字符串——以空格分隔的各次别名调用 |

## 返回值

按 `divider4AliasDefinition`（空格 `' '`）拆分后得到的各条别名定义字符串列表。输入为空时返回空列表。

## 备注

这是执行别名链时的第一个解析步骤。算法逐字符遍历字符串：

1. **双引号处理**：遇到 `"` 时切换 `coveredByDoubleQuotes` 标志。在引号内，空格被视为字面字符——而非分隔符。
2. **分隔符跳过**：连续的分隔符会被合并——`lastStepSubmit` 标志可防止在重复的分隔符字符处提交空字符串。
3. **末尾片段**：循环结束后，任何剩余的非空 `currentDefinition` 会作为最后一项添加。

**示例**：

| 输入 | 输出 |
|-------|--------|
| `"+attack slot\1"` | `["+attack", "slot\1"]` |
| `"say\"hello world\" wait\5"` | `["say\"hello world\"", "wait\5"]` |
| `"esc  toggleInventory"` | `["esc", "toggleInventory"]`（双空格被合并） |

由 `UserAlias.decodeArgs2Alias()`、`getOppositeDefinition()`、`BindAliasClient.loadCFG()` 以及 `bind`/`alias` 命令调用。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getDefinitionSplits](getDefinitionSplits.md) | 第二个解析步骤——按 `\` 将定义拆分为名称 + 参数 |
| [divider4AliasDefinition](divider4AliasDefinition.md) | 使用的分隔符字符（空格 `' '`） |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
