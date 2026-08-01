# pinFocusedSlotTo14 方法（src/client/java/com/github/prohect/mixin/client/HandledScreenMixin.java）

## 语法

```java
@Inject(method = "getSlotAt", at = @At("RETURN"), cancellable = true)
private void pinFocusedSlotTo14(double mouseX, double mouseY, CallbackInfoReturnable<Slot> cir)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `mouseX` | `double` | 屏幕坐标中的鼠标 X 位置（未使用） |
| `mouseY` | `double` | 屏幕坐标中的鼠标 Y 位置（未使用） |
| `cir` | `CallbackInfoReturnable<Slot>` | 用于替换返回值的回调 |

## 备注

注入到 `HandledScreen#getSlotAt(double, double)` 的 `RETURN`。当 `FreeCursorAlias.freeCursor` 为 `false` 时立即返回（无操作），保持原版悬停行为不变。

当 `freeCursor` 为 `true` 时：
1. 通过 `(HandledScreen<?>) (Object) this` 将 `this` 转换为 `HandledScreen<?>`。
2. 遍历 `self.handler.slots`（`ScreenHandler`）中的所有槽位。
3. 找到 `index` 等于 `FORCED_HOVER_INDEX`（13）**且**其 `inventory` 是 `PlayerInventory` 实例的槽位——额外的检查确保被固定的槽位确实是玩家物品栏槽位，而不是恰好有索引 13 的容器槽位。
4. 调用 `cir.setReturnValue(slot)` 替换原版返回值。
5. 如果找不到匹配的槽位，则保持原版返回值不变（回退到正常悬停）。

此注入是 freeCursor 期间固定悬停槽位的唯一机制。通过在 `getSlotAt` 的返回点覆盖它，聚焦槽位的所有下游消费者（丢弃、交换、物品提示渲染）都会看到被固定的槽位，而无需单独的注入。使用返回点而非 `HEAD` 是因为原版方法先执行自己的槽位查找逻辑，这无害——mixin 只是事后替换结果。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [HandledScreenMixin](HandledScreenMixin.md) | 外层 mixin 类 |
| [FreeCursorAlias.freeCursor](../../../alias/builtinAlias/FreeCursorAlias.java/freeCursor.md) | 把关此注入的标志 |
| [MouseMixin.skipOsCursorGrab](../MouseMixin.java/skipOsCursorGrab.md) | freeCursor 期间抑制 OS 光标锁定 |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
