# lastPath 字段（src/client/java/com/github/prohect/mcp/ScreenshotCapture.java）

## 语法

```java
public static volatile String lastPath
```

## 备注

最近一次捕获截图的绝对文件系统路径。与 PNG 字节投递一同由 `NativeImageMixin.onWriteToFile` 填充。以 `"path"` 键包含在 MCP 截图 JSON 响应中。`volatile` 保证跨线程可见性。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [lastName](lastName.md) | 对应的文件名 |
| [NativeImageMixin.onWriteToFile](../../mixin/client/NativeImageMixin.java/onWriteToFile.md) | 填充此字段 |
