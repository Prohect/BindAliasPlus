# onKey 方法（src/client/java/com/github/prohect/mixin/client/KeyBoardMixin.java）

## 语法

```java
@Inject(at = @At("HEAD"), method = "keyPress")
private void onKey(long window, int action, KeyEvent event, CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `window` | `long` | GLFW 窗口句柄；非 Minecraft 窗口的事件被忽略 |
| `action` | `int` | GLFW 按键动作：`0` = 松开，`1` = 按下，`2` = 重复（忽略） |
| `event` | `net.minecraft.client.input.KeyEvent` | 包含按键码和修饰键的按键事件 |
| `ci` | `CallbackInfo` | 未使用的回调 |

## 备注

注入到 `KeyboardHandler#keyPress(long, int, KeyEvent)` 的 `HEAD`。处理逻辑：

1. **窗口守卫**：如果 `window != MinecraftClient.getInstance().getWindow().handle()`，立即返回。
2. **按键解析**：从事件的按键码创建 `InputUtil.Key`（`Type.KEYSYM.getOrCreate(event.key())`）。
3. **锁定检查**：如果 `LockAlias.LOCKED_PHYSICAL_KEYS` 包含此按键，立即返回——被锁定的按键永远不会到达别名系统。
4. **绑定查找**：在 `BindAliasClient.BINDING_PLUS` 中查找匹配的 `BindAliasKeyBinding`。
5. **动作过滤**：action=2（重复）被静默丢弃；action=0 入队 `KeyPressed(key, false)`，action=1 入队 `KeyPressed(key, true)`。

实际别名执行稍后在同一 tick 内通过 [`KeyboardInputMixin.tick`](../KeyboardInputMixin.java/tick.md) 进行，它负责排空 `KEY_QUEUE`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [LockAlias.LOCKED_PHYSICAL_KEYS](../../../alias/builtinAlias/LockAlias.java/LOCKED_PHYSICAL_KEYS.md) | 把关按键处理的锁定集合 |
| [BindAliasClient.BINDING_PLUS](../../../BindAliasClient.java/BINDING_PLUS.md) | 按键→按键绑定查找注册表 |
| [KeyPressed](../../../KeyPressed.java/README.md) | 入队到 `KEY_QUEUE` 的记录 |
| [MouseMixin.onMouseButton](../MouseMixin.java/onMouseButton.md) | 鼠标按钮的类似注入 |
