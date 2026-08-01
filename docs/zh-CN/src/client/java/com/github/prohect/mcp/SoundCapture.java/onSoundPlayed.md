# onSoundPlayed 方法（src/client/java/com/github/prohect/mcp/SoundCapture.java）

## 语法

```java
@Override
public void onSoundPlayed(SoundInstance sound, WeightedSoundSet soundSet, float range)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `sound` | `SoundInstance` | 正在播放的声音，提供空间位置（`getX/Y/Z`） |
| `soundSet` | `WeightedSoundSet` | 声音集定义，通过 `getSubtitle()` 提供字幕文本 |
| `range` | `float` | 此声音的可听范围（全局声音可能为 `Float.POSITIVE_INFINITY`） |

## 备注

`SoundInstanceListener#onSoundPlayed` 的实现——客户端 `SoundManager` 每当向监听者播放声音时调用的回调。这与原版字幕浮层使用的钩子相同，因此正好是那些会显示 HUD 字幕的声音会被上报。

**处理步骤：**

1. **字幕检查：** 调用 `soundSet.getSubtitle()`。如果为 `null`（声音没有字幕），立即返回——只捕获字幕可听到的声音。
2. **玩家检查：** 获取 `MinecraftClient.getInstance().player`。如果为 `null`（不在世界中），返回。
3. **可听性门槛：** 计算声音源到玩家的平方距离，跳过超出 `range` 的声音（与 `SubtitleOverlay.isAudibleFrom` 一致）。如果 `range` 为无穷大，所有声音都通过。
4. **方向格式化：** 调用 `directionOf(p, dx, dy, dz)` 计算相对于玩家视角的偏航角/俯仰角和距离，格式为 `"yaw±N pitch±N D.Dm"` 或 `"here D.Dm"`。
5. **channel 发布：** 通过 `GameChannels.postCoalescing` 将格式化消息 `"[client_tick:N] subtitle [direction]"` 发布到 `GameChannels.SOUND`。

**合并：** `postCoalescing` 对同一 tick 内到达的相同消息去重（例如多只僵尸同时呻吟），因此 SOUND channel 每 tick 最多上报每个不同的字幕+方向对一次。

**错误处理：** 整个方法体被静默吞掉异常的 try-catch 包裹——防止 MCP 声音捕获的任何失败破坏原版声音引擎。

26.x（Mojang）的等价物名为 `onPlaySound`，实现 `SoundEventListener#onPlaySound(SoundInstance, WeighedSoundEvents, float)`——重命名反映了 1.21.x 的 Yarn 映射，其中监听器接口及其方法都改了名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SoundCapture](SoundCapture.md) | 外层类 |
| [directionOf](directionOf.md) | 此方法调用的方向格式化器 |
| [GameChannels.postCoalescing](../../mcp/GameChannels.java/postCoalescing.md) | 带去重的 channel 发布 |
| [register](register.md) | 在声音管理器上注册此监听者 |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
