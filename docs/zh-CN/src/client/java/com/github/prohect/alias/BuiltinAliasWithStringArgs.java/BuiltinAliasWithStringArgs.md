# BuiltinAliasWithStringArgs（src/client/java/com/github/prohect/alias/BuiltinAliasWithStringArgs.java）

## 语法

```java
public abstract class BuiltinAliasWithStringArgs<T extends BuiltinAliasWithStringArgs<T>> extends BuiltinAliasWithArgs<T>
```

以自由形式字符串作为参数的内置别名的抽象基类。将默认的别名定义分隔符从 `' '`（空格）覆盖为 `;`（分号），因为这些别名的字符串参数本身可能包含空格。

## 备注

`divider4AliasDefinition` 字段在此被重新声明为 `';'`。这会影响 `UserAlias` 在通过 `WaitAlias` 延迟时重建别名链的方式——`alias`、`bind` 和 `unbind` 等别名在其定义字符串中使用此分号分隔符，从而使包含空格的参数不会破坏链的解析。

**具体子类**：`SayAlias`（`say\"text"`）、`BindAlias`（`bind\key\alias`）、`SendCommandAlias`（`sendCommand\cmd`）、`LogAlias`（`log\text`）、`LocalSayAlias`（`localSay\text`）、`ReapplyAlias`（`reapply\action`）、`ReloadCFGAlias`（`reloadCFG`）、`AliasAlias`（`alias\name;definition`）、`RunAlias`（`runAlias\name`）、`ApplyRecipeAlias`（`applyRecipe\query`）。

**没有 `parseArgs` 覆盖**：与数字 / 布尔子类不同，`BuiltinAliasWithStringArgs` 不提供 `parseArgs` 方法。每个具体子类在 `run()` 中内联处理其参数解析。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 父类——注册与 builtinAliasName |
| [Alias.divider4AliasDefinition](Alias.java/divider4AliasDefinition.md) | 此子类覆盖的默认分隔符（`' '`） |
| [builtinAlias](builtinAlias/README.md) | 具体的字符串参数实现 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
