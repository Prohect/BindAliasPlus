# VarAlias

Central variable storage and resolution system. Usage: `var\varName\source`. Supports game state queries (`hotbarSlot`, `pitch`, `yaw`, `itemsOfSlotN`, `cN`) and literal numbers.

## Fields

| Name | Type | Description |
|------|------|-------------|
| [GENERAL_VARIABLES](GENERAL_VARIABLES.md) | `Map<String, Number>` | All variable name → numeric value (int or double) |
| [CONTAINER_SLOT_VARIABLES](CONTAINER_SLOT_VARIABLES.md) | `Map<String, Integer>` | Variable name → container slot index (1-based), set by `cN` source |
| [CFG_VARIABLES](CFG_VARIABLES.md) | `Set<String>` | Names of general variables loaded from CFG (for unload tracking) |
| [CFG_CONTAINER_SLOT_VARIABLES](CFG_CONTAINER_SLOT_VARIABLES.md) | `Set<String>` | Names of container slot variables loaded from CFG (for unload tracking) |

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | Store variable from source (runtime, no CFG tracking) |
| [run](run.md) | `run(String args, boolean fromAutoload)` | Store variable with optional CFG autoload tracking |
| [fromContainerSlotSource](fromContainerSlotSource.md) | `fromContainerSlotSource(String)` | Parse cN source string to 1-based slot number |
| [isValidVarName](isValidVarName.md) | `isValidVarName(String)` | Validate variable name (must not start with digit) |
| [getValueFromSource](getValueFromSource.md) | `getValueFromSource(String)` | Resolve source string to Number |
| [getCurrentHotbarSlot](getCurrentHotbarSlot.md) | `getCurrentHotbarSlot()` | Get current hotbar slot (1-9) |
| [getItemCountFromSlot](getItemCountFromSlot.md) | `getItemCountFromSlot(String)` | Get item count from itemsOfSlotN source |
| [getPlayerPitch](getPlayerPitch.md) | `getPlayerPitch()` | Get current pitch angle |
| [getPlayerYaw](getPlayerYaw.md) | `getPlayerYaw()` | Get current yaw angle |
| [resolveValue](resolveValue.md) | `resolveValue(String)` | Resolve variable name or number to Number |
| [resolveInt](resolveInt.md) | `resolveInt(String)` | Resolve to int (convenience) |
| [resolveDouble](resolveDouble.md) | `resolveDouble(String)` | Resolve to double (convenience) |
| [isVariable](isVariable.md) | `isVariable(String)` | Check if name is a stored variable |

## See Also

| Item | Description |
|------|-------------|
| [SwapSlotAlias](../SwapSlotAlias.java/README.md) | Primary consumer of container slot variables |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/README.md) | Remove CFG-loaded variables |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/README.md) | Remove runtime variables |
| [SlotAlias](../SlotAlias.java/README.md) | Uses resolveInt for slot selection |
| [WaitAlias](../WaitAlias.java/README.md) | Uses resolveInt for tick counts |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
