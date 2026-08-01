# Alias（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public interface Alias<T extends Alias<T>>
```

别名执行引擎的核心抽象。每个命令——无论是内置的还是用户定义的——都是实现此接口的对象。该接口声明了全局注册映射、别名链解析工具、界面类型辅助方法，以及所有别名必须履行的 `run` 契约。

所有具体别名类型都实现 `AliasWithArgs` 或 `AliasWithoutArgs`，两者都扩展自 `Alias`。

## 备注

该接口承担三个角色：

1. **注册中心**：静态映射（`aliasesWithArgs`、`aliasesWithoutArgs`、`aliasesWithoutArgs_fromBindCommand`，以及 `_notSuggested` 变体）按名称存储每个别名。内置别名在 `BindAliasClient.onInitializeClient()` 期间注册自身。用户别名通过 `alias` 命令或 CFG 自动加载注册。`UserAlias.run()` 中的查找顺序：`aliasesWithoutArgs` → `aliasesWithoutArgs_notSuggested` → `aliasesWithArgs_notSuggested` → `aliasesWithArgs`。

2. **解析工具**：`getDefinitions(...)` 按 `divider4AliasDefinition`（空格 `' '`）拆分别名链字符串，并尊重双引号包裹的块。`getDefinitionSplits(...)` 按 `divider4AliasArgs`（反斜杠 `\`）拆分单个定义，同样识别引号。`getOppositeDefinition(...)` 翻转开关类别名的 `+` / `-` 前缀。

3. **界面守卫**：静态辅助方法查询 `BindAliasClient.currentScreen` 以决定某些别名是否应执行。`isUnderTextInputScreen()`（聊天界面、告示牌、书、命令方块）在用户输入文字时阻止按键输入类别名触发。`isUnderAnyScreen()` 在任何界面打开时阻止列入黑名单的别名触发，但松开（`"0"`）事件除外。

泛型类型参数 `<T extends Alias<T>>` 支持流畅的构建器式注册（例如 `new SomeAlias().putToAliasesWithArgs().addToScreenBlackList()`）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) | 接受参数的别名的标记子接口 |
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | 仅按名称触发的别名的标记子接口 |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 所有接受参数的内置别名的抽象基类 |
| [UserAlias](UserAlias.java/UserAlias.md) | 用户定义的别名链；主要执行器 |
| [builtinAlias](builtinAlias/README.md) | 具体的内置别名实现 |
| [BindAliasClient](BindAliasClient.java/BindAliasClient.md) | 客户端初始化时注册别名的位置 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
