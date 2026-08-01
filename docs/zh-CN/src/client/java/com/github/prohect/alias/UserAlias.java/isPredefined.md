# isPredefined 方法（src/client/java/com/github/prohect/alias/UserAlias.java)

## 语法

```java
public boolean isPredefined()
```

## 返回值

如果此别名受保护、不能被新的 `alias` 定义覆盖，则返回 `true`，否则返回 `false`。

## 备注

预定义别名是使用 3 参构造函数 `UserAlias(args, fromCFG, predefined=true)` 创建的别名。它们通常由模组在初始化期间设置，以提供用户不应意外覆盖的默认行为。

`AliasAlias` 内置别名在覆盖现有别名之前检查此标志——如果 `isPredefined()` 返回 `true`，则拒绝覆盖。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [isFromCFG](isFromCFG.md) | 相关的跟踪标志 |
| [AliasAlias](builtinAlias/AliasAlias.java/AliasAlias.md) | 覆盖前检查此标志 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
