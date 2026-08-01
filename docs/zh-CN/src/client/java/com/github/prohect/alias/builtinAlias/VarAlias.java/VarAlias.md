# VarAlias（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

存储和检索游戏中数值变量的内置别名。继承 `BuiltinAliasWithArgs`。这是许多其他内置别名用于参数解析的中央变量系统。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.VarAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.VarAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `var` — 用法：`var\varName\source`。

**变量存储架构：**

| 映射 | 类型 | 用途 |
|-----|------|---------|
| `GENERAL_VARIABLES` | `Map<String, Number>` | 所有通用变量名 → 数值（int 或 double） |
| `CONTAINER_SLOT_VARIABLES` | `Map<String, Integer>` | 变量名 → 容器槽位索引（1 基），仅由 `cN` 源设置，仅由 `SwapSlotAlias` 读取 |
| `CFG_VARIABLES` | `Set<String>` | 从 CFG 加载的通用变量名称（用于卸载跟踪） |
| `CFG_CONTAINER_SLOT_VARIABLES` | `Set<String>` | 从 CFG 加载的容器槽位变量名称（用于卸载跟踪） |

**有效源：**

| 源 | 类型 | 说明 |
|--------|------|-------------|
| `hotbarSlot` 或 `selectedSlot` | int | 当前选中的快捷栏槽位（1-9） |
| `itemsOfSlot0` | int | 副手中的物品数量（0 = 空） |
| `itemsOfSlot1`–`itemsOfSlot9` | int | 快捷栏槽位 1-9 中的物品数量 |
| `pitch` | double | 玩家当前的俯仰角 |
| `yaw` | double | 玩家当前的偏航角 |
| `cN`（例如 `c1`、`c5`、`c12`） | int | 容器槽位编号——同时存储在 `GENERAL_VARIABLES`（数值）和 `CONTAINER_SLOT_VARIABLES`（作为 `SwapSlotAlias` 的容器引用）中 |
| 字面数字 | int/double | 直接的整数或浮点值 |

**变量命名规则：** 名称不能以数字开头（由 `isValidVarName()` 使用正则表达式 `^[0-9].*` 验证）。null 或空名称也会被拒绝。

**两个 run() 重载：**
1. `run(String args)` — 标准运行时执行。存储在 `GENERAL_VARIABLES` 和 `CONTAINER_SLOT_VARIABLES` 中，无 CFG 跟踪。
2. `run(String args, boolean fromAutoload)` — 在 CFG 加载期间调用。当 `fromAutoload` 为 true 时，变量名称也会被添加到 `CFG_VARIABLES` 或 `CFG_CONTAINER_SLOT_VARIABLES` 中，供以后由 `unloadCFGVars` 清理。

**供其他别名使用的解析器：** 三个静态方法允许其他内置别名通过变量系统解析它们的参数：
- `resolveValue(String)` → `Number` — 将变量名或数字字符串解析为 Number。
- `resolveInt(String)` → `Integer` — 便捷方法，返回 int 值或 null。
- `resolveDouble(String)` → `Double` — 便捷方法，返回 double 值或 null。
- `isVariable(String)` → `boolean` — 检查字符串是否为当前存储的变量名。

**容器槽位语义：** 当变量以 `cN` 源创建时（例如 `var\mySlot\c5`），值 N **同时**存储在 `GENERAL_VARIABLES`（作为整数）**和** `CONTAINER_SLOT_VARIABLES`（作为 1 基槽位编号）中。`CONTAINER_SLOT_VARIABLES` 条目是关键，因为 `SwapSlotAlias.parseSlotRef()` 检查它来区分"此变量引用容器槽位"和"此变量持有数字 5"。没有这种双重存储，`swapSlot\mySlot` 会错误地将 `mySlot` 解释为玩家物品栏槽位 5，而不是容器槽位 c5。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md) | 容器槽位变量的主要消费者 |
| [SlotAlias](../SlotAlias.java/SlotAlias.md) | 使用 `resolveInt()` 选择槽位 |
| [PitchAlias](../PitchAlias.java/PitchAlias.md) | 使用 `resolveDouble()` 进行相对旋转 |
| [WaitAlias](../WaitAlias.java/WaitAlias.md) | 使用 `resolveInt()` 处理刻数 |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md) | 移除 CFG 加载的通用变量 |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/UnloadUserVarsAlias.md) | 移除运行时创建的变量 |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 直接基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
