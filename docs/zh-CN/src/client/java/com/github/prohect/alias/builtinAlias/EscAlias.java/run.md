# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/EscAlias.java）

处理退出（esc）动作：关闭当前界面，可选打开暂停菜单。

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
2. 如果当前有任意界面打开（`Alias.isUnderAnyScreen()`），则调用 `getCurrentScreen().close()` 关闭它，然后返回。此路径与 flag 值无关——关闭始终优先。
3. 如果没有界面打开且 `flag == 1` 且 `mc.player != null`（玩家在世界中）：
   - 通过 `mc.pauseGame(false)` 打开暂停菜单。`false` 参数表示暂停界面不被强制。
4. 如果 `flag == 0` 且没有界面打开：无操作（仅关闭模式没有可关闭的内容）。

`仅关闭` 模式（`esc\0`）用于脚本希望确保没有界面打开、同时避免意外打开暂停菜单的情况。`切换` 模式（`esc\1`）与原版 Esc 键行为一致。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [Alias.isUnderAnyScreen()](../../Alias.java/isUnderAnyScreen.md) | 界面检测辅助方法 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
