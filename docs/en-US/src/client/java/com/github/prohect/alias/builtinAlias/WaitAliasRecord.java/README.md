# WaitAliasRecord

## Fields

| Name                      | Type           | Description                                                                                 |
| ------------------------- | -------------- | ------------------------------------------------------------------------------------------- |
| `ticks`                   | `int`          | Remaining tick count; decremented each game tick                                            |
| `definition`              | `final String` | The alias definition string to execute when the timer expires                               |
| `reapplyToGameKeyMapping` | `boolean`      | If true, calls `reapplyToGameKeyMapping()` on the alias instead of executing the definition |

## Methods

| Name            | Signature           | Description                                                                |
| --------------- | ------------------- | -------------------------------------------------------------------------- |
| [tick](tick.md) | `public int tick()` | Decrements the timer and executes the deferred action when it reaches zero |

## See Also

| Item                                     | Description                      |
| ---------------------------------------- | -------------------------------- |
| [WaitAlias](../WaitAlias.java/README.md) | Creates instances of this record |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
