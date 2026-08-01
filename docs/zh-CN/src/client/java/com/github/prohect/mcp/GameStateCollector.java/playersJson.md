# playersJson 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
private static String playersJson(Minecraft mc, LocalPlayer p)
```

## 返回值

附近玩家的 JSON 数组字符串，包含 `name`、`pos`、`dim` 和方位信息，格式与 `SoundCapture.directionOf` 相同。

## 备注

遍历当前关卡中的所有玩家。排除本地玩家。对每个远程玩家，通过 `SoundCapture.directionOf` 包含显示名称、位置和方位信息（相对于本地玩家视角的偏航角/俯仰角 + 距离）。不同维度的玩家会标注其维度名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SoundCapture.directionOf](SoundCapture.java/directionOf.md) | 共享的方位格式化器 |
