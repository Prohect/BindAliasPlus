# extractJsonStringField method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static String extractJsonStringField(String json, String fieldName)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `json` | `String` | The JSON string to search in |
| `fieldName` | `String` | The field name to extract the value of |

## Return value

The string value of the field, or `null` if not found.

## Remarks

Minimal JSON field extractor — does not use a full JSON parser. Searches for `"<fieldName>"` followed by `:` and extracts the following JSON string value (in double quotes). Handles escaped characters within the string value (`\"`, `\\`, `\n`, etc.). Used by handlers that need to parse simple JSON request bodies without adding a JSON library dependency. Returns `null` if the field is not found or the value is the literal JSON `null`.

## See Also

| Item | Description |
