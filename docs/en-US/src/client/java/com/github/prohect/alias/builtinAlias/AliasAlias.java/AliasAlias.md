# AliasAlias (src/client/java/com/github/prohect/alias/builtinAlias/AliasAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.AliasAlias extends com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs<com.github.prohect.alias.builtinAlias.AliasAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that sends an `/alias` command to the server. Registered as `alias`.

**Purpose**: Allows programmatic alias creation from within alias definitions. Constructs a command string `alias\definition` and sends it via `player.connection.sendCommand()`, which the server-side command handler processes to create a new UserAlias.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only). Sends a server-bound command packet.

## See Also

| Item                                                                                                                | Description                        |
| ------------------------------------------------------------------------------------------------------------------- | ---------------------------------- |
| [BindAlias](../BindAlias.java/BindAlias.md)                                                                         | Sends `/bind` commands similarly   |
| [UnbindAlias](../UnbindAlias.java/UnbindAlias.md)                                                                   | Sends `/unbind` commands similarly |
| [BuiltinAliasWithGreedyStringArgs](../../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) | Parent class                       |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
