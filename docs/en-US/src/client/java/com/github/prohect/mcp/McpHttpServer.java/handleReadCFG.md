# handleReadCFG method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static void handleReadCFG(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `HttpExchange` | The HTTP exchange for `GET /readCFG`. |

## Remarks

`GET /readCFG` — reads the raw config file from disk and returns its content as a JSON string. The file path is `BindAliasClient.cfgPath` (typically `<minecraft>/config/bind-alias.cfg`).

Returns `{"content": "<file contents>"}` on success. On `IOException`, returns `{"error": "failed to read: ..."}` with status 500.

Does not require main-thread access — file I/O is safe from any thread.

## See Also

| Item | Description |
|------|-------------|
| [handleWriteCFG](handleWriteCFG.md) | Writes config and reloads |
| [BindAliasClient.loadCFG](../../BindAliasClient.java/loadCFG.md) | Reloads config after write |

*Documented for Commit: [719c5aa51ed1bed2dcd073d67f38a68c1e097f0c](https://github.com/Prohect/BindAlias/tree/719c5aa51ed1bed2dcd073d67f38a68c1e097f0c)*
