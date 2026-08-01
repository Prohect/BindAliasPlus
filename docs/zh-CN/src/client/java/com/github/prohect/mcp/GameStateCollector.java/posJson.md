# posJson 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
private static String posJson(ClientPlayerEntity p)
```

## 返回值

JSON 对象字符串：`{"x":...,"y":...,"z":...,"yaw":...,"pitch":...}`，坐标保留 1 位小数，角度保留 2 位小数。

## 备注

将玩家的位置（`getX()`、`getY()`、`getZ()`）和旋转（`getYRot()`、`getXRot()`）格式化为 JSON 对象。坐标使用 `fmt1`，角度使用 `fmt2`。玩家为 null 时返回 `"null"`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [fmt1](fmt1.md) | 坐标格式化（1 位小数） |
| [fmt2](fmt2.md) | 角度格式化（2 位小数） |
