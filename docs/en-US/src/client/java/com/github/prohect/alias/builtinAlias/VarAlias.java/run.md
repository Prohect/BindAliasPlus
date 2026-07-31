# run method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                                    |
| ------ | -------- | -------------------------------------------------------------------------------------------------------------- |
| `args` | `String` | Two tokens separated by `Alias.divider4AliasArgs`: the variable name and the source (e.g. `myVar\hotbarSlot`). |

## Remarks

Parses arguments, validates the variable name, resolves the source to a numeric value, and stores it in `VARIABLES`. This is the standard (non-autoload) entry point.

**Algorithm**:

1. Split `args` via `Alias.getDefinitionSplits()`. Requires at least 2 tokens.
2. Validate `varName` via `isValidVarName()` — rejects names starting with a digit.
3. Resolve the source via `getValueFromSource(source)`.
4. Store in `VARIABLES.put(varName, value)`.
5. Log the result at INFO level.

**Side effects**: Modifies `VARIABLES`. Does NOT modify `AUTOLOADED_VARIABLES` (use the overloaded `run(String, boolean)` for autoload tracking).

**Callers**: Invoked by the alias dispatch system when a user types `var\myVar\source` or a UserAlias definition contains a `var` command.

**Error handling**: Logs ERROR and returns early if args are missing, var name is invalid, or source resolution fails.

## See Also

| Item                                        | Description                |
| ------------------------------------------- | -------------------------- |
| [getValueFromSource](getValueFromSource.md) | Source-to-value resolution |
| [isValidVarName](isValidVarName.md)         | Variable name validation   |
| [VARIABLES](VARIABLES.md)                   | Storage map                |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
