# KeyBoardMixin

Mixin targeting `net.minecraft.client.KeyboardHandler`. Intercepts physical keyboard press/release events, filters by window, lock state, and binding registration, then enqueues `KeyPressed` records into `KEY_QUEUE`.

## Fields

| Name | Type | Description |
|------|------|-------------|

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [onKey](onKey.md) | `void onKey(long window, int action, KeyEvent event, CallbackInfo ci)` | `@Inject` at `HEAD` of `keyPress` — enqueues valid key press/release events into `KEY_QUEUE` |

## See Also

| Item | Description |
|------|-------------|
| [KeyboardInputMixin](../KeyboardInputMixin.java/README.md) | The mixin that drains `KEY_QUEUE` and dispatches aliases |
| [MouseMixin](../MouseMixin.java/README.md) | Analogous mixin for mouse button events |
| [LockAlias](../../../alias/builtinAlias/LockAlias.java/README.md) | Provides the `LOCKED_PHYSICAL_KEYS` set that gates key events |
