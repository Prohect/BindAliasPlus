# SwapSlotAlias (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

在任意物品栏或容器槽位之间交换两个物品堆叠的内置别名。继承 `BuiltinAliasWithArgs`。这是最复杂的内置别名之一，同时支持玩家物品栏槽位（1-41）和容器菜单槽位（cN）。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.SwapSlotAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.SwapSlotAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `swapSlot` — 用法：`swapSlot\a\b`（两个槽位）或 `swapSlot\a`（与选中的快捷栏槽位交换）。

**槽位编号（玩家物品栏）：**
- 1-9：快捷栏槽位
- 10-36：主物品栏槽位（左上到右下）
- 37：脚部盔甲槽
- 38：腿部盔甲槽
- 39：胸部盔甲槽
- 40：头部盔甲槽
- 41：副手槽位

**容器槽位引用（cN）：**
- `c1`、`c2`、...、`cN` 指当前打开的容器菜单的第 N 个槽位（从 1 开始）
- 在任意容器界面中都有效：箱子、工作台、熔炉、铁砧、附魔台、锻造台、砂轮、织布机、切石机、村民交易等
- 容器槽位通常位于菜单槽位列表的前面，玩家物品栏槽位在最后

**单参数形式：** `swapSlot\a` 将槽位 `a` 与当前选中的快捷栏槽位交换。

**双参数形式：** `swapSlot\a\b` 将槽位 `a` 与槽位 `b` 交换。

**变量支持：** 槽位参数可以是经 `VarAlias.resolveInt()` 解析的变量名，或来自 `VarAlias.CONTAINER_SLOT_VARIABLES` 的容器槽位变量。

**三种交换策略（按优先级顺序）：**

1. **快捷栏/副手数据包交换：** 当两个槽位都是快捷栏（1-9）或副手（41）时，使用快速的三向副手交换序列交换物品，无需打开任何界面。即使另一个容器界面打开时也能工作。

2. **菜单中的 SWAP 点击：** 当其中一个槽位可寻址为快捷栏/副手且容器界面打开时，一次 `SlotActionType.SWAP` 点击即可高效完成交换。**限制：** 原版 SWAP 是全有或全无——如果快捷栏/副手物品无法放入目标容器槽位（例如将非燃料放入熔炉燃料槽），服务器会静默拒绝整个交换，两个物品都不会移动。

3. **菜单中的 PICKUP 序列：** 当两个槽位都不可寻址为快捷栏时，使用 PICKUP 点击序列：从 slot0 拾起，点击 slot1，放回 slot0。如果 slot0 拒绝放回（如合成产物之类的仅取出槽位），则恢复 slot1。此路径能优雅处理受限槽位。

**界面处理：**
- 仅在快捷栏/副手槽位之间交换时：无需界面（基于数据包的交换）。
- 容器界面（非物品栏）打开时：直接使用该界面的菜单。
- 否则：打开玩家物品栏界面，执行交换，然后关闭它。
- 如果打开的是物品栏/容器之外的任意界面且交换需要界面：静默返回（避免关闭意外的界面）。

**错误处理：** 对无效槽位编号、相同槽位、player/inventory/网络处理器为 null 以及当前菜单中找不到槽位的情况记录警告。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [VarAlias](../VarAlias.java/VarAlias.md) | 用于槽位参数解析的变量系统 |
| [SlotAlias](../SlotAlias.java/SlotAlias.md) | 选择快捷栏槽位 |
| [SwapHandAlias](../SwapHandAlias.java/SwapHandAlias.md) | 交换主手与副手 |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 直接基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
