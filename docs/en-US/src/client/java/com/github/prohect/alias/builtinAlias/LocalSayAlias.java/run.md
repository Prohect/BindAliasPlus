# run method (src/client/java/com/github/prohect/alias/builtinAlias/LocalSayAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                |
| ------ | -------- | ---------------------------------------------------------- |
| `args` | `String` | The full message text to display in the client's chat HUD. |

## Remarks

Adds a client-side-only message to the local chat HUD.

Algorithm:

1. Checks if `Minecraft.getInstance().player` is null; if so, returns early (no player connected).
2. Obtains the chat component from `Minecraft.getInstance().gui.hud.getChat()`.
3. Calls `addClientSystemMessage(Component.literal(args))` to display the message.

Side effects: displays a system message in the player's chat HUD. The message is local only and never sent to the server.

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"localSay"` matches an `AliasRecord`.

Error handling: silently returns if no player is available (not in a world).

## See Also

| Item                                                    | Description              |
| ------------------------------------------------------- | ------------------------ |
| [LocalSayAlias](LocalSayAlias.md)                       | Owning class             |
| [SayAlias.run](../SayAlias.java/run.md)                 | Sends chat to server     |
| [SendCommandAlias.run](../SendCommandAlias.java/run.md) | Sends commands to server |
| [LogAlias.run](../LogAlias.java/run.md)                 | Logs to mod logger       |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
