# KeyPressed (src/client/java/com/github/prohect/KeyPressed.java)

## Syntax

```java
public final class com.github.prohect.KeyPressed extends java.lang.Record
```

## Static Initializer

_None._

## Remarks

A Java `record` representing a single key or mouse button event — immutable data pushed into `BindAliasClient.KEY_QUEUE` by mixins (`KeyBoardMixin`, `MouseMixin`) and consumed each tick by `MinecraftClientMixin`.

Components:
- `key` — the `InputUtil.Key` identifying which keyboard key or mouse button was involved.
- `pressed` — `true` for key-down / button-down, `false` for key-up / button-up.

This is a pure data carrier. No logic lives here — the tick loop in `MinecraftClientMixin` looks up the key in `BINDING_PLUS` and invokes the appropriate alias.

## See Also

| Item | Description |
|------|-------------|
| [KEY_QUEUE](../BindAliasClient.java/KEY_QUEUE.md) | The FIFO queue where these events are stored |
| [BINDING_PLUS](../BindAliasClient.java/BINDING_PLUS.md) | The key→alias map used to dispatch events |
| [BindAliasKeyBinding](../BindAliasKeyBinding.java/BindAliasKeyBinding.md) | The alias binding triggered by key events |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

