# LOGGER field (src/main/java/com/github/prohect/BindAlias.java)

## Syntax

```java
public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MOD_ID)
```

## Remarks

The mod's SLF4J logger, named `"bind-alias"`. Used to write messages to the Minecraft console and the log file. By convention, the mod ID is used as the logger name so output is clearly attributable. This logger's output is also captured by the `GameChannels` Log4j appender (registered on the same logger name) and fed into the MCP `mod` channel.

## See Also

| Item | Description |
|------|-------------|
| [MOD_ID](MOD_ID.md) | The logger's name |
| [GameChannels.init](../../client/java/com/github/prohect/mcp/GameChannels.java/init.md) | Registers the Log4j appender on this logger |
| [GameChannels.MOD](../../client/java/com/github/prohect/mcp/GameChannels.java/MOD.md) | The MCP channel receiving this logger's output |
