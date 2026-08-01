# swapInMenu 方法（src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java）

在打开的容器菜单内交换两个槽位的内容，根据槽位可寻址性选择最佳策略。

## 语法

```java
private static void swapInMenu(net.minecraft.client.network.ClientPlayerInteractionManager, net.minecraft.screen.ScreenHandler, net.minecraft.screen.slot.Slot, net.minecraft.screen.slot.Slot, net.minecraft.client.network.ClientPlayerEntity)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| interactionManager | ClientPlayerInteractionManager | 游戏的处理容器点击的交互管理器（Yarn：`ClientPlayerInteractionManager`；Mojang：`MultiPlayerGameMode`） |
| menu | ScreenHandler | 打开的容器菜单（Yarn：`ScreenHandler`；Mojang：`AbstractContainerMenu`） |
| slot0 | Slot | 要交换的第一个槽位 |
| slot1 | Slot | 要交换的第二个槽位 |
| player | ClientPlayerEntity | 本地玩家（Yarn：`ClientPlayerEntity`；Mojang：`ClientPlayerEntity`） |

## 备注

**算法（三种策略，按顺序尝试）：**

1. **通过 slot0 进行 SWAP：** 如果 `slot0` 可寻址为快捷栏/副手（`swapButton(slot0) != -1`），则在 `slot1` 上以 `button0` 执行一次 `SlotActionType.SWAP` 点击。这交换快捷栏物品与 slot1 的物品。

2. **通过 slot1 进行 SWAP：** 如果 `slot1` 可寻址为快捷栏/副手，则在 `slot0` 上以 `button1` 执行一次 SWAP 点击。

3. **PICKUP 回退序列：** 如果两个槽位都不可寻址为快捷栏：
   - 以 `SlotActionType.PICKUP` 点击 `slot0`（拾起 slot0 的物品）。
   - 以 `SlotActionType.PICKUP` 点击 `slot1`（将 slot0 的物品放入 slot1，拾起 slot1 的物品）。
   - 如果光标仍有物品（slot0 不是仅取出槽位）：以 PICKUP 点击 `slot0` 将 slot1 的物品放回。
   - 如果光标**仍然**有物品（slot0 拒绝放回——它是仅取出槽位，如合成产物槽）：以 PICKUP 点击 `slot1` 恢复 slot1 的原始物品。
   - 如果光标**仍然**有物品：记录关于光标上孤立物品堆叠的警告。

**SWAP 路径限制：** 原版 SWAP 点击是全有或全无。如果快捷栏/副手物品无法放入容器槽位（例如将非燃料放入熔炉燃料槽、将任何物品放入产物槽），服务器会静默拒绝整个交换，两个物品都不会移动。要从受限槽位取物品，请使用空的快捷栏槽位或与非快捷栏物品栏槽位（10-36）交换，以回退到 PICKUP 路径，该路径能优雅处理拒绝。

**返回值：** void。

**副作用：** 修改两个槽位中的物品堆叠。向服务器发送点击数据包。

**错误处理：** 处理 slot0 拒绝放回（仅取出槽位）的情况，恢复 slot1 的原始物品。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [swapButton](swapButton.md) | 确定 SWAP 点击可寻址性 |
| [clickSlot](clickSlot.md) | 底层槽位点击处理 |
| [run](run.md) | 调用此方法的主 run 方法 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
