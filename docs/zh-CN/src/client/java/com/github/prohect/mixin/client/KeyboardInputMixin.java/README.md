# KeyboardInputMixin

针对 `net.minecraft.client.player.KeyboardInput` 的 mixin。每刻排空模组的 `KEY_QUEUE`，并将排队的按键事件分发给对应的 `AliasWithoutArgs` 实例，在物理输入与别名执行之间架起桥梁。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [tick](tick.md) | `static void tick(CallbackInfo info)` | `KeyboardInput#tick()` 的 `HEAD` 处的 `@Inject` —— 排空 `KEY_QUEUE` 并分发 aliasWithoutArgs |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [KeyBoardMixin](../KeyBoardMixin.java/README.md) | 将键盘事件排入 `KEY_QUEUE` |
| [MouseMixin](../MouseMixin.java/README.md) | 将鼠标事件排入 `KEY_QUEUE` |
| [AliasWithoutArgs](../../../alias/AliasWithoutArgs.java/README.md) | 此处分发的别名类型 |
