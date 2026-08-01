# playersJson 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
private static String playersJson(MinecraftClient mc, ClientPlayerEntity p)
```

## 返回值

附近玩家的 JSON 数组字符串，包含 `name`、`pos`、`dim` 以及采用与 `SoundCapture.directionOf` 相同格式的方向信息。

## 备注

遍历当前关卡中的所有玩家。排除本地玩家。对每个远程玩家，包含显示名、位置和方向信息（相对于本地玩家视角的偏航角/俯仰角 + 距离），通过 `SoundCapture.directionOf` 计算。不同维度的玩家会注明其维度名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SoundCapture.directionOf](SoundCapture.java/directionOf.md) | 共享的方向格式化器 |
