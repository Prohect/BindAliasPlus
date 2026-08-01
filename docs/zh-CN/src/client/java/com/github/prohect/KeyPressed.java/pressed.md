# pressed 方法（src/client/java/com/github/prohect/KeyPressed.java）

## 语法

```java
public boolean pressed()
```

## 参数

_无。_

## 备注

按下状态的记录访问器 —— 按下按键/按下鼠标按钮为 `true`，松开按键/松开鼠标按钮为 `false`。`MinecraftClientMixin` 刻循环使用它来决定调用 `BindAliasKeyBinding` 中的按下别名还是松开别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [key](key.md) | 事件涉及的按键 |
| [BindAliasKeyBinding](../BindAliasKeyBinding.java/BindAliasKeyBinding.md) | 根据此标志分发的按下/松开别名对 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
