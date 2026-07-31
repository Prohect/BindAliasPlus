# getKeyBindingForAction method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Maps a game action type string to its corresponding vanilla `KeyMapping`.

## Syntax

```java
private static net.minecraft.client.KeyMapping getKeyBindingForAction(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `actionType` | `String` | A game action, with or without `"gameKey:"` prefix (e.g., `"attack"`, `"gameKey:forward"`) |

## Return value

The corresponding `Minecraft.options` KeyMapping, or `null` if the action is not a recognized game key.

## Remarks

Strips the `"gameKey:"` prefix if present, then maps the bare action name to its KeyMapping via a switch expression:

| Action | KeyMapping |
|--------|-----------|
| `attack` | `options.keyAttack` |
| `use` | `options.keyUse` |
| `forward` | `options.keyUp` |
| `back` | `options.keyDown` |
| `left` | `options.keyLeft` |
| `right` | `options.keyRight` |
| `jump` | `options.keyJump` |
| `sneak` | `options.keyShift` |
| `sprint` | `options.keySprint` |

Returns `null` for any unrecognized action — callers then fall through to `lockAliasByName()`/`unlockAliasByName()` to treat the input as a custom alias name.

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
