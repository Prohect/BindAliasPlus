# LocalSayAlias (src/client/java/com/github/prohect/alias/builtinAlias/LocalSayAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.LocalSayAlias extends com.github.prohect.alias.BuiltinAliasWithGreedyStringArgs<com.github.prohect.alias.builtinAlias.LocalSayAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias that displays a message in the client's own chat HUD (client-side only, not sent to server).

Lifecycle: singleton — instantiated once during `onInitializeClient()`, registered via `putToAliasesWithArgs()`, and reused for every invocation.

Thread safety: not thread-safe; runs on the render thread. Requires `Minecraft.getInstance().player` to be non-null.

Key collaborators: extends [BuiltinAliasWithGreedyStringArgs](../../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) which uses `';'` as its own definition divider to avoid interfering with alias definition syntax. The entire args string becomes the message content.

Messages appear in the chat HUD as system messages via `gui.hud.getChat().addClientSystemMessage()`. They are visible only to the local player.

## See Also

| Item                                                                                                                | Description                                          |
| ------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------- |
| [BuiltinAliasWithGreedyStringArgs](../../BuiltinAliasWithGreedyStringArgs.java/BuiltinAliasWithGreedyStringArgs.md) | Parent class — greedy string arg parsing             |
| [SayAlias](../SayAlias.java/SayAlias.md)                                                                            | Sends chat to server instead of client               |
| [SendCommandAlias](../SendCommandAlias.java/SendCommandAlias.md)                                                    | Sends commands (`/` prefix) to server                |
| [LogAlias](../LogAlias.java/LogAlias.md)                                                                            | Logs to the mod logger instead of chat               |
| [run](run.md)                                                                                                       | The `run` method that adds the client system message |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
