# SlotAlias (src/client/java/com/github/prohect/alias/builtinAlias/SlotAlias.java)

选择快捷栏槽位（1-9）的内置别名。直接继承 `BuiltinAliasWithArgs`（而非 `BuiltinAliasWithIntegerArgs`），从而完全控制参数解析。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.SlotAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.SlotAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `slot` — 用法：`slot\N`，其中 N 是快捷栏槽位号（1-9）或解析为 1-9 的变量名。

**行为：** 通过调用 `inventory.setSelectedSlot(i - 1)` 选择指定的快捷栏槽位，并发送 `ServerboundSetCarriedItemPacket` 到服务器以同步更改。

**参数解析：** 参数通过 `VarAlias.resolveInt()` 解析，同时支持字面数字（1-9）和变量名。无效或超出范围的值会被拒绝并记录警告。

**为何直接继承 BuiltinAliasWithArgs：** 与自动调用 `parseArgs(args)` 的 `BuiltinAliasWithIntegerArgs` 不同，`SlotAlias` 绕过自动解析，以便更精细地控制参数校验并直接使用 `VarAlias.resolveInt()`。它**不**使用 `this.flag` 存储槽位号。

**无界面抑制：** 在任何界面上均有效——无论打开什么 GUI，快捷栏选择都有效。

**实现说明：** `run()` 顶部注释掉的代码展示了早期使用 `KeyMapping` 按键绑定模拟快捷栏按键按下的方案。当前实现使用直接物品栏操纵 + 服务器数据包以确保可靠性。

**边界情况：**
- args 为 null 或非整数：记录为警告
- args 超出 1-9 范围：记录为警告
- 玩家或物品栏为 null：记录为警告
- 服务器数据包发送失败：记录为错误（但槽位在客户端侧仍会改变）

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [VarAlias](../VarAlias.java/VarAlias.md) | 用于参数解析的变量系统 |
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md) | 交换槽位之间的物品 |
| [PickItemAlias](../PickItemAlias.java/PickItemAlias.md) | 选取方块以选择与目标匹配的槽位 |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 直接基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
