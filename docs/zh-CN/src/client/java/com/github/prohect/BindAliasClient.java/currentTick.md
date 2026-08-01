# currentTick 字段（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public static long currentTick
```

## 备注

单调递增的计数器，由 `onInitializeClient` 中注册的 `ClientTickEvents.START_CLIENT_TICK` 每个客户端刻递增一次。游戏启动时从 0 开始，从不重置。与 `joinTick` 一起被 `tickPrefix()` 用于生成 `[client_tick:N]` 日志前缀（加入以来的刻数）。mixin 和别名读取它来进行基于刻的计时。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
