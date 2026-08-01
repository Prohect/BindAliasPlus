# getItemCountFromSlot 方法（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

使用 `"itemsOfSlotN"` 源模式返回指定槽位的物品数量。

## 语法

```java
private java.lang.Integer getItemCountFromSlot(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| source | String | 类似 `"itemsOfSlot0"`、`"itemsOfSlot5"` 等的源字符串 |

## 备注

**算法：**

1. 获取 `mc.player` 和 `player.getInventory()`——不可用则返回 null。
2. 通过去除 `"itemsOfSlot"` 前缀从源中提取槽位编号。
3. 将剩余字符串解析为整数 `slotIndex`。
4. 验证 `slotIndex` 在 [0, 9] 范围内。
   - 0 = 副手（内部物品栏索引 40）。
   - 1-9 = 快捷栏槽位（内部物品栏索引 0-8）。
5. 获取对应物品栏索引处的 `ItemStack`。
6. 返回 `stack.isEmpty() ? 0 : stack.getCount()`。

**返回值：** 物品数量（空/未找到槽位时为 0），如果玩家/物品栏不可用或槽位编号无效则返回 null。

**槽位映射：**

| 源 | 物品栏索引 |
|--------|----------------|
| `itemsOfSlot0` | 40（副手） |
| `itemsOfSlot1` | 0（快捷栏 1） |
| ... | ... |
| `itemsOfSlot9` | 8（快捷栏 9） |

**错误处理：**
- 无效槽位编号：记录带有效范围提示的错误。
- 玩家为 null：记录警告。
- 物品栏为 null：记录警告。
- 数字格式错误：记录错误。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getValueFromSource](getValueFromSource.md) | 主要调用者（用于 `"itemsOfSlotN"` 源） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
