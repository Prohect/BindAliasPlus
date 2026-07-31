# lastPath field (src/client/java/com/github/prohect/mcp/ScreenshotCapture.java)

## Syntax

```java
public static volatile String lastPath
```

## Remarks

Absolute filesystem path of the last captured screenshot. Populated by `NativeImageMixin.onWriteToFile` alongside the PNG byte delivery. Included in the MCP screenshot JSON response under the `"path"` key. `volatile` for cross-thread visibility.

## See Also

| Item | Description |
|------|-------------|
| [lastName](lastName.md) | The corresponding filename |
| [NativeImageMixin.onWriteToFile](../../mixin/client/NativeImageMixin.java/onWriteToFile.md) | Populates this field |
