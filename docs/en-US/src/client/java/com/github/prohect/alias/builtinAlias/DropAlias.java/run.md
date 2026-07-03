# run method (src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                |
| ------ | -------- | ------------------------------------------------------------------------------------------ |
| `args` | `String` | A boolean flag string — `"1"` to press (start dropping), `"0"` to release (stop dropping). |

## Remarks

Handles the press/release lifecycle for hold-to-repeat dropping.

**Algorithm — press (`flag == true`)**:

1. Cancel if a text input screen is open and the key was just pressed.
2. If in a container screen (`AbstractContainerScreen`): immediately drop the hovered item once via `slotClicked(…, THROW)`. The `button` parameter uses 1 (whole stack) if Ctrl is held, 0 (single item) otherwise. Continuous dropping is handled by `tickDrop()`.
3. If in the 3D game: set `keyDrop.setDown(true)` and increment `clickCount` for an immediate first drop. Continuous dropping handled by `tickDrop()`.

**Algorithm — release (`flag == false`)**:

1. Reset `ticksHeld` to 0.
2. Set `keyDrop.setDown(false)`.

**Side effects**: Modifies `ticksHeld`, vanilla `KeyMapping` state. When in a container screen, sends slot-click packets. In 3D, increments `keyDrop.clickCount` which vanilla processes as a drop action.

**Callers**: Invoked by the alias dispatch system.

## See Also

| Item                                                  | Description                            |
| ----------------------------------------------------- | -------------------------------------- |
| [tickDrop](tickDrop.md)                               | Per-tick continuous dropping           |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | Re-asserts key state after cursor lock |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
