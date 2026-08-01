# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SlotAlias.java）

从参数解析快捷栏槽位号（1-9），校验它，并在客户端和服务端都设置选中槽位。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SlotAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 快捷栏槽位号 1-9，或解析为 1-9 的变量名 |

## 备注

**算法：**

1. 调用 `VarAlias.resolveInt(args)` 解析参数。若为 null（不是有效数字或变量），记录警告并返回。
2. 校验解析出的整数在 [1, 9] 范围内。若不是，记录警告并返回。
3. 获取 `mc.player` 和 `player.getInventory()`。若任一为 null，记录警告并返回。
4. 调用 `inventory.setSelectedSlot(i - 1)` 在本地设置快捷栏槽位。
5. 向服务器发送 `ServerboundSetCarriedItemPacket(i - 1)` 以同步。
6. 若数据包发送抛出异常，将其记录为错误。

**返回值：** `this`（流畅式返回）。

**副作用：**
- 改变客户端上选中的快捷栏槽位。
- 向服务器发送网络数据包以同步选中的槽位。
- 快捷栏视觉更新以显示新的选中项。

**无界面抑制：** 在任何界面上均有效。即使在容器或物品栏界面打开时选中的槽位也会改变（不过槽位的功能取决于界面上下文）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SlotAlias](SlotAlias.md) | 类概览 |
| [VarAlias](../VarAlias.java/resolveInt.md) | 用于参数解析 |
| [SwapSlotAlias](../SwapSlotAlias.java/run.md) | 交换槽位之间的物品 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
