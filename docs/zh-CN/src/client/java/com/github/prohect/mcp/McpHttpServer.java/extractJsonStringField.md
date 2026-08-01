# extractJsonStringField 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
private static String extractJsonStringField(String json, String fieldName)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `json` | `String` | 要在其中搜索的 JSON 字符串 |
| `fieldName` | `String` | 要提取值的字段名 |

## 返回值

字段的字符串值，未找到时返回 `null`。

## 备注

极简 JSON 字段提取器——不使用完整 JSON 解析器。搜索 `"<fieldName>"` 后跟 `:`，提取随后的 JSON 字符串值（双引号内）。处理字符串值内的转义字符（`\"`、`\\`、`\n` 等）。供需要在请求体中解析简单 JSON 而又不想引入 JSON 库依赖的处理器使用。字段未找到或值为字面量 JSON `null` 时返回 `null`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
