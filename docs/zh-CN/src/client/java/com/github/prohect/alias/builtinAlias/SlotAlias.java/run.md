# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SlotAlias.java）

从参数解析快捷栏槽位编号（1-9）、验证它，并在客户端和服务端设置选中的槽位。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SlotAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 快捷栏槽位编号 1-9，或解析为 1-9 的变量名 |

## 备注

**算法：**

1. 调用 `VarAlias.resolveInt(args)` 解析参数。如果为 null（不是有效数字或变量），记录警告并返回。
2. 验证解析出的整数在 [1, 9] 范围内。如果不在，记录警告并返回。
3. 获取 `mc.player` 和 `player.getInventory()`。任一为 null 时，记录警告并返回。
4. 调用 `inventory.selectedSlot = i - 1)` 在本地设置快捷栏槽位。
5. 向服务器发送 `UpdateSelectedSlotC2SPacket(i - 1)` 以同步。
6. 如果数据包发送抛出异常，将其记录为错误。

**返回值：** `this`（流畅返回）。

**副作用：**
- 更改客户端的选中快捷栏槽位。
- 向服务器发送网络数据包以同步选中的槽位。
- 快捷栏视觉上更新以显示新的选择。

**无界面抑制：** 在任意界面上都有效。即使容器或物品栏界面打开时，选中的槽位也会更改（尽管槽位的功能取决于界面上下文）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SlotAlias](SlotAlias.md) | 类概览 |
| [VarAlias](../VarAlias.java/resolveInt.md) | 用于参数解析 |
| [SwapSlotAlias](../SwapSlotAlias.java/run.md) | 在槽位之间交换物品 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
