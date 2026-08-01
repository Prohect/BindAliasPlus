# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/RightAlias.java）

解析 +/- 布尔参数并按下或松开右侧移键（D）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.RightAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | `"1"` 向右侧移（按下 D），`"0"` 停止（松开 D） |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——设置 `this.flag`（"1" 为 true，"0" 为 false）。
2. 如果文本输入界面已打开且 flag 为 true（正在按下），则立即返回——输入时不要移动。
3. 获取原版 `rightKey` 按键绑定。
4. 调用 `key.setPressed(flag)` 按下或松开按键。
5. 如果正在按下（flag=true），则递增 `timesPressed`，使游戏注册初始按下事件。

**移动注入：** 因为按键绑定是通过 `setPressed(flag)` 直接设置的（不经过原版按键按下队列），`KeyboardInputMixin` 每刻拦截它并应用水平移动冲量。即使没有窗口焦点也能工作。

**界面抑制：** 按下在文本输入界面上被抑制。松开从不被抑制。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [RightAlias](RightAlias.md) | 类概览 |
| [LeftAlias](../LeftAlias.java/run.md) | 相反的水平移动 |
| [ReapplyAlias](../ReapplyAlias.java/run.md) | 界面切换后重新应用 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
