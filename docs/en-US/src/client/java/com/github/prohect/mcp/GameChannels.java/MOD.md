# MOD field (src/client/java/com/github/prohect/mcp/GameChannels.java)

## Syntax

```java
public static final String MOD = "mod"
```

## Remarks

Channel name constant for the mod's own log output (alias feedback, errors, `log\` alias messages). Fed by a Log4j appender registered on the `"bind-alias"` logger via [`init()`](init.md). The appender captures only messages from the mod's logger, skipping Fabric/mixin/rendering noise from the root logger. Non-coalescing: each log message is a separate entry.

## See Also

| Item | Description |
|------|-------------|
| [init](init.md) | Registers the Log4j appender that feeds this channel |
| [CHAT](CHAT.md) | The chat channel |
| [SOUND](SOUND.md) | The sound-event channel |
| [RECIPE](RECIPE.md) | The recipe-unlock channel |
