# reapplyToGameKeyMapping method (src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java)

Re-synchronizes the drop key state after a screen transition. Overrides the default `BuiltinAliasWithBooleanArgs` behavior to avoid an extra drop event.

## Syntax

```java
public void reapplyToGameKeyMapping()
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| _(none)_ | | |

## Remarks

After a screen is closed, vanilla Minecraft calls `releaseAll()` on all KeyMappings, which would normally clear the held state. The `reapplyToGameKeyMapping()` mechanism (called from `ReapplyAlias`) re-enables held aliases.

Default behavior from `BuiltinAliasWithBooleanArgs`: if `flag` is true, re-run with `"1"` — which for most aliases means `setDown(true)` + `clickCount++`. However, for DropAlias, the `clickCount++` would cause an **extra unwanted drop** when the cursor is re-locked after closing a screen.

**Customization:** This override only calls `keyDrop.setDown(true)` to restore the held state, and intentionally does **not** increment `clickCount`. This ensures no extra item is dropped when the player returns to the 3D world after a screen transit.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | Main press/release handler |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | Calls `reapplyToGameKeyMapping()` after screen transitions |
| [BuiltinAliasWithBooleanArgs.reapplyToGameKeyMapping()](../../BuiltinAliasWithBooleanArgs.java/reapplyToGameKeyMapping.md) | Default reapply behavior |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
