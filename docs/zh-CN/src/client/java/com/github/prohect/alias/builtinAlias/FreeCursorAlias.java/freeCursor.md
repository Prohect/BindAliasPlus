# freeCursor 字段（src/client/java/com/github/prohect/alias/builtinAlias/FreeCursorAlias.java）

控制是否抑制系统级光标抓取的静态布尔标志。由 `MouseMixin` 读取。

## 语法

```java
public static boolean freeCursor
```

## 备注

为 `true` 时，`MouseMixin` 拦截 `MouseHandler.grabMouse()` 和 `MouseHandler.releaseMouse()`，仅跳过系统级 GLFW 光标抓取/释放调用。逻辑上的 `mouseGrabbed` 标志仍正常切换，因此依赖鼠标状态（按住挖掘、相机旋转）的游戏逻辑继续正常工作。

**生命周期：** 由 `FreeCursorAlias.run()` 在 `+freeCursor` 或 `-freeCursor` 执行时设置。默认值为 `false`（正常的光标抓取行为）。

**读取方：**
- `MouseMixin` — 检查此标志以决定是否跳过系统级抓取调用
- `FreeCursorAlias.run()` — 读取当前值以保护 `-freeCursor` 切换

**线程安全：** 仅从游戏线程访问（通过别名执行和 mixin 注入点）；无需同步。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [FreeCursorAlias.run()](run.md) | 设置此标志 |
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | 读取此标志 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
