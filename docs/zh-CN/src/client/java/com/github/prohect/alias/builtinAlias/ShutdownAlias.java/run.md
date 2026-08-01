# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/ShutdownAlias.java）

记录关闭消息并调用 `MinecraftClient.getInstance().scheduleStop()()` 干净地退出游戏。

## 语法

```java
public com.github.prohect.alias.builtinAlias.ShutdownAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 以 INFO 级别记录 `"[shutdown] Shutting down..."`，带刻前缀。
2. 调用 `MinecraftClient.getInstance().scheduleStop()` 安排优雅的游戏停止。（Yarn：`scheduleStop()`；Mojang：`stop()`）

**返回值：** `this`（流畅返回）——尽管游戏可能在调用方重新获得控制权之前退出。

**副作用：** 安排游戏停止。游戏将在当前刻结束时关闭，允许执行保存操作和资源清理。

**无界面抑制：** 在任意界面上都有效。

**安全性：** 使用干净的 `stop()` 方法，而不是强制 JVM 退出。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ShutdownAlias](ShutdownAlias.md) | 类概览 |
| [ReloadCFGAlias](../ReloadCFGAlias.java/run.md) | 另一个系统级别名 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
