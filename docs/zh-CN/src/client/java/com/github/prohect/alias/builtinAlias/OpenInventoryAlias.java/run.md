# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/OpenInventoryAlias.java）

解析 +/- 布尔参数并打开或关闭玩家物品栏界面。

## 语法

```java
public com.github.prohect.alias.builtinAlias.OpenInventoryAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | `"1"` 打开物品栏，`"0"` 关闭当前打开的物品栏/容器界面 |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——设置 `this.flag`（"1" 为 true，"0" 为 false）。
2. 如果文本输入界面已打开且 flag 为 true（打开），则立即返回——输入时不要打开物品栏。
3. 如果 `mc.player` 为 null，则立即返回。
4. **打开（flag=true）：** 如果当前没有界面打开（`!isUnderAnyScreen()`），则调用 `mc.setScreen(new InventoryScreen(mc.player))`。
5. **关闭（flag=false）：** 如果当前打开了容器界面（`isInContainerScreen()`），则调用 `getCurrentScreen().close()`。

**副作用：**
- 在客户端创建并设置新的 `InventoryScreen`（打开时）。
- 关闭当前界面（关闭时，且在容器界面上时）。

**界面抑制：** 当 `isUnderTextInputScreen()` 为 true 时，打开被抑制。关闭从不被抑制。

**边界情况：**
- 如果 flag 为 true 但任意界面已打开，则什么也不做（静默）。
- 如果 flag 为 false 但当前界面不是容器界面（例如暂停界面），则什么也不做。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [OpenInventoryAlias](OpenInventoryAlias.md) | 类概览 |
| [ToggleInventoryAlias](../ToggleInventoryAlias.java/ToggleInventoryAlias.md) | 一次性切换替代方案 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
