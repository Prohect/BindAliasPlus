# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SayAlias.java）

将字符串参数作为聊天消息发送到服务器。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SayAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 要发送到服务器的聊天消息文本（无需前导 `/`） |

## 备注

**算法：**

1. 获取 `mc.player`。
2. 如果为 null，立即返回。
3. 调用 `player.connection.sendChat(args)` 将消息发送到服务器。

**返回值：** `this`（流畅式返回）。

**副作用：** 向服务器发送聊天数据包。消息会显示在服务器上所有玩家的聊天界面中。

**无界面抑制：** 在任何界面上都能工作，包括文本输入界面——消息绕过聊天 GUI。

**示例：**
- `say\Hello` — 将 "Hello" 发送到聊天界面
- `say\"Hello world"` — 将 "Hello world" 发送到聊天界面（多词时加引号）

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SayAlias](SayAlias.md) | 类概览 |
| [LocalSayAlias](../LocalSayAlias.java/run.md) | 仅客户端侧的消息 |
| [SendCommandAlias](../SendCommandAlias.java/run.md) | 发送服务器命令而非聊天消息 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
