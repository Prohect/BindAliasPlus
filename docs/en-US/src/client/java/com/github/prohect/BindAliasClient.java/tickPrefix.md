# tickPrefix method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static java.lang.String tickPrefix()
```

## Parameters

_None._

## Remarks

Returns a bracketed tick-since-join prefix string for consistent log formatting. If the player has never joined (`joinTick < 0`), returns `[client_tick:-1]`. Otherwise returns `[client_tick:N]` where `N = currentTick - joinTick`. Used throughout the mod when logging to provide temporal context in server logs and the mod log file.

## See Also

| Item | Description |
|------|-------------|
| [currentTick](currentTick.md) | Monotonically increasing tick counter |
| [joinTick](joinTick.md) | Tick when the player last joined a world |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
