# click method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
private static void click(ClientPlayerInteractionManager im, ScreenHandler menu, Slot s, int btn, SlotActionType act,
        ClientPlayerEntity p)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `im` | `ClientPlayerInteractionManager` | The client's interaction manager. Provides the `clickSlot()` method that sends slot-click packets to the server. |
| `menu` | `ScreenHandler` | The currently open container menu. Its `syncId` is passed to `clickSlot()` so the server can identify which menu the click targets. |
| `s` | `Slot` | The slot to click. Its `id` (the raw slot index within the menu) is sent to the server. |
| `btn` | `int` | The button index for the click. Hotbar slots use 0–8; the offhand uses 40. For `PICKUP` clicks this is 0. |
| `act` | `SlotActionType` | The type of slot action: `SlotActionType.SWAP` for hotbar/offhand swaps or `SlotActionType.PICKUP` for pickup/place operations. |
| `p` | `ClientPlayerEntity` | The local player. Passed through to `clickSlot()` but unused by this method itself. |

## Remarks

A thin wrapper around `ClientPlayerInteractionManager.clickSlot()`. The method passes `menu.syncId`, `s.id`, `btn`, `act`, and `p` directly to the interaction manager, which serializes them into a `ClickSlotC2SPacket` and sends it to the server.

This is not an offhand swap — it sends a vanilla slot click. For offhand swaps, use `swapOff()` instead (see [swapOff](swapOff.md)).

Called exclusively by `swapInMenu()` for both the SWAP and PICKUP click sequences.

> **Mojang mapping note**: In 1.21.9+ (Mojang-mapped branches), the equivalent is `clickSlot()` with parameters `MultiPlayerGameMode`, `AbstractContainerMenu`, `Slot`, `int` (button), `ContainerInput` (act), and `LocalPlayer`. In 1.21.8 (Yarn), it is `click()` with `ClientPlayerInteractionManager`, `ScreenHandler`, `Slot`, `int`, `SlotActionType`, and `ClientPlayerEntity`.

## See Also

| Item | Description |
|------|-------------|
| [swapInMenu](swapInMenu.md) | The only caller of this method. Uses it for both SWAP and PICKUP click paths. |
| [swapOff](swapOff.md) | Sends packet-based offhand swaps (no slot click). Used alongside `click` for inventory-only swaps. |
| [swapButton](swapButton.md) | Determines the `btn` value (hotbar index, offhand 40, or -1 for no SWAP possible). |

*Documented for Commit: [ef1c450870a32bbba509f486207fd6b144527f15](https://github.com/Prohect/BindAliasPlus/tree/ef1c450870a32bbba509f486207fd6b144527f15)*
