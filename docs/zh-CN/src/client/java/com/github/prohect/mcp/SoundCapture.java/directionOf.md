# directionOf 方法（src/client/java/com/github/prohect/mcp/SoundCapture.java）

## 语法

```java
static String directionOf(ClientPlayerEntity p, double dx, double dy, double dz)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `p` | `ClientPlayerEntity` | 本地玩家（监听者） |
| `dx` | `double` | X 增量：目标减玩家位置 |
| `dy` | `double` | Y 增量：目标减玩家位置 |
| `dz` | `double` | Z 增量：目标减玩家位置 |

## 返回值

两种形式之一的方向字符串：
- 贴身距离（< 0.31m）：`"here D.Dm"`
- 方向式：`"yaw±N pitch±N D.Dm"` — 目标**相对于玩家当前视角**的偏航角/俯仰角，各自四舍五入到最近的 20° 步长，加上 3D 距离

如果水平距离低于 0.5m，则省略偏航角分量（仅显示俯仰角）。在贴身距离下角度无意义，折叠为 `"here D.Dm"`。

## 备注

使用 Minecraft 的偏航角约定（0=南/+Z，90=西/-X，±180=北/-Z，-90=东/+X）和俯仰角约定（-90=上，0=水平，90=下）。计算目标的绝对偏航角/俯仰角，减去玩家当前旋转得到相对角度，归一化到 [-180, 180]，并四舍五入到最近的 20° 步长。`GameStateCollector.playersJson` 也用它格式化附近玩家的方向。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameStateCollector.playersJson](GameStateCollector.java/playersJson.md) | 另一个调用方 |
| [clamp](clamp.md) | 20° 步长取整 |
| [normalize180](normalize180.md) | 角度归一化 |
| [signed](signed.md) | 恒带符号的整数格式化 |
