# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SneakAlias.java）

解析 +/- 布尔参数并按下或松开潜行键（Shift）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SneakAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | `"1"` 潜行（按下 Shift），`"0"` 停止（松开 Shift） |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——设置 `this.flag`（"1" 为 true，"0" 为 false）。
2. 如果文本输入界面已打开且 flag 为 true，则立即返回。
3. 获取原版 `sneakKey` 按键绑定。
4. 调用 `key.setPressed(flag)` 按下或松开按键。
5. 如果正在按下（flag=true），则递增 `timesPressed`，使游戏注册初始按下事件。

**返回值：** `this`（流畅返回）。

**副作用：** 按键按住时玩家蹲下（潜行）。潜行防止从方块边缘掉落并减小玩家的碰撞箱高度。`KeyboardInputMixin` 每刻拾取按键绑定状态，实现与窗口焦点无关的操作。

**界面抑制：** 按下在文本输入界面上被抑制。松开从不被抑制。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SneakAlias](SneakAlias.md) | 类概览 |
| [SprintAlias](../SprintAlias.java/run.md) | 疾跑键 |
| [ReapplyAlias](../ReapplyAlias.java/run.md) | 界面切换后重新应用 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
