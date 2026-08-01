# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SetYawAlias.java）

解析角度参数并将玩家的偏航角设置为绝对角度。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SetYawAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 以度为单位的绝对偏航角（double）。支持变量。 |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——使用 `VarAlias.resolveDouble()` 解析为 `flag`（double）。
2. 如果 `mc.player` 为 null，记录一条警告并返回。
3. 设置玩家偏航角：`player.setYRot((float) flag)`。

**返回值：** `this`（流畅式返回）。

**副作用：** 将本地玩家的 Y 旋转（偏航角）修改为绝对值。数值由游戏引擎自动环绕。

**无界面抑制：** 在任何界面上都能工作。

**与 YawAlias 的区别：** 此别名设置绝对偏航角；`yaw\deg` 在当前偏航角上累加。

**示例：**
- `setYaw\0` — 面向南方
- `setYaw\90` — 面向西方
- `setYaw\180` — 面向北方

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SetYawAlias](SetYawAlias.md) | 类概览 |
| [YawAlias](../YawAlias.java/run.md) | 相对偏航角旋转 |
| [SetPitchAlias](../SetPitchAlias.java/run.md) | 绝对俯仰角设置器 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
