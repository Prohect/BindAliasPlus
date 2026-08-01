# FreeCursorAlias (src/client/java/com/github/prohect/alias/builtinAlias/FreeCursorAlias.java)

切换特殊"自由光标"模式的内置别名：该模式下系统光标保持自由，而游戏逻辑上表现得如同鼠标已被抓取。继承自 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.FreeCursorAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.FreeCursorAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinFreeCursor"`。用法：`+freeCursor` 启用，`-freeCursor` 禁用。

启用时，静态 `freeCursor` 标志由 `MouseMixin` 读取，它仅跳过 `grabMouse()` / `releaseMouse()` 中的**系统级**光标抓取调用。逻辑上的 `mouseGrabbed` 标志仍正常开启，因此按住挖掘（`continueAttack`）和其他依赖鼠标的游戏逻辑继续正常工作，而宿主光标在游戏窗口外保持可用。这主要是开发/测试便利功能。

**禁用时释放行为：** 从启用切换到禁用（在 `freeCursor` 为 true 时执行 `-freeCursor`）时，代码在 `freeCursor` 仍为 true 时调用 `MouseHandler.releaseMouse()` 以释放逻辑抓取。这使 `MouseMixin` 也跳过系统级 `releaseMouse` 调用——防止物理光标意外跳动。`mouseGrabbed` 变为 false 后，下一次真正的 `grabMouse()` 会正常重新应用系统级抓取。未抓取时多余的 `-freeCursor` 为空操作（由先前状态检查保护）。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [freeCursor](freeCursor.md) | `public static boolean` | 由 `MouseMixin` 读取的标志；为 true 时跳过系统级光标抓取 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [MouseMixin](../../../mixin/MouseMixin.java/MouseMixin.md) | 读取 `freeCursor` 以跳过系统级抓取调用 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
