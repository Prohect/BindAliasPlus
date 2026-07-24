# handleWriteCFG method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static void handleWriteCFG(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `HttpExchange` | The HTTP exchange for `POST /writeCFG`. |

## Remarks

`POST /writeCFG` — overwrites the config file on disk and triggers a reload of all aliases, binds, and variables.

**Input**: Accepts content via query parameter (`?content=...`) or JSON body (`{"content": "..."}`). The JSON body path handles escaped characters (`\n`, `\r`, `\t`, `\\`, `\"`). Returns 400 if no content is found.

**Pipeline**:
1. Writes the content to `BindAliasPlusClient.cfgPath` via `Files.writeString()`.
2. Calls `BindAliasPlusClient.INSTANCE.loadCFG()` on the main thread via `onMainThread()` to reload all aliases, binds, and variables from the updated file.

Returns `{"ok": true}` on success. Returns `{"error": "..."}` with status 500 on I/O or reload failure.

**Security**: No authentication. The server binds to `127.0.0.1` only, so only local processes can reach it.

## See Also

| Item | Description |
|------|-------------|
| [handleReadCFG](handleReadCFG.md) | Reads config |
| [BindAliasPlusClient.loadCFG](../../BindAliasPlusClient.java/loadCFG.md) | Called after write to reload |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
