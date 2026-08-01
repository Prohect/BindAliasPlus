# normalize180 方法（src/client/java/com/github/prohect/mcp/SoundCapture.java）

## 语法

```java
private static double normalize180(double deg)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `deg` | `double` | 要归一化的角度（度） |

## 返回值

包裹到 (-180, 180] 范围内的角度。

## 备注

先取 `deg % 360.0`，再把 > 180 或 ≤ -180 的值调整进目标范围，从而把角度归一化到 (-180, 180]。在取整前用于相对偏航角/俯仰角。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [directionOf](directionOf.md) | 调用方 |
| [clamp](clamp.md) | 后处理步骤 |
