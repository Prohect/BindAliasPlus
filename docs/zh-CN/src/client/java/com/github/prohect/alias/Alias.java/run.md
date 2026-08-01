# run 方法（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
T run(String args)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 本次别名调用的参数字符串。对于 `AliasWithoutArgs`，始终为 `""`。对于 `AliasWithArgs`，包含以反斜杠分隔的参数（例如 `slot\3` 的 `"3"`）。 |

## 返回值

返回 `this`（该别名实例），以支持流畅的链式调用。

## 备注

这是每个别名都必须履行的**核心契约**。当从别名链中调用别名时，`UserAlias.run()` 会使用从 `AliasRecord` 中提取的参数调用 `alias.run(args)`。

**各类型的实现模式**：

| 类型 | 典型的 `run()` 行为 |
|------|--------------------------|
| `BuiltinAliasWithBooleanArgs` 子类 | 调用 `parseArgs("0"|"1")`，然后向原版按键映射注入或松开。可能先检查 `isUnderTextInputScreen()`。 |
| `BuiltinAliasWithIntegerArgs` 子类 | 调用 `parseArgs(args)`，设置 `this.flag`，然后执行操作（选择槽位、延迟等待、旋转相机）。 |
| `BuiltinAliasWithDoubleArgs` 子类 | 与整数相同，但使用 `double`。 |
| `BuiltinAliasWithStringArgs` 子类 | 内联解析参数（没有基础的 `parseArgs`；每个子类有自己的逻辑）。 |
| `BuiltinAliasWithoutArgs` 子类 | 忽略 `args`——始终为 `""`——直接执行操作。 |
| `UserAlias` | 忽略 `args` 参数——使用自身存储的 `this.args` 定义字符串——并分派别名链。 |
| `VarAlias` | 解析 `name\source`，将解析后的值存储到 `GENERAL_VARIABLES` 或 `CONTAINER_SLOT_VARIABLES` 中。 |

**错误处理**：无效参数应通过 `BindAliasClient.LOGGER` 以 `warn` 或 `error` 级别记录，然后方法正常返回。别名不得因输入错误而抛出异常——否则会中断别名链。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UserAlias.run](UserAlias.java/run.md) | 主要调用方——解析链并分派到各个 `run()` |
| [parseArgs](BuiltinAliasWithBooleanArgs.java/parseArgs.md) | 布尔参数别名的参数解析 |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 所有接受参数的别名都实现此契约 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
