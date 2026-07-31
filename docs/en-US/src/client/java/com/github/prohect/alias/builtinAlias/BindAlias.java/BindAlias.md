# BindAlias (src/client/java/com/github/prohect/alias/builtinAlias/BindAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.BindAlias extends com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs<com.github.prohect.alias.builtinAlias.BindAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that sends a `/bind` command to the server. Registered as `bind`.

**Purpose**: Allows programmatic keybinding from within alias definitions. Constructs a command string `bind\definition` and sends it via `player.connection.sendCommand()`.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only).

## See Also

| Item                                                                                                                | Description           |
| ------------------------------------------------------------------------------------------------------------------- | --------------------- |
| [AliasAlias](../AliasAlias.java/AliasAlias.md)                                                                      | `/alias` counterpart  |
| [UnbindAlias](../UnbindAlias.java/UnbindAlias.md)                                                                   | `/unbind` counterpart |
| [BuiltinAliasWithGreedyStringArgs](../../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) | Parent class          |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
