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
2. 若文本输入界面打开且 flag 为 true（打开），立即返回——输入时不打开物品栏。
3. 若 `mc.player` 为 null，立即返回。
4. **打开（flag=true）：** 若当前没有界面打开（`!isUnderAnyScreen()`），向服务器发送 `sendOpenInventory()` 数据包，并将客户端界面设置为新的 `InventoryScreen`。
5. **关闭（flag=false）：** 若当前有容器界面打开（`isInContainerScreen()`），对当前界面调用 `onClose()`。

**副作用：**
- 向服务器发送 `ServerboundOpenInventory` 数据包（打开时）。
- 在客户端创建并设置新的 `InventoryScreen`（打开时）。
- 关闭当前界面（关闭时，且当前在容器界面上）。

**界面抑制：** `isUnderTextInputScreen()` 为 true 时抑制打开。关闭从不被抑制。

**边界情况：**
- 若 flag 为 true 但已有界面打开，不执行任何操作（静默）。
- 若 flag 为 false 但当前界面不是容器界面（例如暂停界面），不执行任何操作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [OpenInventoryAlias](OpenInventoryAlias.md) | 类概览 |
| [ToggleInventoryAlias](../ToggleInventoryAlias.java/ToggleInventoryAlias.md) | 一次性切换替代方案 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
