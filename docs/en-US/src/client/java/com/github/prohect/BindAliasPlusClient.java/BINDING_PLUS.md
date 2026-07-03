# BINDING_PLUS field (src/client/java/com/github/prohect/BindAliasPlusClient.java)

## Syntax

```java
public static final java.util.Map<com.mojang.blaze3d.platform.InputConstants$Key, com.github.prohect.KeyBindingPlus> BINDING_PLUS
```

## Remarks

Master key-binding registry: maps an `InputConstants.Key` to a `KeyBindingPlus` record.

**Writers**: `commandBindExecute`, `commandBindByAliasNameExecute`, and `loadCFG`.
**Readers**: The key-mixin tick handler (looks up pressed/released keys to dispatch aliases).
**Removers**: `commandUnbindExecute`, `UnloadCFGBindsAlias`.

Uses `HashMap` for O(1) key lookups. Not thread-safe — render thread only.

## See Also

| Item                                                       | Description                                   |
| ---------------------------------------------------------- | --------------------------------------------- |
| [KeyBindingPlus](../KeyBindingPlus.java/KeyBindingPlus.md) | The value type stored in this map             |
| [KeyPressed](../KeyPressed.java/KeyPressed.md)             | Key events that trigger lookups into this map | [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)* |
