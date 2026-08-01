# SoundCapture

通过在客户端 `SoundManager` 上实现 `SoundEventListener` 来供给 `SOUND` channel。上报有字幕可听的声音及其精确方位信息（相对于听者的偏航角/俯仰角，四舍五入到 20° 步进，外加距离）。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `INSTANCE` | `SoundCapture`（静态，私有，单例） | 注册在声音管理器上的单例实例 |
| `DEG_STEP` | `double`（静态，私有，`20.0`） | 方位量化的步进角度（度） |

## 方法

**生命周期：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [register](register.md) | `static void register()` | 在客户端 `SoundManager` 上注册单例 |

**声音事件处理器：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [onPlaySound](onPlaySound.md) | `void onPlaySound(SoundInstance sound, WeighedSoundEvents event, float range)` | `SoundEventListener` 回调 —— 带方位发布到 `SOUND` channel |

**方位格式化（与 GameStateCollector 共享）：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [directionOf](directionOf.md) | `static String directionOf(LocalPlayer p, double dx, double dy, double dz)` | 3D 方位格式化器：`"yaw±N pitch±N D.Dm"` 或 `"here D.Dm"` |

**辅助方法：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [clamp](clamp.md) | `static int clamp(double deg)` | 将角度四舍五入到最近的 20° 步进 |
| [normalize180](normalize180.md) | `static double normalize180(double deg)` | 将角度归一化到 (-180, 180] |
| [signed](signed.md) | `static String signed(int v)` | 始终带符号的整数格式化 |
| [fmt1](fmt1.md) | `static String fmt1(double v)` | 1 位小数的 double 格式化 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels.SOUND](GameChannels.java/SOUND.md) | 目标 channel |
| [GameStateCollector.playersJson](GameStateCollector.java/playersJson.md) | 玩家格式化复用 `directionOf` |
