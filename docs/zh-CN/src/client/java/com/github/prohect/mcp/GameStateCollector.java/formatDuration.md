# formatDuration 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
private static String formatDuration(int ticks)
```

## 返回值

格式化为 `"MM:SS"` 的时长（按 20 刻/秒由游戏刻换算为分与秒）。

## 备注

把刻数转换为人类可读的 `MM:SS` 字符串。用于状态效果时长及其他基于刻的计时器显示。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [effectsJson](effectsJson.md) | 主要调用方 |
