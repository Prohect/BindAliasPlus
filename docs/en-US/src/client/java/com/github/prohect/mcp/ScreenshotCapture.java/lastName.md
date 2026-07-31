# lastName field (src/client/java/com/github/prohect/mcp/ScreenshotCapture.java)

## Syntax

```java
public static volatile String lastName
```

## Remarks

File name (without path) of the last captured screenshot. Populated by `NativeImageMixin.onWriteToFile` alongside the PNG byte delivery. Included in the MCP screenshot JSON response under the `"name"` key. `volatile` for cross-thread visibility.

## See Also

| Item | Description |
|------|-------------|
| [lastPath](lastPath.md) | The corresponding full path |
| [NativeImageMixin.onWriteToFile](../../mixin/client/NativeImageMixin.java/onWriteToFile.md) | Populates this field |
