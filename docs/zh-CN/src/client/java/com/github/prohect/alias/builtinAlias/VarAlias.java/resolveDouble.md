# resolveDouble 方法（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

返回解析输入的 double 值的便捷静态解析器。

## 语法

```java
public static java.lang.Double resolveDouble(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| input | String | 要解析的变量名或数字字符串 |

## 备注

**算法：** 调用 `resolveValue(input)`。如果结果非 null，返回 `n.doubleValue()`。否则返回 null。

**返回值：** `Double` 或 null。

**整数安全：** 整数值通过 `intValue()` → `doubleValue()` 安全加宽为 double。在 double 的精确整数范围内（±2^53）的整数值不会损失精度。

**调用者：** `PitchAlias.run()`、`YawAlias.run()`、`SetPitchAlias.run()`、`SetYawAlias.run()`——全部通过 `BuiltinAliasWithDoubleArgs.parseArgs()`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [resolveValue](resolveValue.md) | 底层解析器 |
| [resolveInt](resolveInt.md) | 整数变体 |
| [PitchAlias](../PitchAlias.java/run.md) | 消费者（俯仰角旋转） |
| [YawAlias](../YawAlias.java/run.md) | 消费者（偏航角旋转） |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
