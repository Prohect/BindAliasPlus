# AliasRecord（src/client/java/com/github/prohect/alias/AliasRecord.java）

## 语法

```java
public record AliasRecord(@NotNull String args, @NotNull String aliasName)
```

保存单次别名调用的不可变 record：别名名称及其参数字符串。`UserAlias` 用它存储解析后的标记队列（`ArrayDeque<AliasRecord> aliases`），`WaitAlias` 用它捕获别名链延迟执行的剩余部分。

## 备注

这是一个标准的 Java `record`，自动提供 `equals`、`hashCode`、`toString` 和组件访问器。组件为：

- **`aliasName`** —— 在全局别名映射（`aliasesWithoutArgs`、`aliasesWithArgs` 等）中查找的名称。
- **`args`** —— 参数字符串（已按 `Alias.divider4AliasArgs` 拆分），无参数别名为空字符串。只有内置 `AliasWithArgs` 实例会使用此值；对于 `AliasWithoutArgs`，它始终为 `""`。

当 `UserAlias.decodeArgs2Alias()` 解析像 `slot\3` 这样的定义时，会生成 `new AliasRecord("3", "slot")`。对于像 `esc` 这样的无参数别名，会生成 `new AliasRecord("", "esc")`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UserAlias](UserAlias.java/UserAlias.md) | 将定义字符串解码为 `AliasRecord` 队列 |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md) | 将延迟的别名存储为 `AliasRecord` 以便延迟执行 |
| [Alias](Alias.java/Alias.md) | 根接口——定义重建链时使用的参数分隔符 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
