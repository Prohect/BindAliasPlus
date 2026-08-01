# VarAlias

中央变量存储和解析系统。用法：`var\varName\source`。支持游戏状态查询（`hotbarSlot`、`pitch`、`yaw`、`itemsOfSlotN`、`cN`）和字面数字。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [GENERAL_VARIABLES](GENERAL_VARIABLES.md) | `Map<String, Number>` | 所有变量名 → 数值（int 或 double） |
| [CONTAINER_SLOT_VARIABLES](CONTAINER_SLOT_VARIABLES.md) | `Map<String, Integer>` | 变量名 → 容器槽位索引（1 基），由 `cN` 源设置 |
| [CFG_VARIABLES](CFG_VARIABLES.md) | `Set<String>` | 从 CFG 加载的通用变量名称（用于卸载跟踪） |
| [CFG_CONTAINER_SLOT_VARIABLES](CFG_CONTAINER_SLOT_VARIABLES.md) | `Set<String>` | 从 CFG 加载的容器槽位变量名称（用于卸载跟踪） |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | 从源存储变量（运行时，无 CFG 跟踪） |
| [run](run.md) | `run(String args, boolean fromAutoload)` | 存储变量并可选择进行 CFG 自动加载跟踪 |
| [fromContainerSlotSource](fromContainerSlotSource.md) | `fromContainerSlotSource(String)` | 将 cN 源字符串解析为 1 基槽位编号 |
| [isValidVarName](isValidVarName.md) | `isValidVarName(String)` | 验证变量名称（不能以数字开头） |
| [getValueFromSource](getValueFromSource.md) | `getValueFromSource(String)` | 将源字符串解析为 Number |
| [getCurrentHotbarSlot](getCurrentHotbarSlot.md) | `getCurrentHotbarSlot()` | 获取当前快捷栏槽位（1-9） |
| [getItemCountFromSlot](getItemCountFromSlot.md) | `getItemCountFromSlot(String)` | 从 itemsOfSlotN 源获取物品数量 |
| [getPlayerPitch](getPlayerPitch.md) | `getPlayerPitch()` | 获取当前俯仰角 |
| [getPlayerYaw](getPlayerYaw.md) | `getPlayerYaw()` | 获取当前偏航角 |
| [resolveValue](resolveValue.md) | `resolveValue(String)` | 将变量名或数字解析为 Number |
| [resolveInt](resolveInt.md) | `resolveInt(String)` | 解析为 int（便捷方法） |
| [resolveDouble](resolveDouble.md) | `resolveDouble(String)` | 解析为 double（便捷方法） |
| [isVariable](isVariable.md) | `isVariable(String)` | 检查名称是否为已存储的变量 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SwapSlotAlias](../SwapSlotAlias.java/README.md) | 容器槽位变量的主要消费者 |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/README.md) | 移除 CFG 加载的变量 |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/README.md) | 移除运行时变量 |
| [SlotAlias](../SlotAlias.java/README.md) | 使用 resolveInt 选择槽位 |
| [WaitAlias](../WaitAlias.java/README.md) | 使用 resolveInt 处理刻数 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
