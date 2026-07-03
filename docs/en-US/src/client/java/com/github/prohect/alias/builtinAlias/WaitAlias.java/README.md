# WaitAlias

## Fields

| Name           | Type                                             | Description                                |
| -------------- | ------------------------------------------------ | ------------------------------------------ |
| `tasksWaiting` | `public static final ArrayList<WaitAliasRecord>` | Global list of pending deferred executions |

## Methods

| Name                  | Signature                                              | Description                                                                        |
| --------------------- | ------------------------------------------------------ | ---------------------------------------------------------------------------------- |
| [run](run.md)         | `public WaitAlias run(String args)`                    | Deprecated — creates a simple wait record with no deferred definition              |
| `run(String, String)` | `public WaitAlias run(String args, String definition)` | Creates a wait record that will execute the given alias definition after the delay |

## See Also

| Item                                                 | Description               |
| ---------------------------------------------------- | ------------------------- |
| [WaitAliasRecord](../WaitAliasRecord.java/README.md) | Deferred execution record |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
