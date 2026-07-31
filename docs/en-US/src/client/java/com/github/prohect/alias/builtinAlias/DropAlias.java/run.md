# run method (src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java)

Handles `+drop` (press) and `-drop` (release) with immediate first drop and container-screen awareness.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.DropAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | `"1"` for press (`+drop`), `"0"` for release (`-drop`) |

## Remarks

**Press (`flag == true`):**

1. Calls `parseArgs(args)` to set `this.flag`.
2. **Screen suppression (press only):** If a text-input screen is open, returns immediately.
3. If player is null, returns immediately.
4. Checks if a container screen (`HandledScreen`) is open:
   - **Container path:** If the hovered slot has an item, drops it immediately via `containerScreen.onMouseClick(hoveredSlot, hoveredSlot.index, button, SlotActionType.THROW)`. The button is 1 (entire stack) when Ctrl is held, 0 (single item) otherwise. Returns without setting the KeyBinding — subsequent continuous drops are handled by `tickDrop()`.
   - **3D game path:** Sets `dropKey.setPressed(true)` and increments `timesPressed++` for the immediate first drop. Continuous drops are driven by `tickDrop()` thereafter.

**Release (`flag == false`):**

1. Resets `ticksHeld = 0`.
2. Sets `dropKey.setPressed(false)`.

## See Also

| Item | Description |
|------|-------------|
| [tickDrop](tickDrop.md) | Continuous drop driver called each client tick |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | Reapply after screen transitions without extra drop |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
