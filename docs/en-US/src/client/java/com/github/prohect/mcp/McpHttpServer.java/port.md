# port method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
public static int port()
```

## Return value

The actual port the server is bound to, or `-1` if the server failed to start.

## Remarks

Returns the port assigned during `start()`. May differ from the configured default if the initial port was occupied and a fallback port was used.

## See Also

| Item | Description |
|------|-------------|
| [start](start.md) | Where the port is assigned |
