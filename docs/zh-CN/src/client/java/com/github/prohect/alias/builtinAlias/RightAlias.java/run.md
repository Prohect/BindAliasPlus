# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/RightAlias.java）

解析 +/- 布尔参数并按下或松开右移侧移键（D）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.RightAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | `"1"` 向右移动（按下 D），`"0"` 停止（松开 D） |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——设置 `this.flag`（"1" 为 true，"0" 为 false）。
2. 若文本输入界面打开且 flag 为 true（按下），立即返回——输入时不移动。
3. 获取原版 `keyRight` 按键绑定。
4. 调用 `key.setDown(flag)` 按下或松开按键。
5. 若为按下（flag=true），递增 `clickCount`，使游戏注册初始按下事件。

**移动注入：** 因为按键绑定是直接通过 `setDown(flag)` 设置的（不经过原版按键按下队列），`KeyboardInputMixin` 每刻拦截它并应用横向移动冲量。即使没有窗口焦点也能生效。

**界面抑制：** 文本输入界面上抑制按下。松开从不被抑制。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [RightAlias](RightAlias.md) | 类概览 |
| [LeftAlias](../LeftAlias.java/run.md) | 相反的水平移动 |
| [ReapplyAlias](../ReapplyAlias.java/run.md) | 界面切换后重新应用 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
