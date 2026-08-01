# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/YawAlias.java）

解析角度参数并按指定量旋转玩家的偏航角。

## 语法

```java
public com.github.prohect.alias.builtinAlias.YawAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 以度为单位的相对偏航角（double）。正值 = 向左转，负值 = 向右转。支持变量。 |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——使用 `VarAlias.resolveDouble()` 解析为 `flag`（double）。
2. 如果 `mc.player` 为 null，记录一条警告并返回。
3. 设置玩家偏航角：`player.setYRot((float) (player.getYRot() + flag))`。

**返回值：** `this`（流畅式返回）。

**副作用：** 修改本地玩家的 Y 旋转（偏航角）。旋转是相对的，不是绝对的。偏航角值自动环绕。

**无界面抑制：** 在任何界面上都能工作。

**示例：**
- `yaw\90` — 向左转 90 度
- `yaw\-90` — 向右转 90 度
- `yaw\180` — 转身

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [YawAlias](YawAlias.md) | 类概览 |
| [SetYawAlias](../SetYawAlias.java/run.md) | 绝对偏航角设置器 |
| [PitchAlias](../PitchAlias.java/run.md) | 相对俯仰角旋转 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
