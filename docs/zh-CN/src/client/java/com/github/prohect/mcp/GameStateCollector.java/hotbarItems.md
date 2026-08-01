# hotbarItems 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java)

## 语法

```java
static Map<String, String> hotbarItems(ClientPlayerEntity p)
```

## 返回值

一个从快捷栏槽位键（`"1"`–`"9"`）到物品描述字符串的映射。仅包含非空槽位。

## 备注

遍历玩家的快捷栏物品栏槽位（索引 0-8）。对每个非空槽位，通过 `appendTooltipIfValuable` 格式化物品描述（包括附魔/lore 注解）。槽位键为 1 基（`"1"`–`"9"`）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [hotbarFullJson](hotbarFullJson.md) | 将结果格式化为 JSON |
| [hotbarDiffJson](hotbarDiffJson.md) | 产生逐槽位 diff |
| [appendTooltipIfValuable](appendTooltipIfValuable.md) | 物品描述格式化器 |
