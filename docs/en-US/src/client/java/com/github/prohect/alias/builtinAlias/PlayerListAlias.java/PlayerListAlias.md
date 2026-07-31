# PlayerListAlias (src/client/java/com/github/prohect/alias/builtinAlias/PlayerListAlias.java)

Agent-tooling alias that shows the online-player list overlay by pressing the Tab key.

## Syntax

```java
public class PlayerListAlias extends BuiltinAliasWithBooleanArgs<PlayerListAlias>
```

## Static Initializer

_None._

## Remarks

Usage: `+playerList` holds Tab (shows overlay), `-playerList` releases it (hides overlay).

Intended for an external agent workflow: hold `+playerList`, wait a few ticks for the overlay to render, then capture a screenshot to identify online players.

Uses `KeyMapping.setDown()` directly — no GLFW key event needed. Increments `clickCount` on press to match vanilla behavior.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | Entry point for alias execution |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAlias/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
