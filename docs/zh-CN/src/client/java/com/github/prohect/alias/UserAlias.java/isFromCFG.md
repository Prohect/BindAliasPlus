# isFromCFG 方法（src/client/java/com/github/prohect/alias/UserAlias.java）

## 语法

```java
public boolean isFromCFG()
```

## 返回值

如果此别名是从 CFG 文件加载的（自动加载），则返回 `true`；如果是在运行时创建的（通过 `alias` 命令）或属于内置别名，则返回 `false`。

## 备注

`unloadCFGAliases` 使用它来确定要移除哪些用户别名——只有 `fromCFG == true` 的会被卸载。用户创建的和预定义的别名会保留。

该标志在构造时通过 2 参或 3 参构造函数设置，或稍后通过 `setFromCFG(boolean)` 设置。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [setFromCFG](setFromCFG.md) | 此标志的设置方法 |
| [isPredefined](isPredefined.md) | 相关的保护标志 |
| [UnloadCFGAliasesAlias](builtinAlias/UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md) | 使用此标志的内置别名 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
