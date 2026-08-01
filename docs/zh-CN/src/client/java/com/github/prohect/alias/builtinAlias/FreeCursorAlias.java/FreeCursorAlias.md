# FreeCursorAlias (src/client/java/com/github/prohect/alias/builtinAlias/FreeCursorAlias.java)

切换特殊"自由光标"模式的内置别名：游戏逻辑上表现得像鼠标已被捕获，而系统光标保持自由。继承 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.FreeCursorAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.FreeCursorAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinFreeCursor"`。用法：`+freeCursor` 启用，`-freeCursor` 禁用。

启用时，`MouseMixin` 读取静态 `freeCursor` 标志，它仅跳过 `lockCursor()` / `unlockCursor()` 内部的**操作系统级**光标捕获调用。逻辑上的 `cursorLocked` 标志仍正常打开，因此按住挖掘（`continueAttack`）和其他依赖鼠标的游戏逻辑继续工作，同时宿主光标在游戏窗口外保持可用。这主要是开发/测试的便利功能。

**禁用时的释放行为：** 从启用切换到禁用（`freeCursor` 仍为 true 时执行 `-freeCursor`）时，代码在 `freeCursor` 仍为 true 时调用 `Mouse.unlockCursor()` 来释放逻辑捕获。这使 `MouseMixin` 也跳过操作系统级 `unlockCursor` 调用——避免意外的物理光标跳动。一旦 `cursorLocked` 为 false，下一次真正的 `lockCursor()` 会正常重新应用操作系统级捕获。未捕获时多余的 `-freeCursor` 是无操作（由先前的状态检查保护）。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [freeCursor](freeCursor.md) | `public static boolean` | `MouseMixin` 读取的标志；为 true 时跳过操作系统级光标捕获 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | 读取 `freeCursor` 以跳过操作系统级捕获调用 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
