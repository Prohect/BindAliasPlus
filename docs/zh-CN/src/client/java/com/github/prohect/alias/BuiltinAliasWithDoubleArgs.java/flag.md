# flag 字段（src/client/java/com/github/prohect/alias/BuiltinAliasWithDoubleArgs.java）

## 语法

```java
public double flag
```

存储从参数字符串解析出的 `double` 值。具体子类的 `run()` 方法读取它来应用旋转（setYaw、setPitch）。

## 备注

由 `parseArgs(args)` 通过 `VarAlias.resolveDouble()` 或 `Double.parseDouble()` 设置。默认值为 `0.0`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [parseArgs](parseArgs.md) | 从参数字符串设置此字段 |
| [BuiltinAliasWithIntegerArgs.flag](BuiltinAliasWithIntegerArgs.java/flag.md) | 整数对应字段 |
| [BuiltinAliasWithBooleanArgs.flag](BuiltinAliasWithBooleanArgs.java/flag.md) | 布尔对应字段 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
