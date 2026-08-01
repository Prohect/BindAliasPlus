# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/AttackAlias.java）

通过操纵原版攻击按键绑定来处理 `+attack`（按下）和 `-attack`（松开）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.AttackAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 按下（`+attack`）为 `"1"`，松开（`-attack`）为 `"0"`，由 `parseArgs(args)` 解析 |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`（`"1"` 为 true，`"0"` 为 false）。
2. **界面抑制（仅按下）：** 如果 `flag` 为 true 且文本输入界面（聊天界面、告示牌、书、命令方块）已打开，则立即返回——按下被丢弃，玩家输入时攻击键永远不会被注入。松开事件仍被处理，以免按键卡住。
3. 获取 `MinecraftClient.getInstance().options.attackKey` 并调用：
   - `setPressed(flag)` — 将绑定设置为按住（按下）或松开
   - `timesPressed++`（仅按下）— 递增点击计数，使 Minecraft 的 `wasPressed()` / 攻击点按逻辑触发（Yarn：`wasPressed()`；Mojang：`consumeClick()`）

**额外的界面抑制：** `AttackAlias` 在 `BindAliasClient` 中以 `addToScreenBlackList()` 注册，这使 `UserAlias.run()` 在**所有**界面——而不仅仅是文本输入界面——上抑制它。这层双重防护是出于安全考虑：攻击 / 使用动作在 3D 世界之外没有任何正当用途。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [Alias.isUnderTextInputScreen()](../../Alias.java/isUnderTextInputScreen.md) | 此处使用的界面检查 |
| [UseAlias.run()](../UseAlias.java/run.md) | 右键的相同模式 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
