# run method (src/client/java/com/github/prohect/alias/builtinAlias/PickItemAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description |
| ------ | -------- | ----------- |
| `args` | `String` | Ignored.    |

## Remarks

Triggers vanilla pick-block by firing the `keyPickItem` keybinding.

**Algorithm**:

1. Guard against null player.
2. Get `options.keyPickItem` from Minecraft.
3. Call `pickKey.setDown(true)` and `pickKey.clickCount++`.

This increments `clickCount` so that vanilla's key polling processes a pick-block action in the next tick, calling `pickBlockOrEntity()`.

**Side effects**: Modifies vanilla `KeyMapping` state, which triggers pick-block behavior.

**Callers**: Invoked by the alias dispatch system.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
