# clearAllLocks 方法（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

恢复所有被锁定的游戏按键和自定义别名绑定，清除全部锁定状态。在服务器断开时调用，防止残留过期的按键绑定。

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

1. 遍历 `savedBoundKeys.keySet()` 的副本，对每个键调用 `unlockAction(actionType)`——这将每个原版 KeyMapping 恢复到其原始按键绑定。
2. 遍历 `LOCKED_ALIAS_KEYS.keySet()` 的副本，对每个键调用 `unlockAliasByName(aliasName)`——这移除自定义别名的物理按键屏蔽。
3. 完全清空 `LOCKED_PHYSICAL_KEYS`、`savedBoundKeys` 和 `LOCKED_ALIAS_KEYS`——作为防御性清理，以防解锁方法未能完全清理。

**生命周期：** 客户端从服务器断开时由 `ClientPacketListenerMixin` 调用，确保跨服务器会话不残留锁定状态。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [lockAction](lockAction.md) | 单个动作锁定 |
| [unlockAction](unlockAction.md) | 单个动作解锁 |
| [ClientPacketListenerMixin](../../../mixin/ClientPacketListenerMixin.java/ClientPacketListenerMixin.md) | 断开时调用此方法 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
