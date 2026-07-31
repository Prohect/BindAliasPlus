# decodePercent method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static String decodePercent(String s)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `s` | `String` | Percent-encoded string. May contain `%XX` sequences. |

## Remarks

Decodes percent-encoded characters (`%XX` where XX is hex). Does **not** convert `+` to space — the bridge uses `encodeURIComponent` which emits spaces as `%20`, so no special-case logic is needed.

Invalid percent sequences (non-hex characters, truncated) are passed through as-is. Uses `Character.digit()` for hex parsing with validation that both digits are valid hex.

## See Also

| Item | Description |
|------|-------------|
| [parseQuery](parseQuery.md) | Uses this to decode query parameters |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
