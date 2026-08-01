# HandledScreenMixin

针对 `net.minecraft.client.gui.screen.ingame.HandledScreen` 的 mixin。当 `freeCursor` 激活时，强制 `getSlotAt` 返回索引 13（agent 槽位 14）处的玩家物品栏槽位，使悬停槽位固定而不受 OS 光标位置影响。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `FORCED_HOVER_INDEX` | `int`（static, private, final, `13`） | 映射到容器槽位 14 的 0 基玩家物品栏索引——固定的悬停目标 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [pinFocusedSlotTo14](pinFocusedSlotTo14.md) | `void pinFocusedSlotTo14(double mouseX, double mouseY, CallbackInfoReturnable<Slot> cir)` | `getSlotAt` 的 `RETURN` 处 `@Inject`——freeCursor 激活时用 `FORCED_HOVER_INDEX` 处的槽位替换返回值 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [FreeCursorAlias](../../../alias/builtinAlias/FreeCursorAlias.java/README.md) | `freeCursor` 标志的来源 |
| [MouseMixin](../MouseMixin.java/README.md) | freeCursor 期间抑制 OS 光标锁定和相机转动 |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
