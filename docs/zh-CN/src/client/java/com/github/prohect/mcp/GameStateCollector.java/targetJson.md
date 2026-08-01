# targetJson 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
private static String targetJson(MinecraftClient mc, ClientPlayerEntity p)
```

## 返回值

玩家注视的实体的 JSON 对象字符串：`{"type":"...","name":"...","pos":{...}}`；没有注视实体则为 `null`。

## 备注

使用玩家的 `HitResult`（来自 `mc.hitResult`）。如果命中结果是 `EntityHitResult`，则提取实体类型（注册表键）、显示名和位置。对方块命中结果或不存在目标时返回 `null`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [posJson](posJson.md) | 共享的位置格式化 |
