# parseKey method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
private com.mojang.blaze3d.platform.InputConstants$Key parseKey(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                            |
| ------ | -------- | -------------------------------------------------------------------------------------- |
| `name` | `String` | The key name string, e.g., `"f"`, `"shift"`, `"mouse1"`, `"mouse5"`. Case-insensitive. |

## Remarks

Converts a key name string to an `InputConstants.Key` for use in `BINDING_PLUS` lookups.

Algorithm:

1. Try `InputConstants.getKey("key.keyboard." + name.toLowerCase())`.
   This resolves standard keyboard key names (e.g., `"f"` → `key.keyboard.f`).
2. If step 1 returns `null` and the name starts with `"mouse"` (case-insensitive):
   - Parse the suffix as an integer (e.g., `"mouse1"` → `1`).
   - Return `InputConstants.Type.MOUSE.getOrCreate(button - 1)` (1-based to 0-based).
   - If the suffix isn't a valid int, log a warning and return `null`.
3. Otherwise return `null`.

Return value: the resolved key, or `null` if unrecognized.

**Error handling**: Malformed input returns `null` (callers check for `null`).
Malformed mouse button numbers log a warning.

## See Also

| Item                                                              | Description                                |
| ----------------------------------------------------------------- | ------------------------------------------ |
| [commandBindExecute](commandBindExecute.md)                       | Calls `parseKey` before creating a binding |
| [commandUnbindExecute](commandUnbindExecute.md)                   | Calls `parseKey` before removing a binding |
| [commandBindByAliasNameExecute](commandBindByAliasNameExecute.md) | Calls `parseKey` before binding by name    |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
