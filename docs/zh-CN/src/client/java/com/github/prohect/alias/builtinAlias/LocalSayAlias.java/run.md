# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/LocalSayAlias.java）

将仅客户端侧的聊天消息添加到本地 HUD 和聊天频道。

## 语法

```java
public com.github.prohect.alias.builtinAlias.LocalSayAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 要在本地显示的文本 |

## 备注

1. 若 `Minecraft.player` 为 null（不在世界中），立即返回——没有活动的游戏会话就无法显示消息。
2. 调用 `Minecraft.gui.hud.getChat().addClientSystemMessage(Component.literal(args))` 将文本显示为客户端系统消息。

消息出现在玩家的聊天浮层和聊天记录中，但绝不会发送到服务器。仅本地玩家可见。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SayAlias.run()](../SayAlias.java/run.md) | 向服务器发送聊天（所有玩家可见） |
| [LogAlias.run()](../LogAlias.java/run.md) | 将文本写入模组日志 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
