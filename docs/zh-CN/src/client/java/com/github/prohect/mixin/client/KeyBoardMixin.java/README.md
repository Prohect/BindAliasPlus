# KeyBoardMixin

针对 `net.minecraft.client.KeyboardHandler` 的 mixin。拦截物理键盘按下/松开事件，按窗口、锁定状态和绑定注册进行过滤，然后将 `KeyPressed` 记录排入 `KEY_QUEUE`。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [onKey](onKey.md) | `void onKey(long window, int action, KeyEvent event, CallbackInfo ci)` | `keyPress` 的 `HEAD` 处的 `@Inject` —— 将有效的按键按下/松开事件排入 `KEY_QUEUE` |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [KeyboardInputMixin](../KeyboardInputMixin.java/README.md) | 排空 `KEY_QUEUE` 并分发别名的 mixin |
| [MouseMixin](../MouseMixin.java/README.md) | 处理鼠标按键事件的同类 mixin |
| [LockAlias](../../../alias/builtinAlias/LockAlias.java/README.md) | 提供门控按键事件的 `LOCKED_PHYSICAL_KEYS` 集合 |
