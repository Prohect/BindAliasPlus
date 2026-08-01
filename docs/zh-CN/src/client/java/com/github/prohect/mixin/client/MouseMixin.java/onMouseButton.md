# onMouseButton 方法（src/client/java/com/github/prohect/mixin/client/MouseMixin.java）

## 语法

```java
@Inject(at = @At("HEAD"), method = "onButton")
private void onMouseButton(long window, MouseButtonInfo button, int action, CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `window` | `long` | GLFW 窗口句柄；非 Minecraft 窗口的事件被忽略 |
| `button` | `net.minecraft.client.input.MouseButtonInfo` | 包含按键码和修饰键的鼠标按键信息 |
| `action` | `int` | GLFW 按键动作：`0` = 松开，`1` = 按下，`2` = 重复（忽略） |
| `ci` | `CallbackInfo` | 未使用的回调 |

## 备注

注入于 `MouseHandler#onButton(long, MouseButtonInfo, int)` 的 `HEAD`。使用与 `KeyBoardMixin.onKey` 相同的逻辑将鼠标按键事件路由到 `KEY_QUEUE`：

1. **窗口守卫**：忽略非 Minecraft 窗口的事件。
2. **文本输入守卫**：若 `Alias.isUnderTextInputScreen()` 为 true 则立即返回（输入文字时鼠标点击事件不应触发别名）。
3. **按键解析**：通过 `Type.MOUSE.getOrCreate()` 从 `button.button()` 创建 `InputConstants.Key`。
4. **锁定检查**：若 `LockAlias.LOCKED_PHYSICAL_KEYS` 包含此按键则返回。
5. **绑定查找**：检查 `BindAliasClient.BINDING_PLUS`。
6. **动作过滤**：action=2（重复）被丢弃；action=0 入队 `KeyPressed(key, false)`，action=1 入队 `KeyPressed(key, true)`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [KeyBoardMixin.onKey](../KeyBoardMixin.java/onKey.md) | 键盘按键的类似注入 |
| [Alias.isUnderTextInputScreen](../../../alias/Alias.java/isUnderTextInputScreen.md) | 此处使用的文本输入守卫 |
| [LockAlias.LOCKED_PHYSICAL_KEYS](../../../alias/builtinAlias/LockAlias.java/LOCKED_PHYSICAL_KEYS.md) | 门控输入的锁定集合 |
