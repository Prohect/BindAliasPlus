# BuiltinAliasWithBooleanArgs（src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java）

## 语法

```java
public abstract class BuiltinAliasWithBooleanArgs<T extends BuiltinAliasWithBooleanArgs<T>> extends BuiltinAliasWithArgs<T>
```

作为双态开关工作的内置别名的抽象基类——响应 `+name`（按下 / 启用）和 `-name`（松开 / 禁用）。所有移动键、动作键和切换键（`+attack`、`+use`、`+forward`、`+back`、`+left`、`+right`、`+jump`、`+sneak`、`+sprint`、`+drop`、`+playerList`、`+advancements`、`+silent`、`+freeCursor`）都扩展此类。

## 备注

`flag` 字段存储当前的布尔状态。`parseArgs(args)` 根据 `"0"`（关 / 松开）或 `"1"`（开 / 按下）设置它。无效参数通过 `BindAliasClient.LOGGER` 记录警告。

**按下 / 松开行为**：
- `run("1")` —— 按键被按下 / 按住；具体子类注入到原版按键映射。
- `run("0")` —— 按键被松开；具体子类松开原版按键映射。

**界面切换后重新应用**：界面关闭后会调用 `reapplyToGameKeyMapping()`（由 `reapply` 别名触发）。如果 `flag` 为 `true`，则以 `"1"` 重新运行，将按住的按键状态重新同步到游戏的按键映射中。这抵消了 Minecraft 在 `setScreen()` 时的 `releaseAll()` 行为。

**界面抑制**：大多数布尔别名在 `run()` 开头检查 `Alias.isUnderTextInputScreen()`，以避免在用户于聊天界面、告示牌、书或命令方块界面中输入文字时注入按键按下。有些还会为动作仍应生效的非文本界面检查 `Alias.isUnderAnyScreen()`。

具体子类通常在 `run()` 开头调用 `parseArgs(args)`，然后根据 `this.flag` 执行操作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 父类——注册与 builtinAliasName |
| [Alias.isUnderTextInputScreen](Alias.java/isUnderTextInputScreen.md) | 布尔参数别名使用的界面守卫 |
| [reapply](builtinAlias/ReapplyAlias.java/ReapplyAlias.md) | 在所有布尔别名上触发 `reapplyToGameKeyMapping()` |
| [builtinAlias](builtinAlias/README.md) | 所有 +attack、+use、+forward、... 实现 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
