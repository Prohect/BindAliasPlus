# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGVarsAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description |
| ------ | -------- | ----------- |
| `args` | `String` | Ignored.    |

## Remarks

Removes all autoloaded variables from `VarAlias.VARIABLES` and `VarAlias.AUTOLOADED_VARIABLES`.

**Algorithm**:

1. Collect all names from `VarAlias.AUTOLOADED_VARIABLES` into a snapshot list.
2. For each name, remove from both `VarAlias.VARIABLES` and `VarAlias.AUTOLOADED_VARIABLES`.
3. Log the removal count unless `BindAliasPlusClient.silentMode` is active.

**Side effects**: Removes entries from the two static collections in `VarAlias`.

**Callers**: Invoked by the alias dispatch system and by `UnloadCFGAllAlias.run()`.

## See Also

| Item                                                                      | Description      |
| ------------------------------------------------------------------------- | ---------------- |
| [VarAlias.VARIABLES](../VarAlias.java/VARIABLES.md)                       | The storage map  |
| [VarAlias.AUTOLOADED_VARIABLES](../VarAlias.java/AUTOLOADED_VARIABLES.md) | The tracking set |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
