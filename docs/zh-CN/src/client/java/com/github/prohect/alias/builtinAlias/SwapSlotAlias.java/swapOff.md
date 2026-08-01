# swapOff 方法（src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java）

## 语法

```java
private static void swapOff(ClientPlayNetworkHandler net, int idx)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `net` | `ClientPlayNetworkHandler` | 用于发送数据包的网络处理器 |
| `idx` | `int` | 要与副手交换的快捷栏槽位索引（从 0 开始） |

## 备注

私有静态辅助方法，使用两个原版数据包执行快捷栏↔副手交换：

1. **`UpdateSelectedSlotC2SPacket(idx)`** —— 临时选中快捷栏槽位 `idx`（从 0 开始，对应快捷栏槽位 1–9）。
2. **`PlayerActionC2SPacket(SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN)`** —— 发送副手交换动作，将当前选中的快捷栏物品与副手物品交换。

这是一个数据包级操作，无需打开任何界面即可工作，仅依赖网络处理器。它不需要容器菜单或交互管理器，因此即使在另一个容器界面打开时也能执行。

`SwapSlotAlias.run()` 在“内部”交换策略中使用该方法：当两个槽位均为快捷栏（0–8）或副手（40）时，该方法使用最多三次 `swapOff` 调用的序列实现三方副手轮换，从而交换两个目标槽位而不影响副手槽位的最终内容。所有 `swapOff` 调用完成后，选中的快捷栏槽位通过另一个 `UpdateSelectedSlotC2SPacket` 恢复为原值。

该方法在 26.x（Mojang）分支中的对应版本名为 `swapSlotOffhand`，发送的也是同样的两类数据包——重命名与 Yarn 映射约定保持一致，其中动作常量为 `SWAP_ITEM_WITH_OFFHAND`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [run](run.md) | 调用方——在快捷栏/副手交换策略中使用 swapOff |
| [SwapSlotAlias](SwapSlotAlias.md) | 所属类 |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
