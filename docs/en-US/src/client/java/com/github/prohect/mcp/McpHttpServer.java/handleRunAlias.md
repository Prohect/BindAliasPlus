# handleRunAlias method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
static void handleRunAlias(HttpExchange exchange) throws IOException
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | The HTTP exchange; reads `def` and optional `nap` from the query string |

## Remarks

`POST /runAlias?def=<alias_chain>&nap=<client_ticks>` handler. On the main thread:

1. Extracts the `def` (alias chain definition) and optional `nap` query parameters.
2. Calls `StateTracker.begin(false)` to snapshot the state **before** alias execution.
3. Parses the alias chain definition and executes each alias in sequence via the alias system.
4. If `nap` is provided and > 0:
   - Creates a `NapTask` with `ticksLeft = nap` and a `CompletableFuture<String>` pre-populated with the beginning envelope string.
   - Adds the task to `NAP_TASKS`.
   - The HTTP response is deferred — the `CompletableFuture` will be completed by `tickNapTasks()` after the specified client-tick delay, at which point `StateTracker.finish()` will capture the post-nap state.
5. If no `nap` (or `nap` = 0):
   - Immediately calls `StateTracker.finish(begun)` and returns the envelope.

The `nap` parameter is clamped to `MAX_NAP_TICKS` (600, ~30 seconds) to prevent excessively long deferred responses.

## See Also

| Item | Description |
|------|-------------|
| [StateTracker.begin](StateTracker.java/begin.md) | Pre-execution state snapshot |
| [StateTracker.finish](StateTracker.java/finish.md) | Post-execution state capture |
| [tickNapTasks](tickNapTasks.md) | Counts down nap tasks |
| [Alias.run](../../alias/Alias.java/run.md) | The alias execution system |
