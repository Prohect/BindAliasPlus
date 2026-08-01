# EscAlias（src/client/java/com/github/prohect/alias/builtinAlias/EscAlias.java）

关闭当前界面或切换暂停菜单的内置别名。与简单的开关别名不同，它继承 `BuiltinAliasWithIntegerArgs`，因为它支持两种模式：仅关闭（`\0`）和切换（`\1`）。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.EscAlias extends com.github.prohect.alias.BuiltinAliasWithIntegerArgs<com.github.prohect.alias.builtinAlias.EscAlias>
```

## 静态初始化器

_无。_

## 备注

注册名为 `"builtinEsc"`。整数参数区分两种行为：

- **`esc\0`**（`flag == 0`，仅关闭）：如果当前有打开的界面则将其关闭。如果没有打开的界面，则不执行任何操作。
- **`esc\1`**（`flag == 1`，切换）：如果当前有打开的界面则将其关闭。如果没有打开的界面且玩家在世界中（`mc.player != null`），则通过 `mc.pauseGame(false)` 打开暂停菜单。

面向用户的快捷方式映射到这些模式：`+esc` → 切换（flag=1），`-esc` → 仅关闭（flag=0）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [closeScreen](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a) | 专用的仅关闭别名 |
| [BuiltinAliasWithIntegerArgs](../../BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | 整数参数别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
