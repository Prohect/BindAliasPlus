# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SprintAlias.java）

解析 +/- 布尔参数并按下或松开疾跑键（Ctrl）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SprintAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | `"1"` 疾跑（按下 Ctrl），`"0"` 停止（松开 Ctrl） |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——设置 `this.flag`（"1" 为 true，"0" 为 false）。
2. 如果文本输入界面已打开且 flag 为 true，则立即返回。
3. 获取原版 `sprintKey` 按键绑定。
4. 调用 `key.setPressed(flag)` 按下或松开按键。
5. 如果正在按下（flag=true），则递增 `timesPressed`。

**返回值：** `this`（流畅返回）。

**副作用：** 与前进移动组合时启用疾跑。玩家移动更快，但以更高的速率消耗食物。疾跑受原版约束限制（饥饿值 > 6、未被阻止、持续前进移动）。

**界面抑制：** 按下在文本输入界面上被抑制。松开从不被抑制。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SprintAlias](SprintAlias.md) | 类概览 |
| [SneakAlias](../SneakAlias.java/run.md) | 潜行键 |
| [ForwardAlias](../ForwardAlias.java/run.md) | 前进移动（疾跑所需） |
| [ReapplyAlias](../ReapplyAlias.java/run.md) | 界面切换后重新应用 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
