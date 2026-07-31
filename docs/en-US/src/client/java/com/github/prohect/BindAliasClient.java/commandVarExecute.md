# commandVarExecute method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
private int commandVarExecute(java.lang.String, java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `varName` | `String` | The variable name to create or update |
| `source` | `String` | The value source — `hotbarSlot`, `itemsOfSlot0`-`itemsOfSlot9`, `yaw`, `pitch`, `cN`, or a literal number |

## Remarks

Creates or updates a mod variable by delegating to `VarAlias.run(varName + "\\" + source)`. After execution, checks whether the variable was successfully stored in either `GENERAL_VARIABLES` or `CONTAINER_SLOT_VARIABLES`. If successful and not in silent mode, sends a confirmation message to the player's chat. If the variable was not set in either map, reports failure.

Returns `1` on success, `0` on failure. The private 3-arg overload adds `fromAutoload` to track CFG-origin variables for `unloadCFGVars` cleanup.

## See Also

| Item | Description |
|------|-------------|
| [VarAlias](../alias/builtinAlias/VarAlias.java/VarAlias.md) | The variable storage alias |
| [silentMode](silentMode.md) | Suppresses the success/failure message when true |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
