# VarAlias

## Fields

| Name                   | Type                                      | Description                                                                      |
| ---------------------- | ----------------------------------------- | -------------------------------------------------------------------------------- |
| `VARIABLES`            | `public static final Map<String, Number>` | Global variable storage — maps variable names to their numeric values            |
| `AUTOLOADED_VARIABLES` | `public static final Set<String>`         | Names of variables loaded from the config file (tracked for selective unloading) |

## Methods

| Name                                            | Signature                                                | Description                                                               |
| ----------------------------------------------- | -------------------------------------------------------- | ------------------------------------------------------------------------- |
| [run](run.md)                                   | `public VarAlias run(String args)`                       | Stores a value from a source into a named variable                        |
| `run(String, boolean)`                          | `public VarAlias run(String args, boolean fromAutoload)` | Stores a value with autoload tracking                                     |
| [resolveValue](resolveValue.md)                 | `public static Number resolveValue(String input)`        | Resolves a string to a Number — tries literal parse, then variable lookup |
| [resolveInt](resolveInt.md)                     | `public static Integer resolveInt(String input)`         | Convenience — returns `intValue()` of resolved Number, or null            |
| [resolveDouble](resolveDouble.md)               | `public static Double resolveDouble(String input)`       | Convenience — returns `doubleValue()` of resolved Number, or null         |
| [isVariable](isVariable.md)                     | `public static boolean isVariable(String input)`         | Checks whether a string names an existing variable                        |
| [isValidVarName](isValidVarName.md)             | `private boolean isValidVarName(String)`                 | Validates that a variable name does not start with a digit                |
| [getValueFromSource](getValueFromSource.md)     | `private Number getValueFromSource(String)`              | Dispatches source string to the appropriate getter                        |
| [getCurrentHotbarSlot](getCurrentHotbarSlot.md) | `private Integer getCurrentHotbarSlot()`                 | Returns the player's current hotbar slot (1-9)                            |
| [getItemCountFromSlot](getItemCountFromSlot.md) | `private Integer getItemCountFromSlot(String)`           | Returns item count from an `itemsOfSlotN` pattern                         |
| [getPlayerPitch](getPlayerPitch.md)             | `private Double getPlayerPitch()`                        | Returns the player's current pitch angle                                  |
| [getPlayerYaw](getPlayerYaw.md)                 | `private Double getPlayerYaw()`                          | Returns the player's current yaw angle                                    |

## See Also

| Item                                                       | Description                  |
| ---------------------------------------------------------- | ---------------------------- |
| [SwapSlotAlias](../SwapSlotAlias.java/README.md)           | Uses `resolveInt()`          |
| [SlotAlias](../SlotAlias.java/README.md)                   | Uses `resolveInt()`          |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/README.md) | Removes autoloaded variables |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
