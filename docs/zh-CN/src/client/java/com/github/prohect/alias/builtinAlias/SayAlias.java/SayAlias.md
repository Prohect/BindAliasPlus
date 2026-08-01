# SayAlias（src/client/java/com/github/prohect/alias/builtinAlias/SayAlias.java）

向服务器发送聊天消息的内置别名。继承 `BuiltinAliasWithStringArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.SayAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.SayAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `say` — 用法：`say\text` 或 `say\"multi word text"`。

**行为：** 通过 `player.connection.sendChat(args)` 将参数字符串作为聊天消息发送到服务器。这等同于将消息输入聊天框并按回车。

**引号：** 多词消息必须用双引号：`say\"Hello world"`。引号是别名链语法（`getDefinitionSplits`/`getDefinitions`）的一部分，不属于此别名的逻辑——别名收到的是不带引号的文本。

**要求：** `mc.player` 必须非 null。如果为 null，则静默返回。

**无界面抑制：** 此别名在任何界面上都能工作（它完全绕过聊天界面）。它直接向服务器连接发送消息，而不是通过聊天 GUI。

**与 `localSay` 的区别：** `say` 将消息发送到服务器（对所有玩家可见）。`localSay` 创建仅客户端侧的聊天消息（仅本地玩家可见）。

**与 `sendCommand` 的区别：** `say` 发送纯聊天文本。`sendCommand` 发送服务器命令（例如 `/tp`、`/give`）——`sendCommand` 隐含前导 `/`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LocalSayAlias](../LocalSayAlias.java/LocalSayAlias.md) | 仅客户端侧的聊天消息 |
| [SendCommandAlias](../SendCommandAlias.java/SendCommandAlias.md) | 发送服务器命令 |
| [BuiltinAliasWithStringArgs](../../BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | 字符串参数别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
