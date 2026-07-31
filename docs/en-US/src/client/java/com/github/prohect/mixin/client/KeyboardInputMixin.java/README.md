# KeyboardInputMixin

Mixin targeting `net.minecraft.client.player.KeyboardInput`. Drains the mod's `KEY_QUEUE` each tick and dispatches queued key events to the corresponding `AliasWithoutArgs` instances, bridging physical input to alias execution.

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [tick](tick.md) | `static void tick(CallbackInfo info)` | `@Inject` at `HEAD` of `KeyboardInput#tick()` — drains `KEY_QUEUE` and dispatches aliasWithoutArgs |

## See Also

| Item | Description |
|------|-------------|
| [KeyBoardMixin](../KeyBoardMixin.java/README.md) | Enqueues keyboard events into `KEY_QUEUE` |
| [MouseMixin](../MouseMixin.java/README.md) | Enqueues mouse events into `KEY_QUEUE` |
| [AliasWithoutArgs](../../../alias/AliasWithoutArgs.java/README.md) | The alias type dispatched here |
