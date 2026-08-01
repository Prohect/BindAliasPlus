# AttackAlias (src/client/java/com/github/prohect/alias/builtinAlias/AttackAlias.java)

模拟攻击（左键）按键绑定的内置别名。继承 `BuiltinAliasWithBooleanArgs` 的 `+name`/`-name` 开关模式。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.AttackAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.AttackAlias>
```

## 静态初始化

_无。_

## 备注

注册名为 `"builtinAttack"`。用法：`+attack` 按下，`-attack` 松开。文本输入界面（聊天界面、告示牌、书、命令方块）打开时按下事件（`flag == true`）被抑制——攻击键永远不会注入到输入的文本中。然而，与移动键不同，出于安全考虑，攻击键在**所有**界面上也被阻止（通过 `BuiltinAliasWithBooleanArgs` 从 `UserAlias.run()` 继承的界面抑制以及 `addToScreenBlackList` 注册）：在 GUI 中攻击可能产生意外的副作用。

实现操纵 `MinecraftClient.getInstance().options.attackKey`：

- `setPressed(flag)` — 按住或松开按键
- `timesPressed++` — 按下时递增点击计数，使原版点击检测（点按 vs. 按住）触发

无 `reapplyToGameKeyMapping()` 重写——`BuiltinAliasWithBooleanArgs` 继承的默认实现在界面切换后当 `flag` 为 true 时以 `"1"` 重新执行。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UseAlias](../UseAlias.java/UseAlias.md) | 右键的对应实现 |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 移动键（仅抑制文本输入） |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 开关别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
