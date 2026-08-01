# SlotAlias (src/client/java/com/github/prohect/alias/builtinAlias/SlotAlias.java)

选择快捷栏槽位（1-9）的内置别名。直接继承 `BuiltinAliasWithArgs`（而**不是** `BuiltinAliasWithIntegerArgs`），使其完全控制参数解析。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.SlotAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.SlotAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `slot` — 用法：`slot\N`，其中 N 是快捷栏槽位编号（1-9）或解析为 1-9 的变量名。

**行为：** 通过调用 `inventory.selectedSlot = i - 1)` 选择指定的快捷栏槽位，并向服务器发送 `UpdateSelectedSlotC2SPacket` 以同步更改。

**参数解析：** 参数通过 `VarAlias.resolveInt()` 解析，支持字面数字（1-9）和变量名。无效或超出范围的值会被拒绝并记录警告。

**为什么直接继承 BuiltinAliasWithArgs：** 与自动调用 `parseArgs(args)` 的 `BuiltinAliasWithIntegerArgs` 不同，`SlotAlias` 绕过自动解析以获得对参数验证更精细的控制，并直接使用 `VarAlias.resolveInt()`。它**不**用 `this.flag` 存储槽位编号。

**无界面抑制：** 在任意界面上都有效——无论打开什么 GUI，快捷栏选择都是有效的。

**实现说明：** `run()` 顶部注释掉的代码显示了早期使用 KeyBinding 按键绑定模拟快捷栏按键按下的方法。当前实现使用直接的物品栏操作 + 服务器数据包，以保证可靠性。

**边界情况：**
- args 为 null 或非整数：记录警告
- args 超出 1-9 范围：记录警告
- player 或 inventory 为 null：记录警告
- 服务器数据包发送失败：记录错误（但槽位仍在客户端更改）

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [VarAlias](../VarAlias.java/VarAlias.md) | 用于参数解析的变量系统 |
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md) | 在槽位之间交换物品 |
| [PickItemAlias](../PickItemAlias.java/PickItemAlias.md) | 选取方块以选择与目标匹配的槽位 |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 直接基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
