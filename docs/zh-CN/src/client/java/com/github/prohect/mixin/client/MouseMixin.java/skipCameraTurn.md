# skipCameraTurn 方法（src/client/java/com/github/prohect/mixin/client/MouseMixin.java）

## 语法

```java
@Inject(at = @At("HEAD"), method = "turnPlayer", cancellable = true)
private void skipCameraTurn(CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `ci` | `CallbackInfo` | freeCursor 生效时被取消 |

## 备注

注入于 `MouseHandler#turnPlayer()` 的 `HEAD`。当 `FreeCursorAlias.freeCursor` 为 `true` 时完全取消该方法，使物理鼠标增量移动不旋转玩家的相机。这是必要的，因为 freeCursor 允许逻辑鼠标抓取保持生效（以保证挖掘连续性），否则鼠标移动到聚焦窗口上时会重新启用相机转向。freeCursor 期间相机控制完全通过 `yaw`/`pitch`/`setYaw`/`setPitch` 别名进行。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | 门控此注入的标志 |
| [skipOsCursorGrab](skipOsCursorGrab.md) | 抑制 OS 级光标抓取 |
| [YawAlias](../../../alias/builtinAlias/YawAlias.java/README.md) | 相机旋转的 `yaw` 别名 |
| [PitchAlias](../../../alias/builtinAlias/PitchAlias.java/README.md) | 相机旋转的 `pitch` 别名 |
