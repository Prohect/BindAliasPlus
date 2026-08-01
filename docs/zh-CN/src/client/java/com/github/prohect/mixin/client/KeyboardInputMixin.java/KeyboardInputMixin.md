# KeyboardInputMixin（src/client/java/com/github/prohect/mixin/client/KeyboardInputMixin.java）

## 语法

```java
@Mixin(KeyboardInput.class)
public class com.github.prohect.mixin.client.KeyboardInputMixin
```

## 静态初始化

_无。_

## 备注

混合进 `net.minecraft.client.player.KeyboardInput`，将模组的按键事件队列注入移动输入处理管线。每刻，在原版处理物理按键状态之前，此 mixin 排空 `KEY_QUEUE`（`BindAliasClient.KEY_QUEUE`），并将任何排队的按键按下/松开事件分发给对应的 `AliasWithoutArgs` 实例。这是由 [`KeyBoardMixin`](../KeyBoardMixin.java/README.md) / [`MouseMixin`](../MouseMixin.java/README.md) 捕获的物理按键事件与别名执行系统之间的桥梁。

注入是 `static` 的，位于 `KeyboardInput#tick()` 的 `HEAD` —— 这确保由别名驱动的移动键（`+forward`、`+back`、`+left`、`+right`）在原版读取本帧键盘状态之前被应用，使别名对玩家移动拥有确定性的控制。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [tick](tick.md) | 排空 `KEY_QUEUE` 并分发别名的 `@Inject` |
| [KeyBoardMixin](../KeyBoardMixin.java/README.md) | 将物理按键事件路由进 `KEY_QUEUE` |
| [MouseMixin](../MouseMixin.java/README.md) | 将物理鼠标事件路由进 `KEY_QUEUE` |
| [BindAliasClient.KEY_QUEUE](../../../BindAliasClient.java/KEY_QUEUE.md) | 此处被排空的队列 |
| [BindAliasClient.BINDING_PLUS](../../../BindAliasClient.java/BINDING_PLUS.md) | 用于查找的按键→按键绑定映射 |
