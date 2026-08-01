# swapButton 方法（src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java）

返回槽位的 SWAP 点击按钮号（若该槽位可通过快捷栏/副手直接寻址）。

## 语法

```java
private static int swapButton(net.minecraft.world.inventory.Slot)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| slot | Slot | 要检查 SWAP 点击可寻址性的槽位 |

## 备注

**算法：**

1. 若槽位的容器不是玩家 `Inventory`，返回 -1（不可寻址）。
2. 获取 `containerSlot` 索引：
   - 0-8：快捷栏槽位 → 返回 containerSlot 值（0-8）。
   - 40：副手槽位 → 返回 40。
   - 其他：返回 -1。

**返回值：** SWAP 按钮号（快捷栏为 0-8，副手为 40），若槽位不能直接 SWAP 寻址则返回 -1。

**用法：** 对另一个槽位使用此按钮号进行 SWAP 点击，可在快捷栏/副手物品与被点击槽位的物品之间执行双向交换（适用于任何支持 `ContainerInput.SWAP` 的菜单）。

**原版约束：** SWAP 点击要求按钮槽位能够接收物品。若快捷栏/副手物品无法放入目标槽位（例如将非燃料放入熔炉燃料槽），原版会静默拒绝交换。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [swapInMenu](swapInMenu.md) | 使用此方法确定交换策略 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
