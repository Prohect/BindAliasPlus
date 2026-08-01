# KeyPressed（src/client/java/com/github/prohect/KeyPressed.java）

## 语法

```java
public final class com.github.prohect.KeyPressed extends java.lang.Record
```

## 静态初始化器

_无。_

## 备注

表示单个按键或鼠标按键事件的 Java `record` —— 由 mixin（`KeyBoardMixin`、`MouseMixin`）将不可变数据推入 `BindAliasClient.KEY_QUEUE`，每刻由 `MinecraftClientMixin` 消费。

组件：
- `key` —— 标识涉及的键盘按键或鼠标按键的 `InputConstants.Key`。
- `pressed` —— 按下为 `true`，松开为 `false`。

这是纯数据载体。这里没有逻辑 —— `MinecraftClientMixin` 中的 tick 循环在 `BINDING_PLUS` 中查找按键并调用相应的别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [KEY_QUEUE](../BindAliasClient.java/KEY_QUEUE.md) | 存储这些事件的 FIFO 队列 |
| [BINDING_PLUS](../BindAliasClient.java/BINDING_PLUS.md) | 用于分发事件的 按键→别名 注册表 |
| [BindAliasKeyBinding](../BindAliasKeyBinding.java/BindAliasKeyBinding.md) | 按键事件触发的别名绑定 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
