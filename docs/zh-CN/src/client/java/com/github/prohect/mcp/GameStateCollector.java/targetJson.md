# targetJson 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
private static String targetJson(Minecraft mc, LocalPlayer p)
```

## 返回值

玩家正在注视的实体的 JSON 对象字符串：`{"type":"...","name":"...","pos":{...}}`，若无目标实体则为 `null`。

## 备注

使用玩家的 `HitResult`（来自 `mc.hitResult`）。若命中结果是 `EntityHitResult`，则提取实体类型（注册表键）、显示名称和位置。对方块命中结果或不存在目标时返回 `null`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [posJson](posJson.md) | 共享的位置格式化 |
