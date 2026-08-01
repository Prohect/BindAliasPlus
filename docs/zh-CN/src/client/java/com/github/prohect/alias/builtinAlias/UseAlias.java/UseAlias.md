# UseAlias (src/client/java/com/github/prohect/alias/builtinAlias/UseAlias.java)

模拟按住/松开使用/物品键（右键）的开关别名（`+use` / `-use`）。直接继承 `BuiltinAliasWithArgs`（而**不是** `BuiltinAliasWithBooleanArgs`），使其进行手动布尔解析。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.UseAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.UseAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `builtinUse`（内部，通过 `+use` / `-use` 暴露）。

**行为：**
- `+use`（args="1"）：按下原版 `useKey` 按键绑定（右键），使玩家使用手持物品或与目标方块交互。递增 `timesPressed` 以产生初始按下事件。
- `-use`（args="0"）：松开 `useKey` 按键绑定。

**为什么直接继承 BuiltinAliasWithArgs：** 与其他使用 `parseArgs()` 的 BooleanArgs 别名不同，`UseAlias` 手动对 args 字符串进行 switch（`"0"` → false，`"1"` → true）。这是代码库中的一个特例——大多数其他开关别名使用标准的 `parseArgs(args)` → `this.flag` 模式。

**界面抑制：** 当 `Alias.isUnderTextInputScreen()` 返回 true 时，按下事件被取消。此外，MCP 系统中 `+use` 和 `+attack` 有内置保护，防止在界面切换时绕过原版 `releaseAll()`——它们在**所有**界面上都被完全抑制，而不仅仅是文本输入界面。松开事件从不被抑制。

**点按与按住语义：**
- 点按（快速按下 + 松开）：放置方块或对方块/实体使用物品。
- 按住（持续按下）：持续使用手持物品（吃食物、拉弓、举盾格挡）。

**重新应用行为：** `+use` 和 `+attack` 在 `ReapplyAlias.SUPPORTED_ACTIONS` 中被引用为 `"use"`。然而，出于安全考虑，MCP 系统的内置保护阻止它们在界面切换后自动重新应用。

**错误处理：** 无效参数（既不是 "0" 也不是 "1"）记录警告但不改变按键状态。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AttackAlias](../AttackAlias.java/AttackAlias.md) | 左键（攻击/破坏）对应实现 |
| [ForwardAlias](../ForwardAlias.java/ForwardAlias.md) | 移动键（另一个 BooleanArgs 别名） |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | 界面切换后重新应用被按住的按键 |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 直接基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
