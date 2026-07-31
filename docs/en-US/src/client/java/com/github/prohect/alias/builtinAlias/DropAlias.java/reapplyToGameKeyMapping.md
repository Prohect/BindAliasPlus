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

After a screen is closed, vanilla Minecraft calls `releaseAll()` on all KeyBindings, which would normally clear the held state. The `reapplyToGameKeyMapping()` mechanism (called from `ReapplyAlias`) re-enables held aliases.

Default behavior from `BuiltinAliasWithBooleanArgs`: if `flag` is true, re-run with `"1"` — which for most aliases means `setPressed(true)` + `timesPressed++`. However, for DropAlias, the `timesPressed++` would cause an **extra unwanted drop** when the cursor is re-locked after closing a screen.

**Customization:** This override only calls `dropKey.setPressed(true)` to restore the held state, and intentionally does **not** increment `timesPressed`. This ensures no extra item is dropped when the player returns to the 3D world after a screen transit.

## See Also

| Item | Description |
|------|-------------|
| [run](run.md) | Main press/release handler |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | Calls `reapplyToGameKeyMapping()` after screen transitions |
| [BuiltinAliasWithBooleanArgs.reapplyToGameKeyMapping()](../../BuiltinAliasWithBooleanArgs.java/reapplyToGameKeyMapping.md) | Default reapply behavior |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
