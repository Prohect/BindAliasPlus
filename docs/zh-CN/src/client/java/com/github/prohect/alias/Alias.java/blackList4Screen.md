# blackList4Screen 字段（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static final List<Alias<?>> blackList4Screen
```

任何界面打开时被**抑制**的别名列表（松开 / `"0"` 事件除外）。在注册期间调用 `addToScreenBlackList()` 即可将别名添加到此列表。

## 备注

在 `UserAlias.run()` 和 `runInternal()` 中检查：如果内置别名是 `BuiltinAliasWithArgs` 的实例**并且**在此列表中，则仅当 `!isUnderAnyScreen()` 或其参数为 `"0"`（松开）时才执行。这可以防止按住按键（`+attack`、`+use` 等）在玩家处于物品栏或其他界面时影响游戏，同时仍允许松开事件传播，从而确保按键不会卡住。

在 `BindAliasClient.onInitializeClient()` 期间通过构建器模式填充（例如 `new AttackAlias().putToAliasesWithArgs().addToScreenBlackList()`）。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
