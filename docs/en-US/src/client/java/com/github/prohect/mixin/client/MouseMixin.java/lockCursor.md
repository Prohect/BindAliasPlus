# lockCursor method (src/client/java/com/github/prohect/mixin/client/MouseMixin.java)

## Syntax

```java
private void lockCursor(org.spongepowered.asm.mixin.injection.callback.CallbackInfo)
```

## Parameters

| Name | Type           | Description                   |
| ---- | -------------- | ----------------------------- |
| `ci` | `CallbackInfo` | Mixin callback info (unused). |

## Remarks

Re-applies held key states after the game re-grabs the cursor (e.g., when closing a screen and returning to 3D rendering).

**Why this is needed**: When the game opens a screen, it releases all keys from `gameOptions`. When the screen closes and the game returns to 3D rendering, it re-grabs the cursor and re-checks GLFW key states to restore held keys. However, aliases that were programmatically holding keys (via `BuiltinAliasWithBooleanArgs`) may have been released during this process and need to be re-applied.

Algorithm:

1. Iterates `Alias.aliasesWithArgs_notSuggested` and `Alias.aliasesWithArgs`.
2. For each entry, if it's a `BuiltinAliasWithBooleanArgs<?>` instance, checks its `flag` field.
3. If `flag` is `true` (key was being held), calls `builtinAliasWithBooleanArgs.reapplyToGameKeyMapping()`, which calls `run("1")` to re-press the key.

Side effects: re-sets `KeyMapping.setDown(true)` and increments `clickCount` for all aliases whose `flag` is `true`. This ensures continuous movement/attack keys remain active after screen transitions.

Callers: called by the Mixin framework after `MouseHandler.grabMouse()` returns. This fires during `MinecraftClient.setScreen(null)` and similar screen-close transitions.

## See Also

| Item                                                                                                                              | Description                           |
| --------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------- |
| [MouseMixin](MouseMixin.md)                                                                                                       | Owning mixin class                    |
| [BuiltinAliasWithBooleanArgs.reapplyToGameKeyMapping](../../../alias/BuiltinAliasWithBooleanArgs.java/reapplyToGameKeyMapping.md) | The re-apply method called here       |
| [Alias.aliasesWithArgs](../../../alias/Alias.java/aliasesWithArgs.md)                                                             | Registry iterated over                |
| [AttackAlias](../../../alias/builtinAlias/AttackAlias.java/AttackAlias.md)                                                        | Example alias that benefits from this |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
