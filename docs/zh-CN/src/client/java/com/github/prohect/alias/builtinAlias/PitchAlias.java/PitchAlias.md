# PitchAlias（src/client/java/com/github/prohect/alias/builtinAlias/PitchAlias.java）

按相对角度旋转玩家相机俯仰角的内置别名。继承 `BuiltinAliasWithDoubleArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.PitchAlias extends com.github.prohect.alias.BuiltinAliasWithDoubleArgs<com.github.prohect.alias.builtinAlias.PitchAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `pitch` — 用法：`pitch\deg`，其中 `deg` 是以度为单位的相对俯仰角（双精度）。

**行为：** 通过 `parseArgs(args)` 将参数解析到 `flag`（double），然后将玩家的俯仰角设置为 `player.getXRot() + flag`。正值向下看，负值向上看。

**变量解析：** 参数通过 `VarAlias.resolveDouble()` 解析，因此可以是字面数字或存储数字的变量名。

**要求：** `mc.player` 必须非 null。如果玩家为 null，则记录一条警告。

**钳制：** 与 `setPitch` 不同，此别名不钳制结果——原版俯仰角范围（-90 到 90）由游戏引擎在旋转应用后强制实施。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SetPitchAlias](../SetPitchAlias.java/SetPitchAlias.md) | 绝对俯仰角设置器 |
| [YawAlias](../YawAlias.java/YawAlias.md) | 相对偏航角旋转（水平） |
| [VarAlias](../VarAlias.java/VarAlias.md) | 用于参数解析的变量系统 |
| [BuiltinAliasWithDoubleArgs](../../BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | 双精度参数别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
