# OpenInventoryAlias (src/client/java/com/github/prohect/alias/builtinAlias/OpenInventoryAlias.java)

打开或关闭玩家物品栏界面的内置开关别名（`+openInventory` / `-openInventory`）。继承 `BuiltinAliasWithBooleanArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.OpenInventoryAlias extends com.github.prohect.alias.BuiltinAliasWithBooleanArgs<com.github.prohect.alias.builtinAlias.OpenInventoryAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `builtinOpenInventory`（内部，通过 `+openInventory` / `-openInventory` 暴露）。

**行为：**
- `+openInventory`（flag=1）：通过调用 `mc.setScreen(new InventoryScreen(mc.player))` 打开玩家物品栏界面。文本输入界面打开时被阻止。任意界面已打开时被阻止。
- `-openInventory`（flag=0）：仅当当前界面是容器界面（`isInContainerScreen()`）时关闭它。没有容器界面打开时是无操作。

**界面抑制：** 当 `Alias.isUnderTextInputScreen()` 返回 true 时，按下事件（`+openInventory`）被取消。松开事件从不被抑制。

**与 ToggleInventoryAlias 的区别：** 此别名通过 +/- 模式提供独立的打开/关闭控制，而 `toggleInventory` 每次调用在打开和关闭之间切换。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ToggleInventoryAlias](../ToggleInventoryAlias.java/ToggleInventoryAlias.md) | 不使用 +/- 模式的一次性切换 |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | 开关别名的基类 |
| [McScreenHelper](../../../util/McScreenHelper.java/McScreenHelper.md) | 此别名使用的界面管理辅助类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
