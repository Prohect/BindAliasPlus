# overrideIsMouseGrabbed 方法（src/client/java/com/github/prohect/mixin/client/MouseMixin.java）

## 语法

```java
@Inject(method = "isMouseGrabbed", at = @At("RETURN"), cancellable = true)
private void overrideIsMouseGrabbed(CallbackInfoReturnable<Boolean> cir)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `cir` | `CallbackInfoReturnable<Boolean>` | 返回值覆盖；freeCursor 生效时设为 `true` |

## 备注

注入于 `MouseHandler#isMouseGrabbed()` 的 `RETURN`。当 `FreeCursorAlias.freeCursor` 为 `true` 时，通过 `cir.setReturnValue(true)` 将返回值覆盖为 `true`。原版 `Minecraft#handleKeybinds` 用 `isMouseGrabbed()` 门控 `continueAttack`（按住挖掘）。界面打开并调用 `releaseMouse()` 后，逻辑抓取可能为 `false`，这会中断挖掘。由于 OS 级抓取已被抑制（参见 `skipOsCursorGrab`）而逻辑抓取未被抑制，此覆盖确保挖掘像光标仍被抓住一样继续进行。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | 门控此注入的标志 |
| [skipOsCursorGrab](skipOsCursorGrab.md) | 抑制 OS 级光标抓取 |
