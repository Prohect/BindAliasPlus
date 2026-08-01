# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/EscAlias.java）

处理 Esc 动作：关闭当前界面，并可选地打开暂停菜单。

## 语法

```java
public com.github.prohect.alias.builtinAlias.EscAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | `"0"` 仅关闭，`"1"` 切换（关闭界面或打开暂停菜单） |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`（0 或 1）。
2. 若当前有任何界面打开（`Alias.isUnderAnyScreen()`），调用 `getCurrentScreen().onClose()` 将其关闭，然后返回。此路径无论 flag 值如何都会执行——关闭始终优先。
3. 若没有界面打开且 `flag == 1` 且 `mc.player != null`（玩家在世界中）：
   - 通过 `mc.pauseGame(false)` 打开暂停菜单。`false` 参数表示不强制打开暂停界面。
4. 若 `flag == 0` 且没有界面打开：不执行操作（仅关闭模式没有可关闭的界面）。

`仅关闭` 模式（`esc\0`）用于脚本希望确保没有界面打开、又不想冒险误开暂停菜单的场景。`切换` 模式（`esc\1`）匹配原版 Esc 键行为。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [Alias.isUnderAnyScreen()](../../Alias.java/isUnderAnyScreen.md) | 界面检测辅助方法 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
