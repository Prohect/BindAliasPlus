# BuiltinAliasWithDoubleArgs（src/client/java/com/github/prohect/alias/BuiltinAliasWithDoubleArgs.java）

## 语法

```java
public abstract class BuiltinAliasWithDoubleArgs<T extends BuiltinAliasWithDoubleArgs<T>> extends BuiltinAliasWithArgs<T>
```

单个参数为 `double` 浮点值的内置别名的抽象基类。将参数字符串解析为 `double` 类型的 `flag` 字段。`setYaw` 和 `setPitch` 别名使用此类。

## 备注

`parseArgs(args)` 分两步解析参数：

1. **变量解析**：调用 `VarAlias.resolveDouble(args)`——如果存在同名的用户定义变量，则使用其值。
2. **字面量解析**：如果不是变量，则尝试 `Double.parseDouble(args)`。失败时通过 `BindAliasClient.LOGGER` 记录错误。

解析后的值存储在 `this.flag` 中。具体子类在其 `run()` 方法中读取 `flag` 以应用旋转。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithIntegerArgs](BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | 整数参数对应类（yaw、pitch） |
| [VarAlias.resolveDouble](builtinAlias/VarAlias.java/resolveDouble.md) | 解析中的变量解析步骤 |
| [builtinAlias](builtinAlias/README.md) | setYaw、setPitch 实现 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
