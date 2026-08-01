# swapSlotOffhand 方法（src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java）

在快捷栏槽位与副手槽位之间执行快速基于数据包的交换，无需打开任何界面。

## 语法

```java
private static void swapSlotOffhand(net.minecraft.client.multiplayer.ClientPacketListener, int)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| networkHandler | ClientPacketListener | 用于发送数据包的网络处理器 |
| ratherOffhand | int | 要与副手交换的快捷栏槽位索引（0-8，从 0 开始） |

## 备注

**算法：**

1. 发送 `ServerboundSetCarriedItemPacket(ratherOffhand)`——选择指定的快捷栏槽位。
2. 发送 `ServerboundPlayerActionPacket(SWAP_ITEM_WITH_OFFHAND, BlockPos.ZERO, Direction.DOWN)`——将选中的快捷栏物品与副手物品交换。

**返回值：** void。

**副作用：** 将指定快捷栏槽位中的物品与副手物品交换。这是服务端操作——客户端物品栏在服务器响应时更新。

**两个快捷栏槽位的用法模式：** 调用方通过 `swapSlotOffhand` 使用 3 步序列：
1. 将 slot0 与副手交换
2. 将 slot1 与副手交换（现在 slot1 的物品在副手中，slot0 的物品在 slot1 中）
3. 将 slot0 与副手交换（slot1 的原始物品到 slot0）

这避免了打开任何界面，且在其他容器界面打开时也能工作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [run](run.md) | 对仅快捷栏交换调用此方法的主 run 方法 |
| [SwapHandAlias](../SwapHandAlias.java/SwapHandAlias.md) | 简单的主手↔副手交换 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
