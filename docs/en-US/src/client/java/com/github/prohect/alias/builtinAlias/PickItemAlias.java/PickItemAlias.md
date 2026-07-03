# PickItemAlias (src/client/java/com/github/prohect/alias/builtinAlias/PickItemAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.PickItemAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.PickItemAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to trigger vanilla pick-block behavior. Registered as `pickItem`.

**Purpose**: Fires the `keyPickItem` keybinding, which causes Minecraft to call `pickBlockOrEntity()` in the next polling cycle. This picks the block/entity the player is looking at and selects the corresponding item in the hotbar (creative mode) or swaps it in (survival).

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithoutArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only).

## See Also

| Item                                                                                     | Description  |
| ---------------------------------------------------------------------------------------- | ------------ |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | Parent class |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
