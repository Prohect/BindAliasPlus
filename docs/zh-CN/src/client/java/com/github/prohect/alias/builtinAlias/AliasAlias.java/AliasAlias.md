# AliasAlias（src/client/java/com/github/prohect/alias/builtinAlias/AliasAlias.java）

通过服务端 `/alias` 命令在运行时定义或重新定义用户别名的内置别名。继承 `BuiltinAliasWithStringArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.AliasAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.AliasAlias>
```

## 静态初始化器

_无。_

## 备注

注册名为 `"alias"`。此别名将别名定义委托给服务端命令系统。它不在本地创建别名，而是通过 `player.connection.sendCommand()` 向服务器发送 `/alias` 命令。

参数格式为：`alias\<name> <definition>`。例如：
- `alias\turnDown setPitch\90` 定义一个名为 `turnDown` 的别名，将俯仰角设为 90
- `alias\switchAlias swapSlot\19 +use wait\1 -use swapSlot\19` 定义一个多别名链

实现通过前缀 `"alias"` + 别名定义分隔符来重建命令行，然后将参数中的任何参数分隔符（`\`）替换为正确的别名定义分隔符。这会规范化分隔符字符，使服务器收到一致的格式。

如果玩家为 null（未连接到服务器），则记录一条警告。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BindAlias](../BindAlias.java/BindAlias.md) | 向服务器发送 `/bind` 命令 |
| [UserAlias](../../UserAlias.java/UserAlias.md) | 用户定义别名的本地表示 |
| [Alias.divider4AliasDefinition](../../Alias.java/divider4AliasDefinition.md) | 别名定义的分隔符字符 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
