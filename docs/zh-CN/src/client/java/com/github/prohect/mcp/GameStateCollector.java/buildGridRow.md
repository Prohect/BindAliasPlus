# buildGridRow 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
private static String buildGridRow(String[] cells, int width)
```

## 返回值

格式化后的合成格行字符串，槽位索引以空格分隔。不存在的格子以相应的视觉占位符表示。

## 备注

构建合成格中单行的文本表示，供 `gridJson` 使用。为 `null` 或空的格子渲染为相应的填充符。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [gridJson](gridJson.md) | 调用方 |
