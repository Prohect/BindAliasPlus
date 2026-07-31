# decodePercent method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static String decodePercent(String s)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `s` | `String` | The percent-encoded string to decode |

## Return value

The decoded string with `%XX` sequences replaced by their corresponding characters.

## Remarks

Decodes percent-encoded characters in HTTP query strings. Each `%XX` sequence is converted to a byte and interpreted as a UTF-8 character. Non-encoded characters pass through unchanged.

## See Also

| Item | Description |
|------|-------------|
| [parseQuery](parseQuery.md) | The caller |
