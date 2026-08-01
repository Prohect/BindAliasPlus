# ToggleInventoryAlias (src/client/java/com/github/prohect/alias/builtinAlias/ToggleInventoryAlias.java)

切换玩家物品栏界面的一次性别名——关闭时打开，打开时关闭。继承 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.ToggleInventoryAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.ToggleInventoryAlias>
```

## 静态初始化

_无。_

## 备注

**别名名称：** `toggleInventory`（内部，作为 `toggleInventory` 暴露）。

**行为：**
- 如果当前打开了容器界面（任意 `HandledScreen`）：通过 `close()` 关闭它。
- 如果当前没有界面打开：通过调用 `mc.setScreen(new InventoryScreen(mc.player))` 打开玩家物品栏界面。

**界面抑制：** 当 `Alias.isUnderTextInputScreen()` 返回 true 时，打开和关闭都被抑制。

**要求：** `mc.player` 必须非空。为 null 时静默返回。

**与 OpenInventoryAlias 的区别：** `toggleInventory` 是一次性切换——它反转当前状态。`OpenInventoryAlias` 使用 `+`/`-` 开关模式进行显式打开/关闭控制。ToggleInventoryAlias 更简单，更常用于快速访问物品栏。

**边界情况——任意界面打开：** 如果非容器界面已打开（例如暂停界面、设置），`toggleInventory` 什么也不做（静默返回），因为 `isUnderAnyScreen()` 返回 true 但 `isInContainerScreen()` 返回 false，两个分支都不会执行。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [OpenInventoryAlias](../OpenInventoryAlias.java/OpenInventoryAlias.md) | 带 +/- 模式的基于开关的打开/关闭 |
| [BuiltinAliasWithoutArgs](../../BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | 一次性别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
