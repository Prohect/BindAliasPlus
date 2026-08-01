# KeyBoardMixin（src/client/java/com/github/prohect/mixin/client/KeyBoardMixin.java）

## 语法

```java
@Mixin(KeyboardHandler.class)
public class com.github.prohect.mixin.client.KeyBoardMixin
```

## 静态初始化

_无。_

## 备注

混合进 `net.minecraft.client.KeyboardHandler` 以拦截物理按键按下/松开事件。`keyPress` 的 `HEAD` 处的 `@Inject` 将每个按键按下（action=1）和按键松开（action=0）事件路由进模组的 `KEY_QUEUE`（`BindAliasClient.KEY_QUEUE`），但仅在以下条件满足时：

1. 窗口句柄与 Minecraft 窗口匹配（忽略其他操作系统窗口的事件），
2. 按键码映射到 `BINDING_PLUS` 中已注册的 `BindAliasKeyBinding`，
3. 该按键**未**被 `LockAlias.LOCKED_PHYSICAL_KEYS` 锁定。

按键重复事件（action=2）被明确忽略 —— 只捕获离散的按下/松开转换。此 mixin 是物理键盘输入进入别名系统的唯一入口点。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [onKey](onKey.md) | 将按键事件路由到 `KEY_QUEUE` 的 `@Inject` |
| [KeyboardInputMixin](../KeyboardInputMixin.java/README.md) | 排空 `KEY_QUEUE` 并分发别名的 mixin |
| [LockAlias.LOCKED_PHYSICAL_KEYS](../../../alias/builtinAlias/LockAlias.java/LOCKED_PHYSICAL_KEYS.md) | 此处被门控的锁定物理按键集合 |
| [MouseMixin](../MouseMixin.java/README.md) | 处理鼠标按键事件的同类 mixin |
