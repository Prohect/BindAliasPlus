# tickPrefix 方法（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public static java.lang.String tickPrefix()
```

## 参数

_无。_

## 备注

返回带方括号的"加入以来刻数"前缀字符串，用于一致的日志格式。若玩家从未加入（`joinTick < 0`），返回 `[client_tick:-1]`。否则返回 `[client_tick:N]`，其中 `N = currentTick - joinTick`。整个模组在记录日志时都使用它，为服务器日志和模组日志文件提供时间上下文。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [currentTick](currentTick.md) | 单调递增的刻计数器 |
| [joinTick](joinTick.md) | 玩家上次加入世界时的刻 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
