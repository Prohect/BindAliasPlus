# AdvancementsAlias (src/client/java/com/github/prohect/alias/builtinAlias/AdvancementsAlias.java)

打开或关闭进度界面的内置别名（默认：L 键）。继承自 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.AdvancementsAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.AdvancementsAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinAdvancements"`。用法：`+advancements` 打开，`-advancements`（无切换效果——`-advancements` 不做任何事）。与其他开关别名不同，该按键通过原版 `Gui.java` 中的 `consumeClick()` 轮询——界面在按键**松开**时打开，而非按下时。

实现操纵 `Minecraft.options.keyAdvancements`：

- `setDown(flag)` — 按住或松开按键
- `clickCount++` — 按下时递增点击计数，使 `consumeClick()` 触发

文本输入界面打开时，按下事件会被抑制。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [PlayerListAlias](../PlayerListAlias.java/PlayerListAlias.md) | 玩家列表浮层的类似切换模式 |
| [DebugOverlayAlias](../DebugOverlayAlias.java/DebugOverlayAlias.md) | 切换调试浮层 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
