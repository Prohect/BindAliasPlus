# SoundCapture

通过在客户端 `SoundManager` 上实现 `SoundInstanceListener` 来为 `SOUND` channel 提供数据。上报带精确方向信息（相对于监听者的偏航角/俯仰角，四舍五入到 20° 步长，外加距离）的、字幕可听到的声音。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `INSTANCE` | `SoundCapture`（static, private, 单例） | 注册在声音管理器上的单例实例 |
| `DEG_STEP` | `double`（static, private, `20.0`） | 方向量化步长（度） |

## 方法

**生命周期：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [register](register.md) | `static void register()` | 在客户端 `SoundManager` 上注册单例 |

**声音事件处理器：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [onSoundPlayed](onSoundPlayed.md) | `void onSoundPlayed(SoundInstance sound, WeightedSoundSet soundSet, float range)` | `SoundInstanceListener` 回调——带方向发布到 `SOUND` channel |

**方向格式化（与 GameStateCollector 共享）：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [directionOf](directionOf.md) | `static String directionOf(ClientPlayerEntity p, double dx, double dy, double dz)` | 3D 方向格式化器：`"yaw±N pitch±N D.Dm"` 或 `"here D.Dm"` |

**辅助方法：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [clamp](clamp.md) | `static int clamp(double deg)` | 将角度取整到最近的 20° 步长 |
| [normalize180](normalize180.md) | `static double normalize180(double deg)` | 将角度归一化到 (-180, 180] |
| [signed](signed.md) | `static String signed(int v)` | 恒带符号的整数格式化 |
| [fmt1](fmt1.md) | `static String fmt1(double v)` | 1 位小数的 double 格式化 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels.SOUND](GameChannels.java/SOUND.md) | 目标 channel |
| [GameStateCollector.playersJson](GameStateCollector.java/playersJson.md) | 玩家格式化复用 `directionOf` |
