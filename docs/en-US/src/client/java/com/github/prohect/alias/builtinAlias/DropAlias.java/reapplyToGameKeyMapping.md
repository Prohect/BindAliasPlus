# reapplyToGameKeyMapping method (src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java)

## Syntax

```java
public void reapplyToGameKeyMapping()
```

## Parameters

| Name     | Type | Description |
| -------- | ---- | ----------- |
| _(none)_ |      |             |

## Remarks

Re-asserts the `KeyMapping` state after the cursor is re-locked (e.g., after closing a screen). If `flag` is true, calls `keyDrop.setDown(true)` to maintain the held-down state on the vanilla key binding.

Unlike `run()` with `flag == true`, this method does **not** increment `clickCount` — doing so would cause an extra item to drop every time the cursor is re-locked.

**Side effects**: Sets `Minecraft.getInstance().options.keyDrop.setDown(true)` if held.

**Callers**: Called by `ReapplyAlias.run()` and by the cursor-re-lock handling in the alias system.

**Error handling**: No-op if `flag` is false.

## See Also

| Item                                                 | Description                       |
| ---------------------------------------------------- | --------------------------------- |
| [run](run.md)                                        | Press/release lifecycle           |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | Orchestrates reapplying held keys |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
