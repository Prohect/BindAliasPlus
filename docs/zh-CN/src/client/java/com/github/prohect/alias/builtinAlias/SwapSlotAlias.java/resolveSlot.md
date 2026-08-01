# resolveSlot 方法（src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java）

在容器菜单中查找与给定 `SlotRef` 匹配的实际 `Slot` 对象。

## 语法

```java
private static net.minecraft.world.inventory.Slot resolveSlot(net.minecraft.world.inventory.AbstractContainerMenu, com.github.prohect.alias.builtinAlias.SwapSlotAlias.SlotRef)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| menu | AbstractContainerMenu | 当前打开的容器菜单 |
| ref | SlotRef | 已解析的槽位引用（容器或玩家） |

## 备注

**算法：**

- **容器 SlotRef：** 通过 `ref.index()` 直接索引 `menu.slots`。若索引在范围内则返回该槽位，否则返回 null。
- **玩家 SlotRef：** 遍历菜单中的所有槽位，返回第一个满足 `slot.getContainerSlot() == ref.index()` 且 `slot.container instanceof Inventory`（确保是玩家物品栏槽位，而非具有相同索引的其他容器的槽位）的槽位。

**返回值：** 匹配的 `Slot` 对象，未找到则返回 null。

**为何需要容器检查：** 在某些菜单（例如工作台）中，多个槽位可能具有来自不同容器的相同 `containerSlot` 索引。`instanceof Inventory` 检查确保只匹配玩家的物品栏槽位。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [parseSlotRef](parseSlotRef.md) | 创建此方法要解析的 SlotRef |
| [swapInMenu](swapInMenu.md) | 使用解析出的槽位执行交换 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
