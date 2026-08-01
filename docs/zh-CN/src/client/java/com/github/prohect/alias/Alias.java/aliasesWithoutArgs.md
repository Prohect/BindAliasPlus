# aliasesWithoutArgs 字段（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static final HashMap<String, AliasWithoutArgs<?>> aliasesWithoutArgs
```

所有不接受参数且会向用户建议的别名的全局映射。键是别名名称（例如 `"esc"`、`"toggleInventory"`、`"swapHand"`）。由 `BuiltinAliasWithoutArgs.putToAliasesWithoutArgs()` 在客户端初始化期间填充，并在用户定义新的 `UserAlias` 时由 `AliasAlias` 填充。

## 备注

这是 `UserAlias.run()` 中别名链执行期间检查的**第一个**映射。如果在此找到别名，则调用其 `run("")`（`AliasWithoutArgs` 的空参数）。

**读取方**：`UserAlias.run()`、`KeyBoardMixin`（按键事件路由）、`bind` 命令（建议查找）。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
