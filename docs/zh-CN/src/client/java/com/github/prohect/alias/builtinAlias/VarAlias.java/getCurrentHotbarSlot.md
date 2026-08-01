# getCurrentHotbarSlot 方法（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

返回当前选中的快捷栏槽位编号（1-9）。

## 语法

```java
private java.lang.Integer getCurrentHotbarSlot()
```

## 备注

**算法：**

1. 获取 `mc.player` 和 `player.getInventory()`。
2. 如果任一为 null，记录一条警告并返回 null。
3. 调用 `inventory.getSelectedSlot()`，返回 0-8。
4. 加 1 以转换为模组的 1-9 约定。

**返回值：** 1-9 的 Integer，如果玩家/物品栏不可用则返回 null。

**约定：** 原版 `getSelectedSlot()` 返回 0 基（0-8）。此方法加 1 以匹配 `slot\N` 命令约定（1-9）。

**错误处理：** 如果不可用，记录 `"[var] Player is null"` 或 `"[var] Inventory is null"`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getValueFromSource](getValueFromSource.md) | 主要调用者（用于 `"hotbarSlot"` 源） |
| [SlotAlias](../SlotAlias.java/run.md) | 直接选择快捷栏槽位 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
