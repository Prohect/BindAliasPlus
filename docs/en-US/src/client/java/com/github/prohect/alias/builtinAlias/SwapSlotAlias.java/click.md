# click method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

## Syntax

```java
private static void click(ClientPlayerInteractionManager im, ScreenHandler menu, Slot s, int btn, SlotActionType act, ClientPlayerEntity p)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `im` | `ClientPlayerInteractionManager` | The client interaction manager used to send the click packet |
| `menu` | `ScreenHandler` | The container menu whose `syncId` identifies the window to the server |
| `s` | `Slot` | The target slot to click; its `id` is the slot index within the menu |
| `btn` | `int` | Mouse button / data value for the click action |
| `act` | `SlotActionType` | The type of click action (`SWAP`, `PICKUP`, etc.) |
| `p` | `ClientPlayerEntity` | The local player (required by `clickSlot`) |

## Remarks

Private static helper that performs a single container click by delegating to `ClientPlayerInteractionManager#clickSlot(int syncId, int slotId, int button, SlotActionType action, PlayerEntity player)`. This is the sole point in `SwapSlotAlias` where packets are sent for container interactions — both `swapInMenu` and the PICKUP fallback path route through this method.

On the 1.21.x (Yarn) branch, the vanilla method is called `clickSlot` on `ClientPlayerInteractionManager`; the mod wraps it in this `click` method to keep the call site concise and to centralize the parameter mapping. The `syncId` identifies which container window this click belongs to (needed for server-side validation), and `s.id` is the slot's position within that window's slot list.

Used by `swapInMenu` with three patterns:
- **SWAP click** (`SlotActionType.SWAP`, `btn` = hotbar index): exchanges a hotbar/offhand item with the target slot in a single packet.
- **PICKUP click** (`SlotActionType.PICKUP`, `btn = 0`): picks up or places items one at a time — used when neither slot is hotbar-addressable.
- **Cleanup PICKUP**: after a failed PICKUP sequence, moves leftover items off the cursor.

The 26.x (Mojang) equivalent of this method was called `clickSlot` and used `MultiPlayerGameMode#handleInventoryMouseClick`.

## See Also

| Item | Description |
|------|-------------|
| [swapInMenu](swapInMenu.md) | The primary caller — orchestrates the swap strategy |
| [SwapSlotAlias](SwapSlotAlias.md) | The enclosing class |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
