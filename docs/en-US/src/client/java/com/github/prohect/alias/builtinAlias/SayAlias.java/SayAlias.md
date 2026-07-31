# SayAlias (src/client/java/com/github/prohect/alias/builtinAlias/SayAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.SayAlias extends com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs<com.github.prohect.alias.builtinAlias.SayAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that sends a chat message to the server via `LocalPlayer.connection.sendChat()`.

Lifecycle: singleton — instantiated once during `onInitializeClient()`, registered via `putToAliasesWithArgs()`, and reused for every invocation.

Thread safety: not thread-safe; runs on the render thread. Requires `Minecraft.getInstance().player` to be non-null.

Key collaborators: extends [BuiltinAliasWithGreedyStringArgs](../../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) which uses `';'` as its own definition divider. The entire args string becomes the chat message sent to the server.

## See Also

| Item                                                                                                                | Description                                  |
| ------------------------------------------------------------------------------------------------------------------- | -------------------------------------------- |
| [BuiltinAliasWithGreedyStringArgs](../../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) | Parent class — greedy string arg parsing     |
| [LocalSayAlias](../LocalSayAlias.java/LocalSayAlias.md)                                                             | Displays client-side message instead         |
| [SendCommandAlias](../SendCommandAlias.java/SendCommandAlias.md)                                                    | Sends commands (`/` prefix) to server        |
| [run](run.md)                                                                                                       | The `run` method that sends the chat message |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
