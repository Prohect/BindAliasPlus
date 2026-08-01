# SwapHandAlias (src/client/java/com/github/prohect/alias/builtinAlias/SwapHandAlias.java)

通过原版 SWAP 动作数据包交换主手和副手物品的一次性别名。继承自 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.SwapHandAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.SwapHandAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `swapHand`（内部，以 `swapHand` 暴露）。

**行为：** 向服务器发送动作 `SWAP_ITEM_WITH_OFFHAND` 的 `ServerboundPlayerActionPacket`。这交换玩家主手持有的物品与副手物品。

**为何用网络数据包而非按键绑定：** 注释掉的代码展示了早期使用 `keySwapOffhand` 按键绑定的方案。当前实现直接发送网络数据包以提高可靠性——它绕过按键绑定轮询周期，且与按键绑定状态无关。

**参数：** 数据包以 `BlockPos.ZERO` 和 `Direction.DOWN` 作为占位值发送（对 SWAP_ITEM_WITH_OFFHAND 动作，服务器会忽略它们）。

**界面抑制：** 当 `Alias.isUnderTextInputScreen()` 返回 true 时被取消。

**要求：** 网络处理器（`mc.getConnection()`）必须非 null。若为 null 则记录警告。

**注意：** 交换后，先前选中的快捷栏槽位仍持有相同的物理物品（现在在另一只手中）。物品交换逻辑由服务器处理。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md) | 交换任意两个物品栏/容器槽位 |
| [SlotAlias](../SlotAlias.java/SlotAlias.md) | 选择快捷栏槽位 |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | 一次性别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
