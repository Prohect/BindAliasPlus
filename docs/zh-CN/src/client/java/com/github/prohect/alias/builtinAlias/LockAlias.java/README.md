# LockAlias

用于临时锁定原版游戏按键和自定义别名按键绑定的复杂内置别名。将 KeyMapping 的按键替换为 `InputConstants.UNKNOWN`，并在 mixin 检查的集合中跟踪被锁定的物理按键。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [SUPPORTED_ACTIONS](SUPPORTED_ACTIONS.md) | `static final List<String>` | 所有受支持的游戏按键动作类型，带 `gameKey:` 前缀 |
| [LOCKED_PHYSICAL_KEYS](LOCKED_PHYSICAL_KEYS.md) | `static final Set<Key>` | 当前被屏蔽的物理按键；由键盘/鼠标 mixin 检查 |
| `savedBoundKeys` | `static final Map<String, Key>` | 按动作类型保存的原始按键绑定（私有，包内访问） |
| `LOCKED_ALIAS_KEYS` | `static final Map<String, Set<Key>>` | 按别名名称跟踪基于别名的锁定按键（私有，包内访问） |
| `ACTION_ALIAS_PATTERNS` | `static final Map<String, List<String>>` | 将裸动作名映射到别名名称模式（由 static-init 填充） |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `LockAlias run(String args)` | 解析 `actionType\flag` 并分发锁定/解锁 |
| [lockAction](lockAction.md) | `static void lockAction(String)` | 锁定原版游戏按键，或回退到 lockAliasByName |
| [unlockAction](unlockAction.md) | `static void unlockAction(String)` | 解锁原版游戏按键，或回退到 unlockAliasByName |
| [lockAliasByName](lockAliasByName.md) | `static void lockAliasByName(String)` | 锁定绑定到自定义别名名称的所有物理按键 |
| [unlockAliasByName](unlockAliasByName.md) | `static void unlockAliasByName(String)` | 解锁自定义别名名称的物理按键 |
| [clearAllLocks](clearAllLocks.md) | `static void clearAllLocks()` | 恢复所有锁定；服务器断开时调用 |
| [getKeyBindingForAction](getKeyBindingForAction.md) | `static KeyMapping getKeyBindingForAction(String)` | 将动作类型映射到原版 KeyMapping |
| [lockModBoundKeys](lockModBoundKeys.md) | `static void lockModBoundKeys(String)` | 屏蔽指向被锁定动作的模组绑定按键 |
| [unlockModBoundKeys](unlockModBoundKeys.md) | `static void unlockModBoundKeys(String)` | 移除某动作的模组按键锁定（若不再需要） |
| [aliasTargetsLockedAction](aliasTargetsLockedAction.md) | `static boolean aliasTargetsLockedAction(String, List)` | 递归检查别名是否指向被锁定的动作 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LockAlias_OnLock](../LockAlias_OnLock.java/LockAlias_OnLock.md) | 面向用户的 `+lockKey` 包装 |
| [LockAlias_Unlock](../LockAlias_Unlock.java/LockAlias_Unlock.md) | 面向用户的 `-lockKey` 包装 |
| [ClientPacketListenerMixin](../../../mixin/ClientPacketListenerMixin.java/ClientPacketListenerMixin.md) | 断开时调用 `clearAllLocks()` |
| [KeyBoardMixin](../../../mixin/KeyBoardMixin.java/KeyBoardMixin.md) | 在按键事件中检查 `LOCKED_PHYSICAL_KEYS` |
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | 在鼠标事件中检查 `LOCKED_PHYSICAL_KEYS` |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
