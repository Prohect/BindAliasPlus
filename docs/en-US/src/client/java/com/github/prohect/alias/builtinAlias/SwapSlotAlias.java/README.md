# SwapSlotAlias

Complex builtin alias that swaps item stacks between any two inventory or container slots. Usage: `swapSlot\a\b` or `swapSlot\a` (swap with selected hotbar slot).

## Fields

_No public/protected fields (uses private `SlotRef` record for internal slot representation)._

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | Parse 1-2 slot args, determine strategy, perform swap |
| [parseSlotRef](parseSlotRef.md) | `parseSlotRef(String)` | Parse slot argument into player/container SlotRef |
| [resolveSlot](resolveSlot.md) | `resolveSlot(menu, SlotRef)` | Find Slot object in menu matching SlotRef |
| [swapButton](swapButton.md) | `swapButton(Slot)` | Get SWAP-click button if hotbar/offhand-addressable |
| [swapInMenu](swapInMenu.md) | `swapInMenu(...)` | Swap two slots using SWAP or PICKUP strategy |
| [clickSlot](clickSlot.md) | `clickSlot(...)` | Perform a single container click |
| [swapSlotOffhand](swapSlotOffhand.md) | `swapSlotOffhand(...)` | Packet-based hotbar↔offhand swap |

## See Also

| Item | Description |
|------|-------------|
| [VarAlias](../VarAlias.java/README.md) | Variable resolution for slot arguments |
| [SlotAlias](../SlotAlias.java/README.md) | Select a hotbar slot |
| [SwapHandAlias](../SwapHandAlias.java/README.md) | Simple main↔offhand swap |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
