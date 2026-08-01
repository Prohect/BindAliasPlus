# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SendCommandAlias.java）

将字符串参数作为服务器命令发送（无需前导 `/`）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SendCommandAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 要发送到服务器的命令（例如 `"tp @p 0 70 0"`，不要前导 `/`） |

## 备注

**算法：**

1. 获取 `mc.player`。
2. 如果为 null，立即返回。
3. 调用 `player.connection.sendCommand(args)` 将命令发送到服务器。

**返回值：** `this`（流畅式返回）。

**副作用：** 向服务器发送命令数据包。服务器处理并响应该命令。

**无界面抑制：** 在任何界面上都能工作。

**示例：**
- `sendCommand\tp @p 0 70 0`
- `sendCommand\"give @s diamond 64"`
- `sendCommand\time set day`

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SendCommandAlias](SendCommandAlias.md) | 类概览 |
| [SayAlias](../SayAlias.java/run.md) | 发送聊天消息而非命令 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
