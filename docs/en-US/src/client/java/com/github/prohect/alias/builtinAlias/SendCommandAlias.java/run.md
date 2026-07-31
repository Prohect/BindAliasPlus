# run method (src/client/java/com/github/prohect/alias/builtinAlias/SendCommandAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                             |
| ------ | -------- | ------------------------------------------------------- |
| `args` | `String` | The server command to execute (without the `/` prefix). |

## Remarks

Sends a server command (e.g., `"tp ~ ~ ~"`, `"gamemode creative"`) to the connected server.

Algorithm:

1. Obtains `Minecraft.getInstance().player` and casts to `LocalPlayer`.
2. If player is null (not in a world), returns early.
3. Calls `player.connection.sendCommand(args)` to send the command to the server. The game adds the `/` prefix automatically.

Side effects: sends a command packet to the server. The server processes the command according to the player's permissions.

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"sendCommand"` matches an `AliasRecord`.

Error handling: silently returns if no player is available. Invalid commands are sent as-is; the server will respond with an error message based on its own validation.

## See Also

| Item                                              | Description              |
| ------------------------------------------------- | ------------------------ |
| [SendCommandAlias](SendCommandAlias.md)           | Owning class             |
| [SayAlias.run](../SayAlias.java/run.md)           | Sends chat to server     |
| [LocalSayAlias.run](../LocalSayAlias.java/run.md) | Adds client-side message |
| [LogAlias.run](../LogAlias.java/run.md)           | Logs to mod logger       |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
