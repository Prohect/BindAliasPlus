# joinTick 字段（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public static long joinTick
```

## 备注

当 `ClientPlayConnectionEvents.JOIN` 触发（即玩家进入世界）时被设置为 `currentTick`。初始值为 `-1`，使 `tickPrefix()` 在首次加入前返回 `[client_tick:-1]`。加入后，`tickPrefix()` 计算 `currentTick - joinTick` 来报告加入以来的刻数。从不重置（即使断开连接）；重新加入时会再次更新它。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
