# parseArgs 方法（src/client/java/com/github/prohect/alias/BuiltinAliasWithIntegerArgs.java）

## 语法

```java
public void parseArgs(String args)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 数字字符串（例如 `"3"`）或可由 `VarAlias.resolveInt()` 解析的变量名 |

## 备注

通过两步解析设置 `flag` 字段：

1. **变量查找**：调用 `VarAlias.resolveInt(args)`。如果存在该名称的变量且持有 `Integer` 值，则使用它。这支持 `var\s\hotbarSlot` → `slot\s` 这样的模式。
2. **字面量解析**：回退到 `Integer.parseInt(args)`。发生 `NumberFormatException` 时，通过 `BindAliasClient.LOGGER` 记录错误并将 `flag` 保持为 `0`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [flag](flag.md) | 此方法设置的字段 |
| [VarAlias.resolveInt](builtinAlias/VarAlias.java/resolveInt.md) | 变量解析步骤 |
| [BuiltinAliasWithDoubleArgs.parseArgs](BuiltinAliasWithDoubleArgs.java/parseArgs.md) | 双精度对应方法 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
