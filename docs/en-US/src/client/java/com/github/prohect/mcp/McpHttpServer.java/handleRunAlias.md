# handleRunAlias method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static void handleRunAlias(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `HttpExchange` | The HTTP exchange for `POST /runAlias`. |

## Remarks

`POST /runAlias?def=...` — executes a chain of aliases on the game thread and returns the result.

**Query parameters**:
| Param | Description |
|-------|-------------|
| `def` | Alias definition string. Supports space-separated chained aliases with `\` as argument separator (the standard alias format). |
| `name` + `args` | _(legacy)_ Alternative syntax. If `def` is missing, constructs `def = name + divider + args`. |

**Execution**: Creates a `UserAlias` from the definition and calls `run("")` on the main thread via `onMainThread()`. The `UserAlias` class handles the full alias chain parsing and dispatch.

Returns `{"tick": <N>}` where `tick` is the ticks-since-world-join at the moment the alias was executed (-1 if not in a world). On error (timeout, exception), returns `{"error": "..."}` with status 500.

## See Also

| Item | Description |
|------|-------------|
| [UserAlias.run](../../alias/UserAlias.java/run.md) | The alias dispatch engine |
| [onMainThread](onMainThread.md) | Thread bridge |
| [handleDefineAlias](handleDefineAlias.md) | Defines aliases via the command pipeline |

*Documented for Commit: [719c5aa51ed1bed2dcd073d67f38a68c1e097f0c](https://github.com/Prohect/BindAlias/tree/719c5aa51ed1bed2dcd073d67f38a68c1e097f0c)*
