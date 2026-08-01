# BuiltinAliasWithIntegerArgs（src/client/java/com/github/prohect/alias/BuiltinAliasWithIntegerArgs.java）

## 语法

```java
public abstract class BuiltinAliasWithIntegerArgs<T extends BuiltinAliasWithIntegerArgs<T>> extends BuiltinAliasWithArgs<T>
```

单个参数为 `int` 的内置别名的抽象基类。将参数字符串解析为 `int` 类型的 `flag` 字段。`slot`、`wait`、`yaw` 和 `pitch` 别名使用此类。

## 备注

`parseArgs(args)` 分两步解析参数：

1. **变量解析**：调用 `VarAlias.resolveInt(args)`——如果存在同名的用户定义变量，则使用其值。例如可以先 `var\s\hotbarSlot` 再 `slot\s`，切换到由变量决定的槽位。
2. **字面量解析**：如果不是变量，则尝试 `Integer.parseInt(args)`。失败时通过 `BindAliasClient.LOGGER` 记录错误。

解析后的值存储在 `this.flag` 中。具体子类在其 `run()` 方法中读取 `flag` 以执行别名操作（选择槽位、延迟等待、旋转相机）。

**关于源码中 Javadoc 注释的说明**（`@param args 0->key up, or false, 1->key down, or true`）：此注释是从 `BuiltinAliasWithBooleanArgs` 复制而来，并未准确描述整数参数的解析。实际解析不会特殊处理 `"0"` / `"1"`；它通过 `Integer.parseInt` 解析任何整数。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithDoubleArgs](BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | 双精度参数对应类（setYaw、setPitch） |
| [VarAlias.resolveInt](builtinAlias/VarAlias.java/resolveInt.md) | 解析中的变量解析步骤 |
| [builtinAlias](builtinAlias/README.md) | slot、wait、yaw、pitch 实现 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
