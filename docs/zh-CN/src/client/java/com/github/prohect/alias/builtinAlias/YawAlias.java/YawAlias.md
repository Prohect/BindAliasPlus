# YawAlias（src/client/java/com/github/prohect/alias/builtinAlias/YawAlias.java）

按相对角度旋转玩家相机偏航角的内置别名。继承 `BuiltinAliasWithDoubleArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.YawAlias extends com.github.prohect.alias.BuiltinAliasWithDoubleArgs<com.github.prohect.alias.builtinAlias.YawAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `yaw` — 用法：`yaw\deg`，其中 `deg` 是以度为单位的相对偏航角（双精度）。

**行为：** 通过 `parseArgs(args)` 将参数解析到 `flag`（double），然后将玩家的偏航角设置为 `player.getYRot() + flag`。正值向左转（朝向负偏航角方向），负值向右转。

**变量解析：** 参数通过 `VarAlias.resolveDouble()` 解析，因此可以是字面数字或存储数字的变量名。

**要求：** `mc.player` 必须非 null。如果玩家为 null，则记录一条警告。

**偏航角环绕：** Minecraft 偏航角自然环绕——[0, 360) 或 [-180, 180) 之外的值由游戏引擎自动规范化。这意味着向一个方向连续旋转无需计算模数。

**与 SetYawAlias 的区别：** `yaw\deg` 是相对的（在当前偏航角上累加）；`setYaw\deg` 是绝对的。

**无界面抑制：** 在任何界面上都能工作——它是相机设置，不是游戏输入。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SetYawAlias](../SetYawAlias.java/SetYawAlias.md) | 绝对偏航角设置器 |
| [PitchAlias](../PitchAlias.java/PitchAlias.md) | 相对俯仰角旋转（垂直） |
| [VarAlias](../VarAlias.java/VarAlias.md) | 用于参数解析的变量系统 |
| [BuiltinAliasWithDoubleArgs](../../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | 双精度参数别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
