# DropAlias

## Fields

| Name        | Type           | Description                                              |
| ----------- | -------------- | -------------------------------------------------------- |
| `ticksHeld` | `private long` | Ticks elapsed since the last press while the key is held |

## Methods

| Name                                                  | Signature                               | Description                                                                         |
| ----------------------------------------------------- | --------------------------------------- | ----------------------------------------------------------------------------------- |
| [run](run.md)                                         | `public DropAlias run(String args)`     | Starts or stops dropping based on the boolean flag                                  |
| [tickDrop](tickDrop.md)                               | `public void tickDrop()`                | Called every client tick while held; drives continuous dropping after initial delay |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | `public void reapplyToGameKeyMapping()` | Re-asserts `keyDrop.setDown(true)` after cursor re-lock                             |

## See Also

| Item                                                                            | Description  |
| ------------------------------------------------------------------------------- | ------------ |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/README.md) | Parent class |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
