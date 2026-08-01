# resolveValue 方法（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

为给定输入返回 `Number` 的静态解析器——输入可以是字面数字字符串或变量名。

## 语法

```java
public static java.lang.Number resolveValue(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| input | String | 变量名（例如 `"mySlot"`）或数字字符串（例如 `"5"`、`"3.14"`） |

## 备注

**算法（按顺序解析）：**

1. 如果 input 为 null 或空，返回 null。
2. 去除 input 的空白。
3. 尝试 `Integer.parseInt(trimmed)`——如果成功，返回该 Integer。
4. 尝试 `Double.parseDouble(trimmed)`——如果成功，返回该 Double。
5. 在 `GENERAL_VARIABLES.get(trimmed)` 中查找——如果找到，返回存储的 Number。
6. 返回 null（既不是数字也不是变量）。

**设计理由：** 字面数字**先于**变量查找被检查。这确保用户输入 `5` 时得到值 5，而不是名为 "5" 的变量的值（虽然变量名不能以数字开头，这实际上不可能，但顺序对碰巧也能解析为数字的字符串名称仍然重要）。

**调用者：** 由 `resolveInt()` 和 `resolveDouble()` 便捷方法使用。也被所有使用 `BuiltinAliasWithIntegerArgs.parseArgs()` 或 `BuiltinAliasWithDoubleArgs.parseArgs()` 的别名间接调用。

**返回值：** `Integer`、`Double` 或 null。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [resolveInt](resolveInt.md) | 便捷方法——返回 int 或 null |
| [resolveDouble](resolveDouble.md) | 便捷方法——返回 double 或 null |
| [isVariable](isVariable.md) | 检查名称是否存在于存储中 |
| [GENERAL_VARIABLES](GENERAL_VARIABLES.md) | 变量存储映射 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
