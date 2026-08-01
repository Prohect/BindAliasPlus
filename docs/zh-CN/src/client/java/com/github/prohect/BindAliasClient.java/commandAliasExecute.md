# commandAliasExecute 方法（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
private int commandAliasExecute(java.lang.String, java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `aliasName` | `String` | 要创建的别名名称 |
| `definition` | `String` | 别名链定义字符串（以 `\` 分隔参数的空格分隔调用） |

## 备注

创建或重新定义用户别名。守卫确保内置别名不能被覆盖：
1. 若 `aliasName` 匹配一个接受参数的别名（公开的或 `_notSuggested`），返回 `2`。
2. 若 `aliasName` 匹配一个不接受参数且不是 `UserAlias` 的别名，返回 `3`。
3. 若 `aliasName` 匹配一个 `predefined`（由 `onInitializeClient` 为 `+`/`-` 包装器创建的）的 `UserAlias`，返回 `3`。
4. 否则，将新的 `UserAlias(definition, fromAutoload)` 放入 `aliasesWithoutArgs` 并返回 `1`。

私有的 3 参数重载为 CFG 来源的别名增加了 `fromAutoload` 跟踪，使 `unloadCFGAliases` 可以清理它们。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UserAlias](../alias/UserAlias.java/UserAlias.md) | 存储在 `aliasesWithoutArgs` 中的用户定义别名 |
| [Alias](../alias/Alias.java/Alias.md) | 检查已有条目的注册映射 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
