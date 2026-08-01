# GENERAL_VARIABLES 字段（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

存储所有变量名 → 数值对的全局映射。

## 语法

```java
public static final java.util.Map<java.lang.String, java.lang.Number> GENERAL_VARIABLES
```

## 备注

**用途：** `var` 别名创建的所有变量的中央存储。键是变量名（字符串），值是 `Number` 实例（`Integer` 或 `Double`）。

**写入者：** `VarAlias.run()`（两个重载）——存储来自源的值。`UnloadCFGVarsAlias`——移除 CFG 加载的条目。`UnloadUserVarsAlias`——移除运行时条目。

**读取者：**
- `VarAlias.resolveValue()`、`resolveInt()`、`resolveDouble()`、`isVariable()`——公共静态解析器。
- `SlotAlias.run()`——解析槽位编号。
- `PitchAlias.run()`、`YawAlias.run()`——通过 `BuiltinAliasWithDoubleArgs.parseArgs()` 解析旋转角度。
- `SetPitchAlias.run()`、`SetYawAlias.run()`——解析绝对角度。
- `SetPerspectiveAlias.run()`——解析视角索引。
- `WaitAlias.run()`——解析刻数。
- `SwapSlotAlias.parseSlotRef()`——为非容器引用解析槽位编号。

**线程安全：** 仅从游戏线程访问（单线程）。无需同步。

**默认值：** 类加载时初始化的空 `HashMap`。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
