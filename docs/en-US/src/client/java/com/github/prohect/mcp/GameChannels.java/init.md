# init method (src/client/java/com/github/prohect/mcp/GameChannels.java)

## Syntax

```java
public static void init()
```

## Remarks

Registers a Log4j appender on the `"bind-alias"` logger to feed the [`MOD`](MOD.md) channel with the mod's own log output. Safe to call multiple times (internally deduplicated via a `synchronized` block and `initialized` flag).

The appender captures only messages from the `"bind-alias"` logger, skipping all Fabric/mixin/rendering noise from the root logger. The child `LoggerConfig` is built with `additive = true` so normal log output (console, `latest.log`) is preserved — the appender adds to, rather than replaces, the existing logging pipeline. On failure (e.g., Log4j internals change), the exception is silently swallowed — log capture is best-effort.

## See Also

| Item | Description |
|------|-------------|
| [MOD](MOD.md) | The channel this appender feeds |
| [post](post.md) | How log messages are posted to the channel |
