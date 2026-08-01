# BindAlias（src/client/java/com/github/prohect/alias/builtinAlias/BindAlias.java）

向服务器发送 `/bind` 命令以为别名创建按键绑定的内置别名。继承 `BuiltinAliasWithStringArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.BindAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.BindAlias>
```

## 静态初始化器

_无。_

## 备注

注册名为 `"bind"`。此别名将按键绑定的创建委托给服务端命令系统。它不在本地绑定按键，而是通过 `player.connection.sendCommand()` 发送 `/bind` 命令。

参数格式为：`bind\<aliasName> <key>`。例如：`bind\+attack mouse.left` 将鼠标左键绑定到 `+attack` 别名。

实现通过前缀 `"bind"` + 别名定义分隔符来重建命令行，然后将参数中的任何参数分隔符（`\`）规范化为正确的别名定义分隔符字符。这确保无论链分隔符如何书写，服务器都会收到一致的格式。

如果玩家为 null（未连接到服务器），则记录一条警告。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasAlias](../AliasAlias.java/AliasAlias.md) | 发送 `/alias` 命令以定义/重新定义别名 |
| [BindAliasKeyBinding](../../BindAliasKeyBinding.java/BindAliasKeyBinding.md) | 本地按键绑定表示 |
| [Alias.divider4AliasDefinition](../../Alias.java/divider4AliasDefinition.md) | 分隔符字符 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
