# run method (src/client/java/com/github/prohect/alias/builtinAlias/SayAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                             |
| ------ | -------- | --------------------------------------- |
| `args` | `String` | The chat message to send to the server. |

## Remarks

Sends a chat message to the connected server via the player's network connection.

Algorithm:

1. Obtains `Minecraft.getInstance().player` and casts to `LocalPlayer`.
2. If player is null (not in a world), returns early.
3. Calls `player.connection.sendChat(args)` to send the message to the server.

Side effects: sends a network packet to the server containing the chat message. The server broadcasts it to other players per server rules.

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"say"` matches an `AliasRecord`.

Error handling: silently returns if no player is available (not in a world). Invalid or empty messages are still sent (server validation may reject).

## See Also

| Item                                                    | Description              |
| ------------------------------------------------------- | ------------------------ |
| [SayAlias](SayAlias.md)                                 | Owning class             |
| [LocalSayAlias.run](../LocalSayAlias.java/run.md)       | Adds client-side message |
| [SendCommandAlias.run](../SendCommandAlias.java/run.md) | Sends commands to server |
| [LogAlias.run](../LogAlias.java/run.md)                 | Logs to mod logger       |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
