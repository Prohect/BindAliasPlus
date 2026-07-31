# parseQuery method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static Map<String, String> parseQuery(String query)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `query` | `String` | The raw query string from the HTTP request URI (after `?`) |

## Return value

A map of decoded query parameter name → value. Percent-encoded characters are decoded via `decodePercent`.

## Remarks

Parses an HTTP query string (e.g., `"nap=5&def=slot%5C1"`) into a map of key-value pairs. Handles empty values (key with no `=`) by storing an empty string. Uses `decodePercent` for percent-decoding.

## See Also

| Item | Description |
|------|-------------|
| [decodePercent](decodePercent.md) | Percent-decoding helper |
