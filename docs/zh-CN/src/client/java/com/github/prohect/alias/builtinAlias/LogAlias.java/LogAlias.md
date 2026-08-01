# LogAlias（src/client/java/com/github/prohect/alias/builtinAlias/LogAlias.java）

将消息写入模组日志文件的内置别名。继承 `BuiltinAliasWithArgs`（接受原始字符串参数）。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.LogAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.LogAlias>
```

## 静态初始化器

_无。_

## 备注

注册名为 `"log"`。用法：`log\<text>`。

通过 `BindAliasClient.LOGGER.info()` 以 INFO 级别将消息写入模组日志。消息以 `BindAliasClient.tickPrefix()`（当前客户端刻编号）为前缀，用于时间戳标识。

这是别名脚本的主要调试输出渠道——与 `localSay` 不同，日志输出不会出现在游戏聊天界面中，而是保留在日志文件中供日后查看。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LocalSayAlias](../LocalSayAlias.java/LocalSayAlias.md) | 在本地聊天界面中显示消息 |
| [SayAlias](../SayAlias.java/SayAlias.md) | 将消息发送到服务器聊天界面 |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 通用参数别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
