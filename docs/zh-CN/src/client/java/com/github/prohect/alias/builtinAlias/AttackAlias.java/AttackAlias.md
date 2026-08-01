# AttackAlias (src/client/java/com/github/prohect/alias/builtinAlias/AttackAlias.java)

模拟攻击（左键）按键绑定的内置别名。继承自 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.AttackAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.AttackAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinAttack"`。用法：`+attack` 按下，`-attack` 松开。文本输入界面（聊天界面、告示牌、书、命令方块）打开时，按下事件（`flag == true`）会被抑制——攻击键绝不会注入到输入的文本中。但与移动键不同，出于安全考虑，攻击键在**所有**界面上同样被屏蔽（通过 `BuiltinAliasWithBooleanArgs` 从 `UserAlias.run()` 继承的界面抑制以及 `addToScreenBlackList` 注册）：在 GUI 中攻击可能产生意外的副作用。

实现操纵 `Minecraft.options.keyAttack`：

- `setDown(flag)` — 按住或松开按键
- `clickCount++` — 按下时递增点击计数，使原版点击检测（轻击与按住）触发

无 `reapplyToGameKeyMapping()` 覆写——默认继承自 `BuiltinAliasWithBooleanArgs`，在 `flag` 为 true 时于界面切换后以 `"1"` 重新执行。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UseAlias](../UseAlias.java/UseAlias.md) | 右键的对应别名 |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 移动键（仅抑制文本输入界面） |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 开关别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
