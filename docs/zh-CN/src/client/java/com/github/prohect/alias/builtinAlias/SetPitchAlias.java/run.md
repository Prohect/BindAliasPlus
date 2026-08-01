# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SetPitchAlias.java）

解析角度参数并将玩家的俯仰角设置为绝对角度。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SetPitchAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 以度为单位的绝对俯仰角（double）。支持变量。 |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——使用 `VarAlias.resolveDouble()` 解析为 `flag`（double）。
2. 如果 `mc.player` 为 null，记录一条警告并返回。
3. 设置玩家俯仰角：`player.setXRot((float) flag)`。

**返回值：** `this`（流畅式返回）。

**副作用：** 将本地玩家的 X 旋转（俯仰角）修改为绝对值。原版游戏引擎将结果钳制在 [-90, 90]。

**无界面抑制：** 在任何界面上都能工作。

**与 PitchAlias 的区别：** 此别名设置绝对俯仰角；`pitch\deg` 在当前俯仰角上累加。

**示例：** `setPitch\-45` 将俯仰角设为向上看 45 度。`setPitch\0` 将俯仰角重置为水平。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SetPitchAlias](SetPitchAlias.md) | 类概览 |
| [PitchAlias](../PitchAlias.java/run.md) | 相对俯仰角旋转 |
| [SetYawAlias](../SetYawAlias.java/run.md) | 绝对偏航角设置器 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
