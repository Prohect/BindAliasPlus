# run method (src/client/java/com/github/prohect/alias/builtinAlias/SayAlias.java)

Sends the string argument as a chat message to the server.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.SayAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Chat message text to send to the server (no leading `/` needed) |

## Remarks

**Algorithm:**

1. Get `mc.player`.
2. If null, return immediately.
3. Call `player.connection.sendChat(args)` to send the message to the server.

**Return value:** `this` (fluent return).

**Side effects:** Sends a chat packet to the server. The message appears in the chat for all players on the server.

**No screen suppression:** Works on any screen, including text-input screens — the message bypasses the chat GUI.

**Example:**
- `say\Hello` — sends "Hello" to chat
- `say\"Hello world"` — sends "Hello world" to chat (quoted for multi-word)

## See Also

| Item | Description |
|------|-------------|
| [SayAlias](SayAlias.md) | Class overview |
| [LocalSayAlias](../LocalSayAlias.java/run.md) | Client-side-only message |
| [SendCommandAlias](../SendCommandAlias.java/run.md) | Server command instead of chat |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
