# SendCommandAlias（src/client/java/com/github/prohect/alias/builtinAlias/SendCommandAlias.java）

发送服务器命令（不带前导 `/`）的内置别名。继承 `BuiltinAliasWithStringArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.SendCommandAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.SendCommandAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `sendCommand` — 用法：`sendCommand\command`（无需前导 `/`）。

**行为：** 通过 `player.connection.sendCommand(args)` 将参数字符串作为命令发送到服务器。这等同于在聊天框中输入 `/command`。

**引号：** 多参数命令必须加引号：`sendCommand\"tp @p ~ ~10 ~"`。引号由别名链解析器处理，不由本别名处理。

**要求：** `mc.player` 必须非 null。如果为 null，则静默返回。

**无界面抑制：** 此别名在任何界面上都能工作。它直接向服务器连接发送命令，完全绕过聊天 GUI。

**与 `say` 的区别：** `sendCommand` 发送服务器命令（由服务器解释）。`say` 发送对其他玩家可见的纯聊天文本。

**重要：** 命令字符串中不要包含前导 `/`。`sendCommand()` 方法已经处理命令路由。

**示例：**
- `sendCommand\tp @p 0 70 0` — 传送玩家
- `sendCommand\give @s diamond 64` — 给予物品
- `sendCommand\time set day` — 将时间设置为白天

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SayAlias](../SayAlias.java/SayAlias.md) | 发送聊天文本而非命令 |
| [LocalSayAlias](../LocalSayAlias.java/LocalSayAlias.md) | 仅客户端侧的消息 |
| [BuiltinAliasWithStringArgs](../../BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | 字符串参数别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
