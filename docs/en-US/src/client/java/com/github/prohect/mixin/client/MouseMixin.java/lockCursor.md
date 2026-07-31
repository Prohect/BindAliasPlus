# lockCursor method (src/client/java/com/github/prohect/mixin/client/MouseMixin.java)

## Syntax

```java
@Inject(at = @At("RETURN"), method = "grabMouse")
private void lockCursor(CallbackInfo ci)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `ci` | `CallbackInfo` | Unused callback |

## Remarks

Injected at `RETURN` of `MouseHandler#grabMouse()`. After the game re-grabs the cursor (typically after closing a screen returning to the 3D world), iterates all registered `BuiltinAliasWithBooleanArgs` instances in both `aliasesWithArgs_notSuggested` and `aliasesWithArgs` and calls `reapplyToGameKeyMapping()` on each. This re-synchronizes the game's key-mapping state with any held alias flags (e.g., `+forward`, `+attack`) that may have been cleared by vanilla's `releaseAll()` during the screen transition.

## See Also

| Item | Description |
|------|-------------|
| [BuiltinAliasWithBooleanArgs.reapplyToGameKeyMapping](../../../alias/BuiltinAliasWithBooleanArgs.java/reapplyToGameKeyMapping.md) | The reapply method called on each held alias |
| [Alias.aliasesWithArgs_notSuggested](../../../alias/Alias.java/aliasesWithArgs_notSuggested.md) | First alias map iterated |
| [Alias.aliasesWithArgs](../../../alias/Alias.java/aliasesWithArgs.md) | Second alias map iterated |
