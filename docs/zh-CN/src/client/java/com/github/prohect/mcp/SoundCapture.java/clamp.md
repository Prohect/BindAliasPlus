# clamp 方法（src/client/java/com/github/prohect/mcp/SoundCapture.java）

## 语法

```java
private static int clamp(double deg)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `deg` | `double` | 要取整的角度（度） |

## 返回值

取整到最近 20° 步进的角度（例如 15 → 20，-25 → -20，42 → 40）。

## 备注

把角度取整到 `DEG_STEP`（20°）的最近倍数。刻意取粗——耳朵不是量角器，粗步进产生稳定可读的方位输出。返回 `int`，因为结果总是 `DEG_STEP` 的倍数。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [directionOf](directionOf.md) | 调用方 |
| [normalize180](normalize180.md) | 预处理步骤 |
