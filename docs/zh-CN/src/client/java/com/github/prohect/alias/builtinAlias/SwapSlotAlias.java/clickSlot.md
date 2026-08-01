# clickSlot 方法（src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java）

通过游戏的交互管理器执行单次槽位点击操作。

## 语法

```java
private static void clickSlot(net.minecraft.client.multiplayer.MultiPlayerGameMode, net.minecraft.world.inventory.AbstractContainerMenu, net.minecraft.world.inventory.Slot, int, net.minecraft.world.inventory.ContainerInput, net.minecraft.client.player.LocalPlayer)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| interactionManager | MultiPlayerGameMode | 游戏的交互管理器 |
| menu | AbstractContainerMenu | 打开的容器菜单 |
| slot | Slot | 要点击的槽位 |
| button | int | 按钮号：SWAP 时快捷栏槽位为 0-8，副手为 40，PICKUP 为 0 |
| input | ContainerInput | 点击类型：`SWAP` 或 `PICKUP` |
| player | LocalPlayer | 本地玩家 |

## 备注

**算法：**

1. 调用 `interactionManager.handleContainerInput(menu.containerId, slot.index, button, input, player)`。

**返回值：** void。

**副作用：** 向服务器发送带指定参数的容器点击数据包。服务器处理点击并相应更新物品堆叠。

**按钮语义：**
- 对于 `ContainerInput.PICKUP`：button=0 执行标准左键点击（拿起 / 放置）。
- 对于 `ContainerInput.SWAP`：button=0-8 选择对应的快捷栏槽位，button=40 选择副手槽位。

**校验：** 按钮值应为 0-8 或 40（由主要调用方 `swapButton()` 保证）。此方法内部不执行范围校验。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [swapButton](swapButton.md) | 提供按钮参数 |
| [swapInMenu](swapInMenu.md) | 此方法的主要调用方 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
