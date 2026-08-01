# AdvancementsAlias

原版进度按键绑定（advancementsKey / L 键）的开关别名。继承 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 模式。进度界面在按键松开时打开（由 `MinecraftClient` 通过 `advancementsKey.wasPressed()` 轮询）。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除 `BuiltinAliasWithBooleanArgs.flag` 外无其他）_ | `boolean` | 继承：`+advancements` 为 true，`-advancements` 为 false |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `AdvancementsAlias run(String args)` | 按下或松开 `options.advancementsKey`；在文本输入界面上抑制按下 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [PlayerListAlias](../PlayerListAlias.java/PlayerListAlias.md) | 玩家列表切换 |
| [DebugOverlayAlias](../DebugOverlayAlias.java/DebugOverlayAlias.md) | 调试浮层切换 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
