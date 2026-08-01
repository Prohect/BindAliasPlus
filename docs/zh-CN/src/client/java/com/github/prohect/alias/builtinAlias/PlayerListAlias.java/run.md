# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/PlayerListAlias.java）

解析 +/- 布尔参数并按下或松开玩家列表（Tab）键。

## 语法

```java
public com.github.prohect.alias.builtinAlias.PlayerListAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | `"1"` 显示玩家列表（按下 Tab），`"0"` 隐藏（松开 Tab） |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——设置 `this.flag`（"1" 为 true，"0" 为 false）。
2. 如果文本输入界面已打开且 flag 为 true，则立即返回——输入时不要显示浮层。
3. 获取原版 `playerListKey` 按键绑定。
4. 调用 `key.setPressed(flag)` 按下或松开按键。
5. 如果正在按下（flag=true），则递增 `key.timesPressed`，使游戏注册初始按下事件。

**副作用：** 显示或隐藏在线玩家浮层。按键绑定状态（`isDown`、`timesPressed`）被修改。

**界面抑制：** 当 `isUnderTextInputScreen()` 为 true 时，按下被抑制。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [PlayerListAlias](PlayerListAlias.md) | 类概览 |
| [ReapplyAlias](../ReapplyAlias.java/run.md) | 界面切换后重新应用被按住的按键 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
