# SetYawAlias（src/client/java/com/github/prohect/alias/builtinAlias/SetYawAlias.java）

将玩家相机偏航角设置为绝对角度的内置别名。继承 `BuiltinAliasWithDoubleArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.SetYawAlias extends com.github.prohect.alias.BuiltinAliasWithDoubleArgs<com.github.prohect.alias.builtinAlias.SetYawAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `setYaw` — 用法：`setYaw\deg`，其中 `deg` 是以度为单位的绝对偏航角。

**行为：** 将玩家的偏航角设置为参数指定的精确值。与应用相对旋转（`current + delta`）的 `YawAlias` 不同，此别名是绝对的。

**偏航角方向：** 在 Minecraft 中：
- 0 = 南
- 90 = 西
- 180 = 北
- 270 = 东
数值会环绕（例如 360 = 0，-90 = 270）。

**参数解析：** 参数通过 `VarAlias.resolveDouble()` 解析，支持字面数字和变量名。

**要求：** `mc.player` 必须非 null。如果玩家为 null，则记录一条警告。

**与 YawAlias 的区别：** `setYaw` 是绝对的（设置为特定角度），`yaw` 是相对的（在当前角度上加减）。

**无界面抑制：** 这是相机设置，不是游戏输入——在任何界面上都能工作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [YawAlias](../YawAlias.java/YawAlias.md) | 相对偏航角旋转 |
| [SetPitchAlias](../SetPitchAlias.java/SetPitchAlias.md) | 绝对俯仰角设置器 |
| [VarAlias](../VarAlias.java/VarAlias.md) | 用于参数解析的变量系统 |
| [BuiltinAliasWithDoubleArgs](../../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | 双精度参数别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
