# getDefinitionSplits 方法（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static @NotNull ArrayList<String> getDefinitionSplits(String definition)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `definition` | `String` | 单条别名定义——例如 `"slot\3"` 或 `"swapSlot\1\c2"` |

## 返回值

按 `divider4AliasArgs`（反斜杠 `\`）拆分得到的标记列表。第一个标记是别名名称；后续标记是参数。空的 / 空白的标记（由末尾反斜杠产生）通过 `removeIf(String::isBlank)` 移除。

## 备注

这是 `getDefinitions()` 之后的第二个解析步骤。接收单条定义并将其拆分为名称和参数。

算法与 `getDefinitions()` 类似：逐字符遍历字符串，在尊重双引号包裹的块的同时按反斜杠拆分。与 `getDefinitions()` 不同，它在拆分后还会额外**过滤掉空白标记**——这可以防止末尾反斜杠产生空字符串。

**示例**：

| 输入 | 输出 |
|-------|--------|
| `"slot\3"` | `["slot", "3"]` |
| `"swapSlot\1\c2"` | `["swapSlot", "1", "c2"]` |
| `"esc"` | `["esc"]` |
| `"say\"hello world\""` | `["say", "hello world"]`（引号被剥离） |

由 `UserAlias.decodeArgs2Alias()` 调用，以从每个定义标记中提取别名名称和参数。`BindAliasClient.loadCFG()` 解析 `alias` 和 `var` 行时也会用到。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getDefinitions](getDefinitions.md) | 第一个解析步骤——按空格拆分链 |
| [divider4AliasArgs](divider4AliasArgs.md) | 使用的分隔符字符（反斜杠 `\`） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
