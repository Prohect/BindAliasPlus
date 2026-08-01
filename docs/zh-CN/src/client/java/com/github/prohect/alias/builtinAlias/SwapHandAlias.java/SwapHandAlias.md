# SwapHandAlias (src/client/java/com/github/prohect/alias/builtinAlias/SwapHandAlias.java)

通过原版 SWAP 动作数据包交换主手和副手物品的一次性别名。继承 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.SwapHandAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.SwapHandAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `swapHand`（内部，作为 `swapHand` 暴露）。

**行为：** 向服务器发送动作为 `SWAP_ITEM_WITH_OFFHAND` 的 `PlayerActionC2SPacket`。这交换玩家的主手持握物品和副手物品。（Yarn：`PlayerActionC2SPacket`；Mojang：`ServerboundPlayerActionPacket`）

**为什么用网络数据包而不是按键绑定：** 注释掉的代码显示了早期使用 `keySwapOffhand` 按键绑定的方法。当前实现直接发送网络数据包以保证可靠性——它绕过了按键绑定轮询周期，无论按键绑定状态如何都能工作。

**参数：** 数据包以 `BlockPos.ORIGIN` 和 `Direction.DOWN` 作为占位值发送（服务器对 SWAP_ITEM_WITH_OFFHAND 动作会忽略它们）。（Yarn：`BlockPos.ORIGIN`；Mojang：`BlockPos.ZERO`）

**界面抑制：** 当 `Alias.isUnderTextInputScreen()` 返回 true 时被取消。

**要求：** 网络处理器（`mc.getNetworkHandler()`）必须非空。为 null 时记录警告。

**注意：** 交换后，先前选中的快捷栏槽位仍然持有相同的物理物品（现在在另一只手中）。服务器处理物品交换逻辑。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md) | 交换任意两个物品栏/容器槽位 |
| [SlotAlias](../SlotAlias.java/SlotAlias.md) | 选择快捷栏槽位 |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | 一次性别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
