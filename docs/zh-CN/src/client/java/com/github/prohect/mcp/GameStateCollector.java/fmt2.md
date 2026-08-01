# fmt2 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
static String fmt2(double v)
```

## 返回值

保留 2 位小数的 double 值（例如 `"123.46"`）。使用 `Locale.ROOT` 保证小数点分隔符一致。

## 备注

共享格式化辅助方法，用于玩家朝向角度（偏航角/俯仰角）。等价于 `String.format(Locale.ROOT, "%.2f", v)`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [fmt1](fmt1.md) | 保留 1 位小数的变体 |
