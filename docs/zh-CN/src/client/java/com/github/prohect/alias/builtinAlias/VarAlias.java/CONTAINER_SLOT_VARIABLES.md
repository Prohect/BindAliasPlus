# CONTAINER_SLOT_VARIABLES 字段（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

存储变量名 → 容器槽位索引（1 基）的映射，仅由 `cN` 源设置。

## 语法

```java
public static final java.util.Map<java.lang.String, java.lang.Integer> CONTAINER_SLOT_VARIABLES
```

## 备注

**用途：** 存储变量名到容器槽位编号（1 基）的平行映射。当变量以 `cN` 源创建时（例如 `var\mySlot\c5`），名称连同值 N 存储在这里。这使 `SwapSlotAlias.parseSlotRef()` 能够区分容器槽位引用和普通玩家物品栏槽位编号。

**写入者：** `VarAlias.run()`（两个重载）——为 `cN` 源添加条目，当非 `cN` 源覆盖同一变量名时移除条目。`UnloadUserVarsAlias`——移除不在 `CFG_CONTAINER_SLOT_VARIABLES` 中的运行时条目。

**读取者：** `SwapSlotAlias.parseSlotRef()`——唯一的消费者。在回退到 `resolveInt()` 之前检查此映射，以便 `cN` 源的变量被正确解释为容器槽位。

**关键设计洞察：** 没有此平行映射时，`swapSlot\mySlot` 会通过 `resolveInt()` 解析 `mySlot` 得到数字 5，将其视为玩家物品栏槽位 5。有了此映射，`parseSlotRef()` 首先检查 `CONTAINER_SLOT_VARIABLES`，找到条目并正确将其视为容器槽位 c5。

**线程安全：** 仅从游戏线程访问。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
