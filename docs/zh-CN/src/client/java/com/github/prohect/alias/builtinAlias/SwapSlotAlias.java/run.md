# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java）

解析 1 或 2 个槽位参数（玩家 1-41、容器 cN 或变量）并交换它们的物品堆叠。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SwapSlotAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 一个槽位（`swapSlot\a`）或用 `\` 分隔的两个槽位（`swapSlot\a\b`） |

## 备注

**算法（高层）：**

1. 验证 `mc.player` 和物品栏非 null。
2. 按 `\`（反斜杠，`divider4AliasArgs`）拆分参数。
   - 1 个参数：与选中的快捷栏槽位交换。
   - 2 个参数：交换指定的两个槽位。
   - 其他：记录警告并返回。
3. 通过 `parseSlotRef()` 解析每个参数——解析玩家数字（1-41）、cN 引用和变量。
4. 验证两个槽位非 null 且不相同。
5. 确定界面上下文：物品栏、创造物品栏、容器界面或无。
6. 选择交换策略：
   - **仅快捷栏/副手：** 使用 `swapSlotOffhand()` 执行 3 步副手交换序列，无需界面。
   - **容器界面打开：** 使用该界面的菜单配合 `swapInMenu()`。
   - **其他情况：** 打开物品栏界面，交换，关闭物品栏界面。

**返回值：** `this`（流畅式返回）。

**副作用：** 交换两个槽位中的物品。可能临时打开/关闭玩家物品栏界面。根据使用的交换策略发送网络数据包以与服务器同步。

**无界面抑制：** 在任何非阻塞界面上都能工作，但如果打开了非物品栏/非容器界面且交换需要界面，则静默返回。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SwapSlotAlias](SwapSlotAlias.md) | 类概览 |
| [parseSlotRef](parseSlotRef.md) | 槽位参数解析 |
| [swapInMenu](swapInMenu.md) | 基于菜单的交换逻辑 |
| [swapSlotOffhand](swapSlotOffhand.md) | 副手数据包交换 |
| [resolveSlot](resolveSlot.md) | 在菜单中查找 Slot 对象 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
