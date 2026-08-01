# click 方法（src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java）

## 语法

```java
private static void click(ClientPlayerInteractionManager im, ScreenHandler menu, Slot s, int btn, SlotActionType act, ClientPlayerEntity p)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `im` | `ClientPlayerInteractionManager` | 用于发送点击数据包的客户端交互管理器 |
| `menu` | `ScreenHandler` | 容器菜单，其 `syncId` 向服务器标识对应的窗口 |
| `s` | `Slot` | 要点击的目标槽位；其 `id` 是该槽位在菜单中的索引 |
| `btn` | `int` | 点击动作的鼠标按键/数据值 |
| `act` | `SlotActionType` | 点击动作的类型（`SWAP`、`PICKUP` 等） |
| `p` | `ClientPlayerEntity` | 本地玩家（`clickSlot` 所需） |

## 备注

私有静态辅助方法，通过委托给 `ClientPlayerInteractionManager#clickSlot(int syncId, int slotId, int button, SlotActionType action, PlayerEntity player)` 来执行一次容器点击。这是 `SwapSlotAlias` 中发送容器交互数据包的唯一位置——`swapInMenu` 和 PICKUP 回退路径都会经过此方法。

在 1.21.x（Yarn）分支上，原版方法在 `ClientPlayerInteractionManager` 中名为 `clickSlot`；模组将其包装在此 `click` 方法中，以保持调用点简洁并集中管理参数映射。`syncId` 标识该点击所属的容器窗口（服务端校验所需），`s.id` 是槽位在该窗口槽位列表中的位置。

`swapInMenu` 以三种模式使用该方法：
- **SWAP 点击**（`SlotActionType.SWAP`，`btn` = 快捷栏索引）：通过单个数据包交换快捷栏/副手物品与目标槽位中的物品。
- **PICKUP 点击**（`SlotActionType.PICKUP`，`btn = 0`）：逐个拾取或放置物品——用于两个槽位均无法通过快捷栏寻址的情况。
- **清理 PICKUP**：在 PICKUP 序列失败后，将残留物品移离光标。

该方法在 26.x（Mojang）分支中的对应版本名为 `clickSlot`，使用的是 `MultiPlayerGameMode#handleInventoryMouseClick`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [swapInMenu](swapInMenu.md) | 主要调用方——协调交换策略 |
| [SwapSlotAlias](SwapSlotAlias.md) | 所属类 |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
