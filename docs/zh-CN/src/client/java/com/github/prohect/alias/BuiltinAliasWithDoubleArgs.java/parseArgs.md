# parseArgs 方法（src/client/java/com/github/prohect/alias/BuiltinAliasWithDoubleArgs.java）

## 语法

```java
public void parseArgs(String args)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 数字字符串（例如 `"90.5"`）或可由 `VarAlias.resolveDouble()` 解析的变量名 |

## 备注

通过两步解析设置 `flag` 字段：

1. **变量查找**：调用 `VarAlias.resolveDouble(args)`。如果存在该名称的变量且持有 `Double` 值，则使用它。
2. **字面量解析**：回退到 `Double.parseDouble(args)`。发生 `NumberFormatException` 时，通过 `BindAliasClient.LOGGER` 记录错误并将 `flag` 保持为 `0.0`。

这既支持字面量值（`setYaw\90.5`），也支持变量驱动的值（`var\myYaw\90.5` 然后 `setYaw\myYaw`）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [flag](flag.md) | 此方法设置的字段 |
| [VarAlias.resolveDouble](builtinAlias/VarAlias.java/resolveDouble.md) | 变量解析步骤 |
| [BuiltinAliasWithIntegerArgs.parseArgs](BuiltinAliasWithIntegerArgs.java/parseArgs.md) | 整数对应方法 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
