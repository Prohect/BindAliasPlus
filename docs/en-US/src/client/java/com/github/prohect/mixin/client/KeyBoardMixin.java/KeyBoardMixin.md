# KeyBoardMixin (src/client/java/com/github/prohect/mixin/client/KeyBoardMixin.java)

## Syntax

```java
@Mixin(KeyboardHandler.class)
public class com.github.prohect.mixin.client.KeyBoardMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `net.minecraft.client.KeyboardHandler` to intercept physical key press/release events. The `@Inject` at `HEAD` of `keyPress` routes every key-down (action=1) and key-up (action=0) event into the mod's `KEY_QUEUE` (`BindAliasClient.KEY_QUEUE`), but only when:

1. The window handle matches the Minecraft window (ignoring events for other OS windows),
2. The key code maps to a registered `BindAliasKeyBinding` in `BINDING_PLUS`,
3. The key is **not** currently locked via `LockAlias.LOCKED_PHYSICAL_KEYS`.

Key-repeat events (action=2) are explicitly ignored — only discrete press/release transitions are captured. This mixin is the sole entry point for physical keyboard input into the alias system.

## See Also

| Item | Description |
|------|-------------|
| [onKey](onKey.md) | The `@Inject` that routes key events to `KEY_QUEUE` |
| [KeyboardInputMixin](../KeyboardInputMixin.java/README.md) | The mixin that drains `KEY_QUEUE` and dispatches aliases |
| [LockAlias.LOCKED_PHYSICAL_KEYS](../../../alias/builtinAlias/LockAlias.java/LOCKED_PHYSICAL_KEYS.md) | The set of locked physical keys gated here |
| [MouseMixin](../MouseMixin.java/README.md) | The analogous mixin for mouse button events |
