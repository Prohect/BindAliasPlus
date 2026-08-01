# AdvancementsAlias

原版进度按键绑定（keyAdvancements / L 键）的开关别名。继承自 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 模式。进度界面在按键松开时打开（由 `Gui.consumeClick()` 轮询）。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除 `BuiltinAliasWithBooleanArgs.flag` 外无其他）_ | `boolean` | 继承自基类：`+advancements` 为 true，`-advancements` 为 false |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `AdvancementsAlias run(String args)` | 按下或松开 `options.keyAdvancements`；在文本输入界面下抑制按下事件 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [PlayerListAlias](../PlayerListAlias.java/PlayerListAlias.md) | 玩家列表切换 |
| [DebugOverlayAlias](../DebugOverlayAlias.java/DebugOverlayAlias.md) | 调试浮层切换 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
