# commandUnbindExecute method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
private int commandUnbindExecute(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `keyName` | `String` | Human-readable key name (e.g. `"f"`, `"mouse1"`, `"key.keyboard.f"`) |

## Remarks

Removes a key binding from `BINDING_PLUS`. First resolves the key name to an `InputUtil.Key` via `parseKey`. If the key is unknown, returns `0` (no-op). Otherwise removes the entry and returns `1`.

## See Also

| Item | Description |
|------|-------------|
| [parseKey](parseKey.md) | Converts key name strings to `InputUtil.Key` |
| [BINDING_PLUS](BINDING_PLUS.md) | The key→alias binding map |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
