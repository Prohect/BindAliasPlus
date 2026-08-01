# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/AttackAlias.java）

通过操纵原版攻击按键绑定来处理 `+attack`（按下）和 `-attack`（松开）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.AttackAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | `"1"` 表示按下（`+attack`），`"0"` 表示松开（`-attack`），由 `parseArgs(args)` 解析 |

## 备注

1. 调用 `parseArgs(args)` 设置 `this.flag`（`"1"` 为 true，`"0"` 为 false）。
2. **界面抑制（仅按下）：** 若 `flag` 为 true 且文本输入界面（聊天界面、告示牌、书、命令方块）打开，则立即返回——按下事件被丢弃，玩家输入时攻击键绝不会被注入。松开事件仍会处理，以免按键卡住。
3. 获取 `Minecraft.options.keyAttack` 并调用：
   - `setDown(flag)` — 将绑定设为按住（按下）或松开
   - `clickCount++`（仅按下）— 递增点击计数，使 Minecraft 的 `consumeClick()` / 攻击轻击逻辑触发

**额外的界面抑制：** `AttackAlias` 在 `BindAliasClient` 中通过 `addToScreenBlackList()` 注册，这会使 `UserAlias.run()` 在**所有**界面（而不仅是文本输入界面）上抑制它。这种双重防护是有意为之的安全措施：攻击 / 使用动作在 3D 世界之外没有正当用途。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [Alias.isUnderTextInputScreen()](../../Alias.java/isUnderTextInputScreen.md) | 此处使用的界面检查 |
| [UseAlias.run()](../UseAlias.java/run.md) | 右键的相同模式 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
