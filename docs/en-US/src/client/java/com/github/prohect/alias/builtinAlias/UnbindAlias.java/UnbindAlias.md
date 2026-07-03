# UnbindAlias (src/client/java/com/github/prohect/alias/builtinAlias/UnbindAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.UnbindAlias extends com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs<com.github.prohect.alias.builtinAlias.UnbindAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that sends an `/unbind` command to the server. Registered as `unbind`.

**Purpose**: Allows programmatic keybinding removal from within alias definitions. Constructs a command string `unbind\definition` and sends it via `player.connection.sendCommand()`.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only).

## See Also

| Item                                                                                                                | Description          |
| ------------------------------------------------------------------------------------------------------------------- | -------------------- |
| [BindAlias](../BindAlias.java/BindAlias.md)                                                                         | `/bind` counterpart  |
| [AliasAlias](../AliasAlias.java/AliasAlias.md)                                                                      | `/alias` counterpart |
| [BuiltinAliasWithGreedyStringArgs](../../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) | Parent class         |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
