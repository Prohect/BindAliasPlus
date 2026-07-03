# commandVarExecute method (src/client/java/com/github/prohect/BindAliasPlusClient.java)

## Syntax

```java
private int commandVarExecute(java.lang.String, java.lang.String, boolean)
```

## Parameters

| Name           | Type      | Description                                                                                        |
| -------------- | --------- | -------------------------------------------------------------------------------------------------- |
| `varName`      | `String`  | The variable name to create or update.                                                             |
| `source`       | `String`  | The value expression for the variable (e.g., a literal number, another var reference, arithmetic). |
| `fromAutoload` | `boolean` | `true` if called from `loadCFG()` (autoload tracking).                                             |

## Remarks

Creates or updates a variable via `VarAlias.run()`. The `varName` and `source`
are combined with `Alias.divider4AliasArgs` and passed to `VarAlias`.

After running, checks `VarAlias.VARIABLES` to determine success:

- If the variable exists after the call → sends success feedback (unless `silentMode`).
- If the variable does not exist → sends failure feedback.

Return value:

- `1` — variable was created/updated successfully.
- `0` — variable creation failed.

The two-arg overload delegates to this method with `fromAutoload = false`.

## See Also

| Item                                                                | Description                                            |
| ------------------------------------------------------------------- | ------------------------------------------------------ |
| [VarAlias](../alias/builtinAlias/VarAlias.java/VarAlias.md)         | The alias that performs the actual variable set/update |
| [Alias.divider4AliasArgs](../alias/Alias.java/divider4AliasArgs.md) | Delimiter used to combine varName and source           |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
