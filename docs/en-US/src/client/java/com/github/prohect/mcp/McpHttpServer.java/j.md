# j method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static String j(String s)
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

> **Branch note**: This method is named `j` on 1.21.x (Yarn) branches. On Mojang (26.x) branches it is `jsonEscape`. The logic is identical.

## See Also

| Item | Description |
|------|-------------|
| [sendJson](sendJson.md) | Sends the final JSON — callers compose strings with this |

*Documented for Commit: [719c5aa51ed1bed2dcd073d67f38a68c1e097f0c](https://github.com/Prohect/BindAlias/tree/719c5aa51ed1bed2dcd073d67f38a68c1e097f0c)*
