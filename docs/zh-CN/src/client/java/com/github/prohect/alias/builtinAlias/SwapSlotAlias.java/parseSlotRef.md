# parseSlotRef 方法（src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java）

将单个槽位参数字符串解析为 `SlotRef`——玩家物品栏槽位索引（0-40）或容器槽位索引。

## 语法

```java
private static com.github.prohect.alias.builtinAlias.SwapSlotAlias.SlotRef parseSlotRef(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| arg | String | 槽位参数：`"cN"` 表示容器槽位，数字 1-41 表示玩家槽位，或变量名 |

## 备注

**算法：**

1. 去除参数字符串的空白。
2. 如果字符串以 'c' 开头且长度大于 1：将其余部分解析为整数 N。如果 N >= 1，返回索引为 `N-1`（0 基）的容器 SlotRef。
3. 在 `VarAlias.CONTAINER_SLOT_VARIABLES` 中查找去除空白后的名称——使用 `cN` 源创建的变量存储在这里。如果找到，返回 `value-1` 的容器 SlotRef。
4. 调用 `VarAlias.resolveInt(trimmed)` 将其解析为数字或变量。如果为 null，返回 null（无效）。
5. 转换为 0 基索引（`resolved - 1`）。如果在 [0, 40] 范围内，返回玩家 SlotRef。否则返回 null。

**返回值：** 一个 `SlotRef` 记录（container=true/false，index=0 基），如果参数无效则返回 null。

**边界情况：**
- cN 值始终被视为容器槽位，即使存在同名变量。
- 玩家槽位编号验证必须在 1-41 范围内。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [VarAlias](../VarAlias.java/CONTAINER_SLOT_VARIABLES.md) | 容器槽位变量存储 |
| [VarAlias](../VarAlias.java/resolveInt.md) | 玩家槽位的整数解析 |
| [resolveSlot](resolveSlot.md) | 在菜单中从 SlotRef 查找 Slot 对象 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
