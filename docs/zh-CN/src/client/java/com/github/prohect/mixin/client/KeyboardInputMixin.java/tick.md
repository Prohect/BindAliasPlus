# tick 方法（src/client/java/com/github/prohect/mixin/client/KeyboardInputMixin.java）

## 语法

```java
@Inject(at = @At("HEAD"), method = "tick")
private static void tick(CallbackInfo info)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `info` | `CallbackInfo` | 未使用的回调 |

## 备注

注入在 `KeyboardInput#tick()` 的 `HEAD` 处。通过 `while` 循环轮询来排空 `BindAliasClient.KEY_QUEUE`。对每个出队的 `KeyPressed` 记录：

1. 按按键码在 `BindAliasClient.BINDING_PLUS` 中查找对应的 `BindAliasKeyBinding`。
2. 解析别名名称：若 `pressed`，使用 `keyBindingPlus.aliasNameOnKeyPressed()`；否则使用 `keyBindingPlus.aliasNameOnKeyReleased()`。
3. 在 `Alias.aliasesWithoutArgs` 中查找 `AliasWithoutArgs` 实例，回退到 `Alias.aliasesWithoutArgs_fromBindCommand`。
4. 若找到，调用 `aliasWithoutArgs.run("")`。

这是一个 `static` 方法 —— mixin 的 `@Inject` 目标是 `static` 的 `KeyboardInput#tick()` 方法。此队列中预期只有 `AliasWithoutArgs` 别名；`args` 始终为空字符串。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [KeyBoardMixin.onKey](../KeyBoardMixin.java/onKey.md) | 将按键按下/松开事件排队 |
| [MouseMixin.onMouseButton](../MouseMixin.java/onMouseButton.md) | 将鼠标按键事件排队 |
| [BindAliasClient.KEY_QUEUE](../../../BindAliasClient.java/KEY_QUEUE.md) | 被排空的并发队列 |
| [Alias.aliasesWithoutArgs](../../../alias/Alias.java/aliasesWithoutArgs.md) | 主要别名注册表 |
| [Alias.aliasesWithoutArgs_fromBindCommand](../../../alias/Alias.java/aliasesWithoutArgs_fromBindCommand.md) | 回退注册表（通过 `bind` 命令创建的别名） |
