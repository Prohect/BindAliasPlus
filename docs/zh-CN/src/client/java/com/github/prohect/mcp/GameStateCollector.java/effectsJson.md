# effectsJson 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
private static String effectsJson(Collection<MobEffectInstance> effects)
```

## 返回值

活跃状态效果的 JSON 数组字符串，每个效果包含 `name`（显示名称）、`amplifier`（从 0 开始）与 `duration`（格式化为 `MM:SS`）。

## 备注

遍历玩家活跃的 `MobEffectInstance` 集合，经 `Component.getString()` 从效果的 `MobEffect.getDisplayName()` 提取显示名称、提取等级与剩余时长（刻经 `formatDuration` 转换为 `MM:SS`）。集合为空或 null 时返回 `"[]"`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [formatDuration](formatDuration.md) | 刻→MM:SS 转换 |
