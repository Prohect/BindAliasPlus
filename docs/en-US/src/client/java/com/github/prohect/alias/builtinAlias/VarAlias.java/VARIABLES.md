# VARIABLES field (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
public static final java.util.Map<java.lang.String, java.lang.Number> VARIABLES
```

## Remarks

The global variable storage map. Keys are variable names (strings), values are `Number` instances — `Integer` for integral values (hotbar slot, item count, literal integers) and `Double` for floating-point values (pitch, yaw, literal decimals).

Populated by `run()` and `run(String, boolean)`. Read by `resolveValue()`, `resolveInt()`, `resolveDouble()`, and `isVariable()`. Entries are removed by `UnloadCFGVarsAlias.run()` for autoloaded variables only.

Declared `final` (the reference is immutable) but the map contents are mutable. Not thread-safe.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
