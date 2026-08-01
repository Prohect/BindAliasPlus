# overrideIsCursorLocked 方法（src/client/java/com/github/prohect/mixin/client/MouseMixin.java）

## 语法

```java
@Inject(method = "isCursorLocked", at = @At("RETURN"), cancellable = true)
private void overrideIsCursorLocked(CallbackInfoReturnable<Boolean> cir)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `cir` | `CallbackInfoReturnable<Boolean>` | 用于覆盖返回值的回调 |

## 备注

注入到 `Mouse#isCursorLocked()` 的 `RETURN`。当 `FreeCursorAlias.freeCursor` 为 `true` 时，通过 `cir.setReturnValue(true)` 强制返回值为 `true`。

**目的：** `isCursorLocked` 把关 `MinecraftClient#handleInputEvents` 中的按住挖掘（`handleBlockBreaking`）。freeCursor 激活期间，逻辑锁定可能为 `false`——例如界面打开并调用 `unlockCursor()` 后，原版光标锁定状态被释放。然而，agent 仍然需要挖掘像光标已锁定那样继续。通过让 `isCursorLocked` 在 freeCursor 期间始终返回 `true`，挖掘守卫被绕过，`+attack` 继续工作。

这是 `MouseMixin` 中三个 freeCursor 支持注入之一：
- `skipOsCursorGrab`：取消 OS 级别 `glfwSetInputMode` 调用，使宿主光标保持自由。
- `skipCameraTurn`：取消 `updateMouse`，防止鼠标增量转动相机。
- `overrideIsCursorLocked`（本方法）：确保逻辑锁定状态永远不会把关挖掘。

当 `freeCursor` 为 `false` 时，注入不做修改直接返回，原版 `isCursorLocked` 行为不变。

26.x（Mojang）的等价物名为 `overrideIsMouseGrabbed`，针对 `Mouse#isMouseGrabbed()`——重命名反映了 Yarn 映射中该方法在 `Mouse` 类上名为 `isCursorLocked`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [MouseMixin](MouseMixin.md) | 外层 mixin 类 |
| [skipOsCursorGrab](skipOsCursorGrab.md) | freeCursor 期间抑制 OS 级别光标锁定 |
| [skipCameraTurn](skipCameraTurn.md) | freeCursor 期间抑制鼠标增量引起的相机转动 |
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | 把关此注入的标志 |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
