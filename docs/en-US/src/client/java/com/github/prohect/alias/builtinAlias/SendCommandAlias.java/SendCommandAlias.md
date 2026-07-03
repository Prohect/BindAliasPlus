# SendCommandAlias (src/client/java/com/github/prohect/alias/builtinAlias/SendCommandAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SendCommandAlias extends com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs<com.github.prohect.alias.builtinAlias.SendCommandAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that sends a server command (without the `/` prefix) via `LocalPlayer.connection.sendCommand()`.

Lifecycle: singleton — instantiated once during `onInitializeClient()`, registered via `putToAliasesWithArgs()`, and reused for every invocation.

Thread safety: not thread-safe; runs on the render thread. Requires `Minecraft.getInstance().player` to be non-null.

Key collaborators: extends [BuiltinAliasWithGreedyStringArgs](../../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) which uses `';'` as its own definition divider. The entire args string is sent as the command (the `/` prefix is added by `sendCommand()`).

## See Also

| Item                                                                                                                | Description                              |
| ------------------------------------------------------------------------------------------------------------------- | ---------------------------------------- |
| [BuiltinAliasWithGreedyStringArgs](../../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) | Parent class — greedy string arg parsing |
| [SayAlias](../SayAlias.java/SayAlias.md)                                                                            | Sends chat message instead of command    |
| [LocalSayAlias](../LocalSayAlias.java/LocalSayAlias.md)                                                             | Displays client-side message             |
| [run](run.md)                                                                                                       | The `run` method that sends the command  |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
