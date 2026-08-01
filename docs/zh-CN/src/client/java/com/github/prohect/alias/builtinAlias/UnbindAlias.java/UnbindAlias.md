# UnbindAlias（src/client/java/com/github/prohect/alias/builtinAlias/UnbindAlias.java）

向服务器发送 `unbind` 命令以移除按键绑定的内置别名。继承 `BuiltinAliasWithStringArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.UnbindAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.UnbindAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `unbind` — 用法：`unbind\key` 或 `unbind\"key with spaces"`。

**行为：** 构建以 `"unbind"` 开头、后跟空格（`divider4AliasDefinition` 分隔符）的 unbind 命令行，然后追加参数字符串。组装后的命令通过 `player.connection.sendCommand(line)` 发送到服务器。注意：这调用的是 `sendCommand`（作为服务器命令发送，意味着会被转发到服务器处理），而不是 `sendChat`。

**为什么用 `sendCommand` 而不是 `sendChat`：** `unbind` 命令由模组的服务端命令处理器处理。`sendCommand` 隐含前导 `/`。

**参数处理：** 参数字符串中的反斜杠分隔符（`\`）被替换为别名定义分隔符（空格）。这意味着命令行在参数之间使用空格而不是反斜杠。

**要求：** `mc.player` 必须非 null。如果为 null，则记录一条警告。

**无界面抑制：** 在任何界面上都能工作——它是命令，不是游戏输入。

**与 `UnloadCFGBindsAlias` 和 `UnloadUserBindsAlias` 的关系：** 这些卸载别名以编程方式从客户端的 `BINDING_PLUS` 映射中移除按键绑定。`unbind` 别名则是将命令发送到服务端处理器。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | 以编程方式移除 CFG 加载的绑定 |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/UnloadUserBindsAlias.md) | 以编程方式移除运行时绑定 |
| [SendCommandAlias](../SendCommandAlias.java/SendCommandAlias.md) | 用于分发的底层 `sendCommand` |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
