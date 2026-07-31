# jsonEscape method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static String jsonEscape(String s)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `s` | `String` | Raw string to escape. May be `null`. |

## Remarks

Minimal JSON string escaper — no external dependency required. Wraps the result in double quotes. Handles:
- `"` → `\"`
- `\\` → `\\\\`
- `\n` → `\\n`
- `\r` → `\\r`
- `\t` → `\\t`

Returns `"null"` (JSON literal) if the input is `null`. All other characters pass through unchanged.

Used by every endpoint handler when embedding strings into JSON responses, including player names, item IDs, error messages, and file paths.

## See Also

| Item | Description |
|------|-------------|
| [sendJson](sendJson.md) | Sends the final JSON — callers compose strings with this |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
