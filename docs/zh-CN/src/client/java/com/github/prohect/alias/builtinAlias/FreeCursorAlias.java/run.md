# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/FreeCursorAlias.java）

处理 `+freeCursor`（启用）和 `-freeCursor`（禁用），并仔细管理鼠标抓取状态。

## 语法

```java
public com.github.prohect.alias.builtinAlias.FreeCursorAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | `"1"` 表示启用（`+freeCursor`），`"0"` 表示禁用（`-freeCursor`） |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **禁用切换（`!flag && freeCursor`）：** 在 `freeCursor` 仍为 true 时从启用切换到禁用：
   - 调用 `Minecraft.mouseHandler.releaseMouse()` 释放逻辑抓取。因为此时 `freeCursor` 仍为 true，`MouseMixin` 会跳过系统级 `releaseMouse()` 调用——避免物理光标意外跳动。`mouseGrabbed` 变为 false 后，下一次真正的 `grabMouse()` 会正确重新应用系统级抓取。受保护逻辑保证未抓取时多余的 `-freeCursor` 为空操作。
3. 设置 `freeCursor = flag` —— 这是 `MouseMixin` 读取的静态标志。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [freeCursor](freeCursor.md) | 由此方法切换的静态标志 |
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | 读取 `freeCursor` 以跳过系统级抓取调用 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
