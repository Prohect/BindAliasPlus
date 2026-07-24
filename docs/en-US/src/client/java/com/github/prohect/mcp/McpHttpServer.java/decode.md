# decode method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static String decode(String s)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `s` | `String` | Percent-encoded string. May contain `%XX` sequences. |

## Remarks

Decodes percent-encoded characters (`%XX` where XX is hex). Does **not** convert `+` to space — the bridge uses `encodeURIComponent` which emits spaces as `%20`, so no special-case logic is needed.

Invalid percent sequences (non-hex characters, truncated) are passed through as-is. Uses `Character.digit()` for hex parsing with validation that both digits are valid hex.

> **Branch note**: This method is named `decode` on 1.21.x (Yarn) branches. On Mojang (26.x) branches it is `decodePercent`. The logic is identical.

## See Also

| Item | Description |
|------|-------------|
| [parseQuery](parseQuery.md) | Uses this to decode query parameters |

*Documented for Commit: [719c5aa51ed1bed2dcd073d67f38a68c1e097f0c](https://github.com/Prohect/BindAliasPlus/tree/719c5aa51ed1bed2dcd073d67f38a68c1e097f0c)*
