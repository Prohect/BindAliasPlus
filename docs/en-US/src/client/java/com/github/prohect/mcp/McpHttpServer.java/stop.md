# stop method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
public static void stop()
```

## Remarks

Stops the HTTP server with a 2-second grace period. Cancels all pending nap tasks by setting their `cancelled` flag to `true`. Called during mod shutdown.

## See Also

| Item | Description |
|------|-------------|
| [start](start.md) | Server startup |
