# KeyPressed（src/client/java/com/github/prohect/KeyPressed.java）

## 语法

```java
public final class com.github.prohect.KeyPressed extends java.lang.Record
```

## 静态初始化器

_无。_

## 备注

一个表示单个按键或鼠标按钮事件的 Java `record`——由 mixin（`KeyBoardMixin`、`MouseMixin`）推入 `BindAliasClient.KEY_QUEUE` 的不可变数据，每个 tick 由 `MinecraftClientMixin` 消费。

组件：
- `key` — 标识涉及的键盘按键或鼠标按钮的 `InputUtil.Key`。
- `pressed` — 按键按下/按钮按下为 `true`，按键松开/按钮松开为 `false`。

这是纯数据载体。这里没有逻辑——`MinecraftClientMixin` 中的 tick 循环在 `BINDING_PLUS` 中查找按键并调用相应的别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [KEY_QUEUE](../BindAliasClient.java/KEY_QUEUE.md) | 存储这些事件的 FIFO 队列 |
| [BINDING_PLUS](../BindAliasClient.java/BINDING_PLUS.md) | 用于分发事件的按键→别名注册表 |
| [BindAliasKeyBinding](../BindAliasKeyBinding.java/BindAliasKeyBinding.md) | 由按键事件触发的别名绑定 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
