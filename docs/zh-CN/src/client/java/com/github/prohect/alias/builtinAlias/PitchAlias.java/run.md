# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/PitchAlias.java）

解析角度参数并按指定量旋转玩家的俯仰角。

## 语法

```java
public com.github.prohect.alias.builtinAlias.PitchAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 以度为单位的相对俯仰角（double）。正值 = 向下看，负值 = 向上看。支持变量。 |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——使用 `VarAlias.resolveDouble()` 解析为 `flag`（double）。
2. 如果 `mc.player` 为 null，记录一条警告并返回。
3. 设置玩家俯仰角：`player.setXRot((float) flag + player.getXRot())`。

**返回值：** `this`（流畅式返回）。

**副作用：** 修改本地玩家的 X 旋转（俯仰角）。旋转是相对的，不是绝对的。

**错误处理：** 如果玩家不可用，记录 `"[pitch]Player is null"`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [PitchAlias](PitchAlias.md) | 类概览 |
| [SetPitchAlias](../SetPitchAlias.java/run.md) | 绝对俯仰角设置器 |
| [YawAlias](../YawAlias.java/run.md) | 相对偏航角旋转 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
