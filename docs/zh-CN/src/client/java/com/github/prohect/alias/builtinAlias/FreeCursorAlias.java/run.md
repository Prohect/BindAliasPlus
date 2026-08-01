# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/FreeCursorAlias.java）

处理 `+freeCursor`（启用）和 `-freeCursor`（禁用），带细致的鼠标捕获状态管理。

## 语法

```java
public com.github.prohect.alias.builtinAlias.FreeCursorAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 启用（`+freeCursor`）为 `"1"`，禁用（`-freeCursor`）为 `"0"` |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **禁用切换（`!flag && freeCursor`）：** 从启用切换到禁用而 `freeCursor` 仍为 true 时：
   - 调用 `MinecraftClient.getInstance().mouse.unlockCursor()` 释放逻辑捕获。因为此时 `freeCursor` 仍为 true，`MouseMixin` 跳过操作系统级 `unlockCursor()` 调用——避免意外的物理光标跳动。一旦 `cursorLocked` 为 false，下一次真正的 `lockCursor()` 会正确重新应用操作系统级捕获。受到保护，因此未捕获时多余的 `-freeCursor` 是无操作。
3. 设置 `freeCursor = flag` —— `MouseMixin` 读取的静态标志。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [freeCursor](freeCursor.md) | 此方法切换的静态标志 |
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | 读取 `freeCursor` 以跳过操作系统级捕获调用 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
