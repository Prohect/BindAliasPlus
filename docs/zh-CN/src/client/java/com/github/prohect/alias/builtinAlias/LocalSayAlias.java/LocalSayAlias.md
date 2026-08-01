# LocalSayAlias (src/client/java/com/github/prohect/alias/builtinAlias/LocalSayAlias.java)

显示仅客户端侧聊天消息的内置别名，消息显示在本地玩家的 HUD 和聊天频道中。消息不会发送到服务器。继承自 `BuiltinAliasWithStringArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.LocalSayAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.LocalSayAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"localSay"`。用法：`localSay\<text>`。

消息通过 `Minecraft.gui.hud.getChat().addClientSystemMessage(Component.literal(args))` 添加，显示在客户端系统消息频道中。**不会**通过网络发送——服务器和其他玩家永远看不到。

若玩家为 null（不在世界中），别名静默返回，不执行任何操作。

**用例：** 别名开发期间的调试日志、仅本地可见的行内标注，或显示来自变量的计算值。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SayAlias](../SayAlias.java/SayAlias.md) | 向服务器发送聊天消息（所有人可见） |
| [LogAlias](../LogAlias.java/LogAlias.md) | 将文本写入模组日志文件 |
| [SendCommandAlias](../SendCommandAlias.java/SendCommandAlias.md) | 发送服务器命令 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
