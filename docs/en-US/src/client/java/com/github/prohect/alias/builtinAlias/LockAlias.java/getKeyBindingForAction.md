# getKeyBindingForAction method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
private static net.minecraft.client.KeyMapping getKeyBindingForAction(java.lang.String)
```

## Parameters

| Name         | Type     | Description                                                                                 |
| ------------ | -------- | ------------------------------------------------------------------------------------------- |
| `actionType` | `String` | Action identifier, optionally prefixed with `gameKey:` (e.g. `gameKey:attack` or `attack`). |

## Remarks

Maps a string action type to the corresponding vanilla `KeyMapping` from `Minecraft.getInstance().options`. Strips the `gameKey:` prefix if present, then uses a `switch` expression to return the appropriate key binding.

**Mapping**:

- `attack` → `options.keyAttack`
- `use` → `options.keyUse`
- `forward` → `options.keyUp`
- `back` → `options.keyDown`
- `left` → `options.keyLeft`
- `right` → `options.keyRight`
- `jump` → `options.keyJump`
- `sneak` → `options.keyShift`
- `sprint` → `options.keySprint`

Returns `null` for unrecognized actions (which triggers alias-name locking fallback in callers).

**Side effects**: None (reads game options, which are constant references).

**Callers**: `lockAction()`, `unlockAction()`.

Return value: The matching `KeyMapping`, or `null` if the action type is not a recognized vanilla key.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
