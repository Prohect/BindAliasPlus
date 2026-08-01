# PlayerListAlias (src/client/java/com/github/prohect/alias/builtinAlias/PlayerListAlias.java)

模拟按住/松开玩家列表键（Tab）的开关别名（`+playerList` / `-playerList`）。按住时显示在线玩家浮层。继承 `BuiltinAliasWithBooleanArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.PlayerListAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.PlayerListAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `builtinPlayerList`（内部，通过 `+playerList` / `-playerList` 暴露）。

**行为：**
- `+playerList`（flag=1）：按下原版 `playerListKey` 按键绑定（Tab），使在线玩家浮层可见，并递增 `timesPressed` 以实现首次按下刻行为。
- `-playerList`（flag=0）：松开 `playerListKey` 按键绑定。

**使用场景：** 主要供 agent/MCP 截图玩家列表浮层，以识别服务器上谁在线。

**界面抑制：** 当 `Alias.isUnderTextInputScreen()` 返回 true 时，按下事件（`+playerList`）被取消。松开事件从不被抑制。

**重新应用行为：** 从 `BuiltinAliasWithBooleanArgs` 继承 `reapplyToGameKeyMapping()`——界面切换后，如果 `flag` 为 true，按键会重新应用到游戏的按键映射。在 `ReapplyAlias.SUPPORTED_ACTIONS` 中列为 `"playerList"`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | 界面切换后重新应用被按住的按键 |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 开关别名的基类 |
| [SilentAlias](../SilentAlias.java/SilentAlias.md) | 静默模式的开关别名（另一个非移动开关） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
