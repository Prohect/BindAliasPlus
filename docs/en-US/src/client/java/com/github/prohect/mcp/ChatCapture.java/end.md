# end method (src/client/java/com/github/prohect/mcp/ChatCapture.java)

## Syntax

```java
public static String end()
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| _(none)_ | | |

## Remarks

Stops capture, drains the buffer, and returns all captured messages joined with newlines. Sets `active = false` so future `onSystemMessage()` calls are ignored.

The drain is performed under `synchronized(buffer)` — a snapshot is taken, the buffer is cleared, and the snapshot is joined into a single string. This prevents a race where new messages arrive between copying the array and clearing.

Returns an empty string (`""`) if no messages were captured during the window.

Called by `McpHttpServer.handleRunAlias()` after the command completes, to include feedback in the HTTP response.

## See Also

| Item | Description |
|------|-------------|
| [begin](begin.md) | Starts capture |
| [McpHttpServer.handleRunAlias](../McpHttpServer.java/handleRunAlias.md) | Caller |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
