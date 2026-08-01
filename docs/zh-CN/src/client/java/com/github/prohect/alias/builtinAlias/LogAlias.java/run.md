# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/LogAlias.java）

以 INFO 级别将消息写入模组日志，并带有刻编号前缀。

## 语法

```java
public com.github.prohect.alias.builtinAlias.LogAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 要记录到日志的文本 |

## 备注

1. 调用 `BindAliasClient.LOGGER.info("{}{}", BindAliasClient.tickPrefix(), args)` 将消息写入模组日志。

输出格式为：`[tickPrefix]message`，其中 `tickPrefix` 是当前客户端刻编号（例如 `[t1234]`）。这有助于在调试别名脚本时将日志条目与游戏事件关联起来。

无需空值检查——日志记录器可以优雅地处理 null 参数。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LocalSayAlias.run()](../LocalSayAlias.java/run.md) | 在本地聊天界面中显示消息 |
| [BindAliasClient.LOGGER](../../../BindAliasClient.java/LOGGER.md) | 模组日志记录器实例 |
| [BindAliasClient.tickPrefix()](../../../BindAliasClient.java/tickPrefix.md) | 返回当前刻前缀 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
