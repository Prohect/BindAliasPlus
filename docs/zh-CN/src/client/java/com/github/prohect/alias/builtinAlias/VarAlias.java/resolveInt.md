# resolveInt 方法（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

返回解析输入的整数值的便捷静态解析器。

## 语法

```java
public static java.lang.Integer resolveInt(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| input | String | 要解析的变量名或数字字符串 |

## 备注

**算法：** 调用 `resolveValue(input)`。如果结果非 null，返回 `n.intValue()`。否则返回 null。

**返回值：** `Integer` 或 null。

**精度损失：** 如果解析出的值是 `Double`（例如来自 `var\myAngle\pitch`），`intValue()` 会截断小数部分。请注意，浮点变量值与整数参数别名一起使用时会有精度损失。

**调用者：** `SlotAlias.run()`、`SetPerspectiveAlias.run()`（通过 `parseArgs()`）、`WaitAlias.run()`、`SwapSlotAlias.parseSlotRef()`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [resolveValue](resolveValue.md) | 底层解析器 |
| [resolveDouble](resolveDouble.md) | double 变体 |
| [SlotAlias](../SlotAlias.java/run.md) | 主要消费者（槽位编号） |
| [WaitAlias](../WaitAlias.java/run.md) | 消费者（刻数） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
