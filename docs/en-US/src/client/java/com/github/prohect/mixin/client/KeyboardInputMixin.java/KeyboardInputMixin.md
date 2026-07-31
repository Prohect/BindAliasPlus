# KeyboardInputMixin (src/client/java/com/github/prohect/mixin/client/KeyboardInputMixin.java)

## Syntax

```java
@Mixin(KeyboardInput.class)
public class com.github.prohect.mixin.client.KeyboardInputMixin
```

## Static Initializer

_None._

## Remarks

Mixes into `net.minecraft.client.player.KeyboardInput` to inject the mod's key-event queue into the movement-input processing pipeline. Each tick, before vanilla processes physical key state, this mixin drains the `KEY_QUEUE` (`BindAliasClient.KEY_QUEUE`) and dispatches any queued key press/release events to the corresponding `AliasWithoutArgs` instances. This is the bridge between the physical key events captured by [`KeyBoardMixin`](../KeyBoardMixin.java/README.md) / [`MouseMixin`](../MouseMixin.java/README.md) and the alias execution system.

The injection is `static` and at `HEAD` of `KeyboardInput#tick()` — this ensures alias-driven movement keys (`+forward`, `+back`, `+left`, `+right`) are applied before vanilla reads the keyboard state for the frame, giving aliases deterministic control over player movement.

## See Also

| Item | Description |
|------|-------------|
| [tick](tick.md) | The `@Inject` that drains `KEY_QUEUE` and dispatches aliases |
| [KeyBoardMixin](../KeyBoardMixin.java/README.md) | Routes physical key events into `KEY_QUEUE` |
| [MouseMixin](../MouseMixin.java/README.md) | Routes physical mouse events into `KEY_QUEUE` |
| [BindAliasClient.KEY_QUEUE](../../../BindAliasClient.java/KEY_QUEUE.md) | The queue drained here |
| [BindAliasClient.BINDING_PLUS](../../../BindAliasClient.java/BINDING_PLUS.md) | The key→key-binding map used for lookup |
