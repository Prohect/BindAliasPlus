# getValueFromSource 方法（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

通过检查每种已知源类型将源字符串解析为数值。

## 语法

```java
private java.lang.Number getValueFromSource(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| source | String | 源标识符：`"hotbarSlot"`、`"itemsOfSlotN"`、`"pitch"`、`"yaw"`、`"cN"` 或字面数字 |

## 备注

**算法（按顺序求值）：**

1. **`hotbarSlot` / `selectedSlot`：** 通过 `getCurrentHotbarSlot()` 返回当前快捷栏槽位（1-9）。
2. **`itemsOfSlotN`：** 如果以 `"itemsOfSlot"` 开头（不区分大小写），解析尾随数字（0-9）并通过 `getItemCountFromSlot()` 返回物品数量。
3. **`pitch`：** 通过 `getPlayerPitch()` 返回玩家俯仰角。
4. **`yaw`：** 通过 `getPlayerYaw()` 返回玩家偏航角。
5. **`cN`：** 通过 `fromContainerSlotSource()` 解析。如果有效，返回整数 N。
6. **字面数字：** 先尝试 `Integer.parseInt(source)`，然后尝试 `Double.parseDouble(source)`。如果都失败，记录错误并返回 null。

**返回值：** 成功时返回 `Number`（Integer 或 Double），如果源无法识别或玩家/物品栏不可用则返回 null。

**错误处理：** 未知源会记录一条带有效源类型列表的错误。玩家/物品栏为 null 时返回 null（不记录日志——由检查 null 的 `run()` 处理）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getCurrentHotbarSlot](getCurrentHotbarSlot.md) | 快捷栏槽位解析器 |
| [getItemCountFromSlot](getItemCountFromSlot.md) | 物品数量解析器 |
| [getPlayerPitch](getPlayerPitch.md) | 俯仰角解析器 |
| [getPlayerYaw](getPlayerYaw.md) | 偏航角解析器 |
| [fromContainerSlotSource](fromContainerSlotSource.md) | cN 解析器 |
| [run](run.md) | 主要调用者 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
