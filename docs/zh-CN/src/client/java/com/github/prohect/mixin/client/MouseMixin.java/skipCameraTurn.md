# skipCameraTurn 方法（src/client/java/com/github/prohect/mixin/client/MouseMixin.java）

## 语法

```java
@Inject(at = @At("HEAD"), method = "updateMouse", cancellable = true)
private void skipCameraTurn(CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `ci` | `CallbackInfo` | freeCursor 激活时取消 |

## 备注

注入到 `Mouse#updateMouse()` 的 `HEAD`。当 `FreeCursorAlias.freeCursor` 为 `true` 时，完全取消该方法，使物理鼠标增量移动不会旋转玩家的相机。这是必要的，因为 freeCursor 允许逻辑鼠标锁定保持生效（以保持挖掘连续性），否则当鼠标移到聚焦窗口上时会重新启用相机转动。freeCursor 期间的相机控制完全通过 `yaw`/`pitch`/`setYaw`/`setPitch` 别名进行。（Yarn：`updateMouse()`；Mojang：`turnPlayer()`）

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | 把关此注入的标志 |
| [skipOsCursorGrab](skipOsCursorGrab.md) | 抑制 OS 级别光标锁定 |
| [YawAlias](../../../alias/builtinAlias/YawAlias.java/README.md) | 用于相机旋转的 `yaw` 别名 |
| [PitchAlias](../../../alias/builtinAlias/PitchAlias.java/README.md) | 用于相机旋转的 `pitch` 别名 |
