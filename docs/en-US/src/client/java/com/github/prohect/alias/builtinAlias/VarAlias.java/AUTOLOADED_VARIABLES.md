# AUTOLOADED_VARIABLES field (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
public static final java.util.Set<java.lang.String> AUTOLOADED_VARIABLES
```

## Remarks

Tracks which variables were loaded from the config file during `loadCFG()`. Variables created via the overloaded `run(String, boolean fromAutoload)` with `fromAutoload = true` are added to this set; variables created with `fromAutoload = false` (or via the standard `run(String)`) are removed from it if they were previously autoloaded.

Used by `UnloadCFGVarsAlias` and `UnloadCFGAllAlias` to selectively remove only config-loaded variables, leaving runtime-created variables intact.

Declared `final` (the reference is immutable) but the set contents are mutable. Not thread-safe.

## See Also

| Item                                                                   | Description                                      |
| ---------------------------------------------------------------------- | ------------------------------------------------ |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md) | Removes autoloaded variables                     |
| [VARIABLES](VARIABLES.md)                                              | The variable storage this set tracks a subset of |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
