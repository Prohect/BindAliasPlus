# tick method (src/client/java/com/github/prohect/mixin/client/KeyboardInputMixin.java)

## Syntax

```java
@Inject(at = @At("HEAD"), method = "tick")
private static void tick(CallbackInfo info)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `info` | `CallbackInfo` | Unused callback |

## Remarks

Injected at `HEAD` of `KeyboardInput#tick()`. Drains the `BindAliasClient.KEY_QUEUE` by polling in a `while` loop. For each `KeyPressed` record dequeued:

1. Looks up the corresponding `BindAliasKeyBinding` in `BindAliasClient.BINDING_PLUS` by the key code.
2. Resolves the alias name: if `pressed`, uses `keyBindingPlus.aliasNameOnKeyPressed()`; otherwise uses `keyBindingPlus.aliasNameOnKeyReleased()`.
3. Looks up the `AliasWithoutArgs` instance in `Alias.aliasesWithoutArgs`, falling back to `Alias.aliasesWithoutArgs_fromBindCommand`.
4. If found, calls `aliasWithoutArgs.run("")`.

This is a `static` method — the mixin's `@Inject` targets the `static` `KeyboardInput#tick()` method. Only `AliasWithoutArgs` aliases are expected in this queue; the `args` are always an empty string.

## See Also

| Item | Description |
|------|-------------|
| [KeyBoardMixin.onKey](../KeyBoardMixin.java/onKey.md) | Enqueues key press/release events |
| [MouseMixin.onMouseButton](../MouseMixin.java/onMouseButton.md) | Enqueues mouse button events |
| [BindAliasClient.KEY_QUEUE](../../../BindAliasClient.java/KEY_QUEUE.md) | The concurrent queue being drained |
| [Alias.aliasesWithoutArgs](../../../alias/Alias.java/aliasesWithoutArgs.md) | Primary alias registry |
| [Alias.aliasesWithoutArgs_fromBindCommand](../../../alias/Alias.java/aliasesWithoutArgs_fromBindCommand.md) | Fallback registry (aliases created via `bind` command) |
