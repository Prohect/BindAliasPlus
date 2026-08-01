# decodePercent 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
private static String decodePercent(String s)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `s` | `String` | 要解码的百分号编码字符串 |

## 返回值

解码后的字符串，`%XX` 序列替换为对应字符。

## 备注

解码 HTTP 查询字符串中的百分号编码字符。每个 `%XX` 序列转换为一个字节并按 UTF-8 字符解释。未编码字符原样通过。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [parseQuery](parseQuery.md) | 调用方 |
