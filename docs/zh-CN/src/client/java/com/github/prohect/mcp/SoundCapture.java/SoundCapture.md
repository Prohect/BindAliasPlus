# SoundCapture（src/client/java/com/github/prohect/mcp/SoundCapture.java）

## 语法

```java
public final class SoundCapture implements net.minecraft.client.sounds.SoundEventListener
```

## 静态初始化器

_无。_

## 备注

供给 [`GameChannels.SOUND`](GameChannels.java/SOUND.md) channel。实现 `SoundEventListener` 并注册在客户端 `SoundManager` 上 —— 与原版字幕浮层使用的钩子相同，因此上报的正是会显示 HUD 字幕的声音。

消息格式：`[client_tick:N] SoundName [yaw±N pitch±N D.Dm]`，例如 `[client_tick:123] Zombie groans [yaw-40 pitch+20 4.2m]`。方位是声音源**相对于听者听到声音那一刻视角**的偏航角/俯仰角，四舍五入到 20° 步进（刻意粗略 —— 耳朵不是量角器）。听者自身位置处的声音折叠为 `here D.Dm`。相同声音的重复通过 `GameChannels.postCoalescing` 合并。

`directionOf` 方法也被 `GameStateCollector.playersJson` 复用于玩家的方位格式化。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels.SOUND](GameChannels.java/SOUND.md) | 目标 channel |
| [GameChannels.postCoalescing](GameChannels.java/postCoalescing.md) | 重复声音的合并发布 |
| [register](register.md) | 在声音管理器上注册 |
| [onPlaySound](onPlaySound.md) | 事件监听器回调 |
| [directionOf](directionOf.md) | 3D 方位格式化器（`playersJson` 也使用） |
