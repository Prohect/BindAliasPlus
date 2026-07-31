# run method (src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java)

Parses 1 or 2 slot arguments (player 1-41, container cN, or variable) and swaps their item stacks.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.SwapSlotAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | One slot (`swapSlot\a`) or two slots separated by `\` (`swapSlot\a\b`) |

## Remarks

**Algorithm (high-level):**

1. Validate `mc.player` and inventory are non-null.
2. Split args by `\` (backslash, `divider4AliasArgs`).
   - 1 arg: swap with selected hotbar slot.
   - 2 args: swap the two specified slots.
   - Other: log warning, return.
3. Parse each arg via `parseSlotRef()` — resolves player numbers (1-41), cN references, and variables.
4. Validate both slots are non-null and not identical.
5. Determine screen context: inventory, creative inventory, container screen, or none.
6. Choose swap strategy:
   - **Hotbar/offhand only:** Use `swapSlotOffhand()` for a 3-step offhand swap sequence with no screen needed.
   - **Container screen open:** Use that screen's menu with `swapInMenu()`.
   - **Otherwise:** Open inventory screen, swap, close inventory screen.

**Return value:** `this` (fluent return).

**Side effects:** Swaps the items in two slots. May open/close the player inventory screen temporarily. Sends network packets to synchronize with the server depending on the swap strategy used.

**No screen suppression:** Works on any non-blocking screen, but if a non-inventory/non-container screen is open and the swap requires a screen, it returns silently.

## See Also

| Item | Description |
|------|-------------|
| [SwapSlotAlias](SwapSlotAlias.md) | Class overview |
| [parseSlotRef](parseSlotRef.md) | Slot argument parsing |
| [swapInMenu](swapInMenu.md) | Menu-based swap logic |
| [swapSlotOffhand](swapSlotOffhand.md) | Offhand packet swap |
| [resolveSlot](resolveSlot.md) | Find Slot object in menu |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
