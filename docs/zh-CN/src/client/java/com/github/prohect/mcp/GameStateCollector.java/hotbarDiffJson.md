# hotbarDiffJson 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
static String hotbarDiffJson(Map<String, String> last, Map<String, String> cur)
```

## 返回值

只包含发生变化快捷栏槽位的差分 JSON 对象字符串。值为 `null` 的条目表示该槽位变空。无变化时返回 `null`。

## 备注

计算当前与先前快捷栏物品 map 之间的逐槽位差分。逻辑与 `containerDiffJson` 相同：新增/变化的槽位输出新值，移除的槽位输出 `null`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [hotbarFullJson](hotbarFullJson.md) | 完整变体 |
| [containerDiffJson](containerDiffJson.md) | 对应的容器差分 |
| [StateTracker.begin](StateTracker.java/begin.md) | 调用方 |
