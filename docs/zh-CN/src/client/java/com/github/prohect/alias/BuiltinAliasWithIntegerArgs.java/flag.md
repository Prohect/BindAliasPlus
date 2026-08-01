# flag 字段（src/client/java/com/github/prohect/alias/BuiltinAliasWithIntegerArgs.java）

## 语法

```java
public int flag
```

存储从参数字符串解析出的 `int` 值。具体子类的 `run()` 方法读取它来选择快捷栏槽位（`slot`）、延迟执行（`wait`）或旋转相机（`yaw`、`pitch`）。

## 备注

由 `parseArgs(args)` 通过 `VarAlias.resolveInt()` 或 `Integer.parseInt()` 设置。默认值为 `0`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [parseArgs](parseArgs.md) | 从参数字符串设置此字段 |
| [BuiltinAliasWithDoubleArgs.flag](BuiltinAliasWithDoubleArgs.java/flag.md) | 双精度对应字段 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
