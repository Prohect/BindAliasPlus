# AliasWithoutArgs（src/client/java/com/github/prohect/alias/AliasWithoutArgs.java）

## 语法

```java
public interface AliasWithoutArgs<T extends AliasWithoutArgs<T>> extends Alias<T>
```

仅按名称触发——调用时不传递显式参数——的别名的标记接口。内置的单动作别名（例如 `esc`、`toggleInventory`、`swapHand`）和用户定义的别名（`UserAlias`）都实现此接口。

提供 `putToAliasesWithoutArgs` / `putToAliasesWithoutArgs_notSuggested` 默认方法，将别名注册到全局 `Alias.aliasesWithoutArgs` 或 `Alias.aliasesWithoutArgs_notSuggested` 映射中。

## 备注

这是大多数别名的默认注册路径。按键事件驱动的别名（通过 `BindAliasKeyBinding` 绑定到键盘按键的别名）必须是 `AliasWithoutArgs`，因为按键按下无法提供参数。

`_notSuggested` 变体（`Alias.aliasesWithoutArgs_notSuggested`）用于不应出现在面向用户的命令建议中的内部别名。

另一个映射 `Alias.aliasesWithoutArgs_fromBindCommand` 存储由 `bind` 命令创建的别名——这些是为按键绑定查找而注册的用户别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) | 对应的另一类——接受显式参数的别名 |
| [BuiltinAliasWithoutArgs](BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | 提供无键 `putToAliasesWithoutArgs()` 重载的抽象基类 |
| [UserAlias](UserAlias.java/UserAlias.md) | 唯一非内置的 `AliasWithoutArgs` |
| [Alias](Alias.java/Alias.md) | 声明注册映射的根接口 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
