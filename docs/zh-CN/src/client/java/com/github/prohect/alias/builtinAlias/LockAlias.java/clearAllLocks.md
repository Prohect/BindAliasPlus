# clearAllLocks 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

恢复所有被锁定的游戏按键和自定义别名绑定，清除所有锁定状态。在服务器断开连接时调用，以防止过期的按键绑定。

## 语法

```java
public static void clearAllLocks()
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（无）_ | | |

## 备注

**算法：**

1. 遍历 `savedBoundKeys.keySet()` 的副本，对每个调用 `unlockAction(actionType)`——这会将每个原版 KeyBinding 恢复到其原始按键绑定。
2. 遍历 `LOCKED_ALIAS_KEYS.keySet()` 的副本，对每个调用 `unlockAliasByName(aliasName)`——这会移除自定义别名的物理按键阻止。
3. 完全清空 `LOCKED_PHYSICAL_KEYS`、`savedBoundKeys` 和 `LOCKED_ALIAS_KEYS`——以防解锁方法未能完全清理的防御性清理。

**生命周期：** 当客户端与服务器断开连接时由 `ClientPacketListenerMixin` 调用，确保跨服务器会话不会残留过期的锁定状态。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [lockAction](lockAction.md) | 单个动作的锁定 |
| [unlockAction](unlockAction.md) | 单个动作的解锁 |
| [ClientPacketListenerMixin](../../../mixin/ClientPacketListenerMixin.java/ClientPacketListenerMixin.md) | 断开连接时调用此方法 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
