# begin method (src/client/java/com/github/prohect/mcp/ChatCapture.java)

## Syntax

```java
public static void begin()
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| _(none)_ | | |

## Remarks

Starts a command-feedback capture window. Clears the message buffer and sets `active = true`. Subsequent calls to `onSystemMessage()` from the mixin will accumulate text until `end()` is called.

Called by `McpHttpServer.handleRunAlias()` before dispatching a command to the game thread. Not idempotent — calling `begin()` while already active will clear any previously captured messages.

## See Also

| Item | Description |
|------|-------------|
| [end](end.md) | Stops capture and returns collected text |
| [onSystemMessage](onSystemMessage.md) | Appends messages during active capture |
| [McpHttpServer.handleRunAlias](../McpHttpServer.java/handleRunAlias.md) | Caller |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
