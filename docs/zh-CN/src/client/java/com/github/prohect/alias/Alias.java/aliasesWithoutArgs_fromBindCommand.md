# aliasesWithoutArgs_fromBindCommand 字段（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static final HashMap<String, AliasWithoutArgs<?>> aliasesWithoutArgs_fromBindCommand
```

专门由 `bind` 命令创建的用户别名的映射。键是通过 `bind\key\aliasName` 分配的别名名称。`BindAliasKeyBinding` 使用这些别名来查找按下绑定按键时应执行的别名。

## 备注

与 `aliasesWithoutArgs` 分开存放，因为 `bind` 命令创建的别名只应由按键事件触发，而不能在别名链中按名称触发。`unbind` 命令会从此映射中移除条目。

由 `BindAlias` 内置别名填充。由 `BindAliasKeyBinding.onKeyPressed()` 读取，以将按键按下分派给对应的用户别名。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
