# gridJson 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
private static String gridJson(List<int[]> grid)
```

## 返回值

表示合成格布局的 JSON 字符串，包含槽位索引与尺寸。合成格列表为空或 null 时返回 `null`。

## 备注

从容器菜单的合成槽位与配方书组件提取。每个合成格条目是构成一行的槽位索引 `int[]`。格式化为带 `rows` 与 `cols` 元数据的 JSON，外加行模式数组（槽位索引以空格分隔，例如 2×2 合成格的 `"0 1"`、`"3 4"`）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [buildGridRow](buildGridRow.md) | 行格式化辅助方法 |
| [containerSnapshot](containerSnapshot.md) | 调用方 |
