# AliasWithArgs（src/client/java/com/github/prohect/alias/AliasWithArgs.java）

## 语法

```java
public interface AliasWithArgs<T extends AliasWithArgs<T>> extends Alias<T>
```

接受参数的别名的标记接口。只有**内置**别名应实现此接口；用户定义的别名（`UserAlias`）始终实现 `AliasWithoutArgs`。

提供 `putToAliasesWithArgs` / `putToAliasesWithArgs_notSuggested` 默认方法，将别名注册到全局 `Alias.aliasesWithArgs` 或 `Alias.aliasesWithArgs_notSuggested` 映射中。

## 备注

`AliasWithArgs` 与 `AliasWithoutArgs` 的区别决定了别名位于哪个注册映射，以及 `UserAlias.run()` 如何查找它。执行期间，`UserAlias` 先检查 `withoutArgs` 映射，然后检查 `withArgs` 映射。如果在 `withArgs` 映射中找到别名，`AliasRecord` 中的 `args` 字符串会传给 `run(args)`。

`_notSuggested` 变体（`Alias.aliasesWithArgs_notSuggested`）用于不应出现在面向用户的命令建议中的内部别名（例如 `builtinDrop`、`builtinLock`）。

**约束**：用户别名不得实现此接口。它们始终实现 `AliasWithoutArgs`，将参数嵌入定义字符串中。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | 对应的另一类——无显式参数的别名 |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 提供无键 `putToAliasesWithArgs()` 重载的抽象基类 |
| [Alias](Alias.java/Alias.md) | 声明注册映射的根接口 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
