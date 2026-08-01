# onMouseButton 方法（src/client/java/com/github/prohect/mixin/client/MouseMixin.java）

## 语法

```java
@Inject(at = @At("HEAD"), method = "onMouseButton")
private void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `window` | `long` | GLFW 窗口句柄；非 Minecraft 窗口的事件被忽略 |
| `button` | `int` | GLFW 鼠标按钮码 |
| `action` | `int` | GLFW 按钮动作：`0` = 松开，`1` = 按下，`2` = 重复（忽略） |
| `ci` | `CallbackInfo` | 未使用的回调 |

## 备注

注入到 `Mouse#onMouseButton(long, int, int, int)` 的 `HEAD`。以与 `KeyBoardMixin.onKey` 相同的逻辑将鼠标按钮事件路由到 `KEY_QUEUE`：（Yarn：`onMouseButton()`；Mojang：`onButton()`）

1. **窗口守卫**：忽略非 Minecraft 窗口的事件。
2. **文本输入守卫**：如果 `Alias.isUnderTextInputScreen()` 为 true，立即返回（输入文字时鼠标点击事件不应触发别名）。
3. **按键解析**：通过 `InputUtil.Type.MOUSE.createFromCode(button)` 从 `button` 创建 `InputUtil.Key`。
4. **锁定检查**：如果 `LockAlias.LOCKED_PHYSICAL_KEYS` 包含此按键则返回。
5. **绑定查找**：检查 `BindAliasClient.BINDING_PLUS`。
6. **动作过滤**：action=2（重复）被丢弃；action=0 入队 `KeyPressed(key, false)`，action=1 入队 `KeyPressed(key, true)`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [KeyBoardMixin.onKey](../KeyBoardMixin.java/onKey.md) | 键盘按键的类似注入 |
| [Alias.isUnderTextInputScreen](../../../alias/Alias.java/isUnderTextInputScreen.md) | 此处使用的文本输入守卫 |
| [LockAlias.LOCKED_PHYSICAL_KEYS](../../../alias/builtinAlias/LockAlias.java/LOCKED_PHYSICAL_KEYS.md) | 把关输入的锁定集合 |
