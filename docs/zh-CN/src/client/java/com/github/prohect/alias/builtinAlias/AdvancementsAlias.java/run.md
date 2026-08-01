# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/AdvancementsAlias.java）

通过操纵原版进度按键绑定来处理 `+advancements`（按下）和 `-advancements`（松开）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.AdvancementsAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | `"1"` 表示按下（`+advancements`），`"0"` 表示松开（`-advancements`） |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **界面抑制（仅按下、仅文本输入界面）：** 若 `flag` 为 true 且文本输入界面打开，则立即返回。松开事件始终处理。
3. 获取 `Minecraft.options.keyAdvancements`，按下时调用 `setDown(flag)` 并执行 `clickCount++`。

进度按键较为特殊：原版 `Gui.java` 通过 `consumeClick()` 轮询它，进度界面在按键按下后**松开**时打开——而非按下时。`-advancements` 形式无切换效果。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [PlayerListAlias.run()](../PlayerListAlias.java/run.md) | 类似的切换模式 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
