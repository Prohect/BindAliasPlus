# addToScreenBlackList 方法（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public default T addToScreenBlackList()
```

## 返回值

返回 `this`（该别名实例），以便进行流畅的构建器链式调用。

## 备注

将 `this` 别名添加到静态 `blackList4Screen` 列表中。当别名位于黑名单上时，只要有任何界面打开，`UserAlias.run()` 和 `runInternal()` 就会抑制其执行——**但**松开事件（`args.equals("0")`）除外，它总是会通过。

这可以防止按住按键（+attack、+use、+forward 等）在玩家处于物品栏、合成界面或其他界面时影响游戏操作，同时允许松开事件继续传播，从而确保按键不会"卡住"。

`BindAliasClient.onInitializeClient()` 中的典型用法：

```java
new AttackAlias()
    .putToAliasesWithArgs()
    .addToScreenBlackList();
```

只有 `BuiltinAliasWithArgs` 实例才会受黑名单检查约束——`UserAlias` 中的检查会先测试 `alias instanceof BuiltinAliasWithArgs`，然后才查询 `blackList4Screen`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [blackList4Screen](blackList4Screen.md) | 此方法添加到的列表 |
| [isUnderAnyScreen](isUnderAnyScreen.md) | 与黑名单配合使用的界面检查 |
| [UserAlias.run](UserAlias.java/run.md) | 执行期间检查黑名单的位置 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
