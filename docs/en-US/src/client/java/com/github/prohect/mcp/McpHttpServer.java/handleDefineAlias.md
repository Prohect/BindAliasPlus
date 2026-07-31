# handleDefineAlias method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static void handleDefineAlias(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | The HTTP exchange; reads `name` and `def` from the query string |

## Remarks

`POST /defineAlias?name=<name>&def=<definition>` handler. On the main thread:

1. Extracts `name` and `def` query parameters.
2. Validates the alias name: must be a single word (no spaces, `\`, `;`, or `/`), cannot be empty. Skips names already claimed by builtin aliases like `+attack`, `slot`, etc.
3. Defines the alias via the mod's alias system.
4. Returns `StateTracker.begin(false)` + `StateTracker.finish(begun)` with the state diff envelope.

Errors (missing parameters, invalid name) return an envelope with an `"error"` member.

## See Also

| Item | Description |
|------|-------------|
| [StateTracker](StateTracker.java/README.md) | State envelope production |
