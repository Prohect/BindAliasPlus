# swapInMenu 方法（src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java）

在打开的容器菜单内交换两个槽位的内容，根据槽位可寻址性选择最佳策略。

## 语法

```java
private static void swapInMenu(net.minecraft.client.multiplayer.MultiPlayerGameMode, net.minecraft.world.inventory.AbstractContainerMenu, net.minecraft.world.inventory.Slot, net.minecraft.world.inventory.Slot, net.minecraft.client.player.LocalPlayer)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| interactionManager | MultiPlayerGameMode | 用于处理容器点击的游戏交互管理器 |
| menu | AbstractContainerMenu | 打开的容器菜单 |
| slot0 | Slot | 要交换的第一个槽位 |
| slot1 | Slot | 要交换的第二个槽位 |
| player | LocalPlayer | 本地玩家（用于点击上下文） |

## 备注

**算法（三种策略，按顺序尝试）：**

1. **通过 slot0 SWAP：** 若 `slot0` 可通过快捷栏/副手寻址（`swapButton(slot0) != -1`），对 `slot1` 使用 `button0` 执行单次 `ContainerInput.SWAP` 点击。这交换快捷栏物品与 slot1 的物品。

2. **通过 slot1 SWAP：** 若 `slot1` 可通过快捷栏/副手寻址，对 `slot0` 使用 `button1` 执行单次 SWAP 点击。

3. **PICKUP 回退序列：** 若两个槽位都无法通过快捷栏寻址：
   - 使用 `ContainerInput.PICKUP` 点击 `slot0`（拿起 slot0 的物品）。
   - 使用 `ContainerInput.PICKUP` 点击 `slot1`（将 slot0 的物品放入 slot1，拿起 slot1 的物品）。
   - 若光标仍有物品（slot0 不是只取槽位）：使用 PICKUP 点击 `slot0` 将 slot1 的物品放回。
   - 若光标**仍然**有物品（slot0 拒绝放回——它是像合成结果这样的只取槽位）：使用 PICKUP 点击 `slot1` 恢复 slot1 的原始物品。
   - 若光标**仍然**有物品：记录有关光标上孤立物品堆叠的警告。

**SWAP 路径限制：** 原版 SWAP 点击是全有或全无。若快捷栏/副手物品无法放入容器槽位（例如将非燃料放入熔炉燃料槽、将任意物品放入结果槽），服务器会静默拒绝整个交换，两个物品都不会移动。要从受限槽位取物品，请使用空的快捷栏槽位，或与非快捷栏的物品栏槽位（10-36）交换以回退到 PICKUP 路径，该路径能优雅处理拒绝。

**返回值：** void。

**副作用：** 修改两个槽位中的物品堆叠。向服务器发送点击数据包。

**错误处理：** 处理 slot0 拒绝放回（只取槽位）的情况，恢复 slot1 的原始物品。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [swapButton](swapButton.md) | 确定 SWAP 点击可寻址性 |
| [clickSlot](clickSlot.md) | 底层槽位点击处理器 |
| [run](run.md) | 调用此方法的主 run 方法 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
