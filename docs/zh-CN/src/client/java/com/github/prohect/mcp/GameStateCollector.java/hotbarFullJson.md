# hotbarFullJson 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
static String hotbarFullJson(Map<String, String> items)
```

## 返回值

快捷栏槽位 1-9 的完整 JSON 对象字符串，各槽位以槽位编号为键。空槽位产生 `null` 值。

## 备注

将快捷栏物品 map 格式化为包含全部 9 个槽位的完整 JSON 对象。由 `StateTracker.begin` 在首个快照与世界变化时使用。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [hotbarDiffJson](hotbarDiffJson.md) | 增量差分变体 |
| [StateTracker.begin](StateTracker.java/begin.md) | 调用方 |
