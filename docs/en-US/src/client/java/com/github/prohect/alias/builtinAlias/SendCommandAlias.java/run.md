# run method (src/client/java/com/github/prohect/alias/builtinAlias/SendCommandAlias.java)

Sends the string argument as a server command (no leading `/` needed).

## Syntax

```java
public com.github.prohect.alias.builtinAlias.SendCommandAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Command to send to the server (e.g., `"tp @p 0 70 0"`, NO leading `/`) |

## Remarks

**Algorithm:**

1. Get `mc.player`.
2. If null, return immediately.
3. Call `player.connection.sendCommand(args)` to send the command to the server.

**Return value:** `this` (fluent return).

**Side effects:** Sends a command packet to the server. The server processes and responds to the command.

**No screen suppression:** Works on any screen.

**Examples:**
- `sendCommand\tp @p 0 70 0`
- `sendCommand\"give @s diamond 64"`
- `sendCommand\time set day`

## See Also

| Item | Description |
|------|-------------|
| [SendCommandAlias](SendCommandAlias.md) | Class overview |
| [SayAlias](../SayAlias.java/run.md) | Send chat message instead of command |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
