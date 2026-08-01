# worldName 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
private static String worldName(Minecraft mc)
```

## 返回值

维度注册表键路径字符串（例如 `"minecraft:overworld"`、`"minecraft:the_nether"`、`"minecraft:the_end"`），玩家或关卡为 null 时为 `null`。

## 备注

通过 `mc.player.level().dimension().location().toString()` 从玩家当前关卡提取世界/维度名。不在世界内时返回 `null`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [collect](collect.md) | 调用方 |
