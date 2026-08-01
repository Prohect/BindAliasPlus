# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/AdvancementsAlias.java）

通过操纵原版进度按键绑定来处理 `+advancements`（按下）和 `-advancements`（松开）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.AdvancementsAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 按下（`+advancements`）为 `"1"`，松开（`-advancements`）为 `"0"` |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`。
2. **界面抑制（仅按下、仅文本输入界面）：** 如果 `flag` 为 true 且文本输入界面已打开，则立即返回。松开事件始终处理。
3. 获取 `MinecraftClient.getInstance().options.advancementsKey` 并调用 `setPressed(flag)`，按下时额外执行 `timesPressed++`。

进度键比较特殊：原版 `MinecraftClient` 通过 `advancementsKey.wasPressed()` 轮询它，进度界面在按键**按下后被松开**时打开——而不是按下时。`-advancements` 形式没有切换效果。（Yarn：`wasPressed()`；Mojang：`consumeClick()`）

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [PlayerListAlias.run()](../PlayerListAlias.java/run.md) | 类似的切换模式 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
