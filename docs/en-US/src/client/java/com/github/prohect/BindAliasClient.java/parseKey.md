# parseKey method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
private net.minecraft.client.util.InputUtil.Key parseKey(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `name` | `String` | Key name string — a keyboard key (e.g. `"f"`, `"key.keyboard.f"`, `"left.shift"`) or mouse button (e.g. `"mouse1"`, `"mouse.1"`) |

## Return value

The resolved `InputUtil.Key`, or `null` if the name could not be resolved.

## Remarks

Converts a human-readable key name to Minecraft's `InputUtil.Key`. Tries two resolution strategies:

1. **Keyboard keys**: Calls `InputUtil.getKey("key.keyboard." + name.toLowerCase())`. Minecraft's key name registry translates names like `"f"`, `"left.shift"`, `"key.keyboard.f"` into their internal key constants.

2. **Mouse buttons**: If the keyboard lookup fails and the name starts with `"mouse"`, parses the numeric suffix (e.g. `"mouse1"` → button 1, `"mouse.1"` also works via `toLowerCase`). Returns `InputUtil.Type.MOUSE.getOrCreate(button - 1)` (0-based, so mouse button 1 → index 0).

Invalid mouse button numbers or unrecognized key names log a warning and return `null`.

## See Also

| Item | Description |
|------|-------------|
| [InputUtil.Key](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a) | Minecraft's key type — the return type of this method |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
