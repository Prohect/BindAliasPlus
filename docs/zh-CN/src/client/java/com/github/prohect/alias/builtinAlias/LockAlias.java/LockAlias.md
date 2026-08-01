# LockAlias (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

临时锁定原版游戏按键或自定义用户别名的内置别名，防止玩家的物理键盘/鼠标输入干扰别名序列。继承 `BuiltinAliasWithArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.LockAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.LockAlias>
```

## 静态初始化

_参见 [static-init](static-init.md)。_

## 备注

注册名为 `"builtinLock"`。用法：`builtinLock\actionType\flag`，其中 `actionType` 是游戏按键动作或自定义别名名称，`flag` 为 `"1"`（锁定）或 `"0"`（解锁）。

**支持的按键动作**（使用 `gameKey:` 前缀）：`gameKey:attack`、`gameKey:use`、`gameKey:forward`、`gameKey:back`、`gameKey:left`、`gameKey:right`、`gameKey:jump`、`gameKey:sneak`、`gameKey:sprint`。

**原版按键的锁定机制：**
1. 保存动作的原始按键绑定（`InputUtil.Key`）。
2. 将 `KeyBinding.key` 替换为 `InputUtil.UNKNOWN_KEY`（GLFW_KEY_UNKNOWN = -1），GLFW 能优雅处理它，Minecraft 在 `releaseAll()` / 按键轮询中也会跳过它。
3. 调用 `KeyBinding.resetMapping()` 应用更改。
4. 同时锁定别名指向被锁定动作的任何模组绑定按键（`BINDING_PLUS` 条目），将其物理按键添加到 `LOCKED_PHYSICAL_KEYS`。

**自定义别名的锁定机制（`lockAliasByName`）：**
1. 在 `BindAliasClient.BINDING_PLUS` 中查找绑定别名名称（按下或松开时）与给定名称匹配的所有物理按键。
2. 将这些物理按键添加到 `LOCKED_PHYSICAL_KEYS`——键盘/鼠标 mixin 检查此集合以阻止输入。
3. 别名仍可通过 `builtinRunAlias` 以编程方式触发。

**面向用户的快捷方式：** `+lockKey\gameKey:attack`（[LockAlias_OnLock](../LockAlias_OnLock.java/LockAlias_OnLock.md)）和 `-lockKey\gameKey:attack`（[LockAlias_Unlock](../LockAlias_Unlock.java/LockAlias_Unlock.md)）。锁定自定义用户别名：`+lockKey\myAliasName`。

**清理：** 服务器断开连接时调用 `clearAllLocks()` 以恢复所有原始按键绑定并防止过期状态。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LockAlias_OnLock](../LockAlias_OnLock.java/LockAlias_OnLock.md) | 面向用户的 `+lockKey` 包装 |
| [LockAlias_Unlock](../LockAlias_Unlock.java/LockAlias_Unlock.md) | 面向用户的 `-lockKey` 包装 |
| [SUPPORTED_ACTIONS](SUPPORTED_ACTIONS.md) | 支持的按键动作类型列表 |
| [LOCKED_PHYSICAL_KEYS](LOCKED_PHYSICAL_KEYS.md) | mixin 检查以阻止物理输入的集合 |
| [clearAllLocks](clearAllLocks.md) | 断开连接时恢复所有锁定 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
