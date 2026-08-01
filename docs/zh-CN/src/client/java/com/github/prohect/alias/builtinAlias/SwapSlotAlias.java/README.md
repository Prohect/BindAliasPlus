# SwapSlotAlias

在任意两个物品栏或容器槽位之间交换物品堆叠的复杂内置别名。用法：`swapSlot\a\b` 或 `swapSlot\a`（与选中的快捷栏槽位交换）。

## 字段

_无公共/受保护字段（内部使用私有 `SlotRef` record 表示槽位）。_

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | 解析 1-2 个槽位参数，确定策略，执行交换 |
| [parseSlotRef](parseSlotRef.md) | `parseSlotRef(String)` | 将槽位参数解析为玩家/容器 SlotRef |
| [resolveSlot](resolveSlot.md) | `resolveSlot(menu, SlotRef)` | 在菜单中查找与 SlotRef 匹配的 Slot 对象 |
| [swapButton](swapButton.md) | `swapButton(Slot)` | 若可通过快捷栏/副手寻址则获取 SWAP 点击按钮 |
| [swapInMenu](swapInMenu.md) | `swapInMenu(...)` | 使用 SWAP 或 PICKUP 策略交换两个槽位 |
| [clickSlot](clickSlot.md) | `clickSlot(...)` | 执行单次容器点击 |
| [swapSlotOffhand](swapSlotOffhand.md) | `swapSlotOffhand(...)` | 基于数据包的快捷栏↔副手交换 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [VarAlias](../VarAlias.java/README.md) | 槽位参数的变量解析 |
| [SlotAlias](../SlotAlias.java/README.md) | 选择快捷栏槽位 |
| [SwapHandAlias](../SwapHandAlias.java/README.md) | 简单的主手↔副手交换 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
