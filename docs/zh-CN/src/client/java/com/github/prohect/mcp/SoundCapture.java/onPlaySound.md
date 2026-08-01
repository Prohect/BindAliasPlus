# onPlaySound 方法（src/client/java/com/github/prohect/mcp/SoundCapture.java）

## 语法

```java
@Override
public void onPlaySound(SoundInstance sound, WeighedSoundEvents soundEvent, float range)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `sound` | `SoundInstance` | 正在播放的声音，提供世界位置和其他属性 |
| `soundEvent` | `WeighedSoundEvents` | 声音事件定义，提供字幕文本 |
| `range` | `float` | 可听范围；超出此距离的声音被跳过 |

## 备注

`SoundEventListener` 回调。处理流程：

1. **字幕检查**：若 `soundEvent.getSubtitle()` 为 `null`，则该声音没有 HUD 字幕 → 立即返回。
2. **玩家检查**：若本地玩家为 null，返回。
3. **可听性检查**：计算玩家与声音源之间的 3D 距离；若 `range` 非无穷且距离超过它，返回（与原版字幕浮层行为一致）。
4. **发布**：将消息格式化为 `[client_tick:N] <subtitle> [<direction>]`，通过 `postCoalescing` 发布到 `GameChannels.SOUND`，以字幕文本作为合并键。

包裹在 try-catch 中，确保声音引擎的异常永远不会导致客户端崩溃。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameChannels.postCoalescing](GameChannels.java/postCoalescing.md) | 发布方法 |
| [directionOf](directionOf.md) | 方位格式化器 |
