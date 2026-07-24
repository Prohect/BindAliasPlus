# parseQuery method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static Map<String, String> parseQuery(String query)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `query` | `String` | Raw URL query string (e.g. `"name=foo&def=bar%20baz"`). May be null or blank. |

## Remarks

Parses a URL query string into a `Map<String, String>`. Splits on `&`, then splits each pair on the first `=`. Both keys and values are percent-decoded via `decodePercent()`.

Returns an empty map if the query is null or blank. No duplicate key handling — the last occurrence wins.

## See Also

| Item | Description |
|------|-------------|
| [decodePercent](decodePercent.md) | Percent-decoding used on each key/value |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
