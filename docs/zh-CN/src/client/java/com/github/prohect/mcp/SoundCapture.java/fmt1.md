# fmt1 方法（src/client/java/com/github/prohect/mcp/SoundCapture.java）

## 语法

```java
private static String fmt1(double v)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `v` | `double` | 要格式化的值 |

## 返回值

使用 `Locale.ROOT` 保留 1 位小数的值。

## 备注

距离格式化辅助方法（例如 `"4.2"`）。模式与 `GameStateCollector.fmt1` 相同，但本类保留了一份私有副本，避免声音事件产生跨类耦合。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [directionOf](directionOf.md) | 调用方 |
| [GameStateCollector.fmt1](GameStateCollector.java/fmt1.md) | GameStateCollector 中的等价方法 |
