# fmt1 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
static String fmt1(double v)
```

## 返回值

保留 1 位小数的 double 值（例如 `"123.5"`）。使用 `Locale.ROOT` 保证小数点分隔符一致。

## 备注

共享格式化辅助方法，用于坐标与距离。等价于 `String.format(Locale.ROOT, "%.1f", v)`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [fmt2](fmt2.md) | 保留 2 位小数的变体 |
