# parseQuery 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
private static Map<String, String> parseQuery(String query)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `query` | `String` | HTTP 请求 URI 中的原始查询字符串（`?` 之后） |

## 返回值

解码后的查询参数名→值 map。百分号编码字符经 `decodePercent` 解码。

## 备注

把 HTTP 查询字符串（例如 `"nap=5&def=slot%5C1"`）解析为键值对 map。处理空值（无 `=` 的键）时存储空字符串。使用 `decodePercent` 做百分号解码。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [decodePercent](decodePercent.md) | 百分号解码辅助方法 |
