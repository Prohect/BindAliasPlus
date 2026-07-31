# getKeyBindingForAction method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Maps a game action type string to its corresponding vanilla `KeyBinding`.

## Syntax

```java
private static net.minecraft.client.option.KeyBinding getKeyBindingForAction(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `actionType` | `String` | A game action, with or without `"gameKey:"` prefix (e.g., `"attack"`, `"gameKey:forward"`) |

## Return value

The corresponding `MinecraftClient.getInstance().options` KeyBinding, or `null` if the action is not a recognized game key.

## Remarks

Strips the `"gameKey:"` prefix if present, then maps the bare action name to its KeyBinding via a switch expression:

| Action | KeyBinding |
|--------|-----------|
| `attack` | `options.attackKey` |
| `use` | `options.useKey` |
| `forward` | `options.forwardKey` |
| `back` | `options.backKey` |
| `left` | `options.leftKey` |
| `right` | `options.rightKey` |
| `jump` | `options.jumpKey` |
| `sneak` | `options.sneakKey` |
| `sprint` | `options.sprintKey` |

Returns `null` for any unrecognized action — callers then fall through to `lockAliasByName()`/`unlockAliasByName()` to treat the input as a custom alias name.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
