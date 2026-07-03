# commandUnbindExecute method (src/client/java/com/github/prohect/BindAliasPlusClient.java)

## Syntax

```java
private int commandUnbindExecute(java.lang.String)
```

## Parameters

| Name      | Type     | Description                                                   |
| --------- | -------- | ------------------------------------------------------------- |
| `keyName` | `String` | Key string (e.g., `"f"`, `"mouse1"`). Parsed by `parseKey()`. |

## Remarks

Removes the binding for the given key from `BINDING_PLUS`.

Algorithm:

1. Call `parseKey(keyName)` to convert the string to an `InputConstants.Key`.
2. If `key` is `null` (unrecognized key), return `0`.
3. Call `BINDING_PLUS.remove(key)` and return `1`.

Return values:

- `0` — unknown key; no binding removed.
- `1` — binding removed (or key wasn't bound to begin with; `remove` returns `null`).

## See Also

| Item                                        | Description                               |
| ------------------------------------------- | ----------------------------------------- |
| [parseKey](parseKey.md)                     | Converts key name to `InputConstants.Key` |
| [commandBindExecute](commandBindExecute.md) | Creates bindings (inverse operation)      |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
