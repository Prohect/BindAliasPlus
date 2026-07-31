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

Parses a URL query string into a `Map<String, String>`. Splits on `&`, then splits each pair on the first `=`. Both keys and values are percent-decoded via `decode()`.

Returns an empty map if the query is null or blank. No duplicate key handling — the last occurrence wins.

## See Also

| Item | Description |
|------|-------------|
| [decode](decode.md) | Percent-decoding used on each key/value |

*Documented for Commit: [719c5aa51ed1bed2dcd073d67f38a68c1e097f0c](https://github.com/Prohect/BindAlias/tree/719c5aa51ed1bed2dcd073d67f38a68c1e097f0c)*
