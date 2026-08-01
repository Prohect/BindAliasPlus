# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/ToggleInventoryAlias.java）

切换物品栏界面：关闭则打开，打开则关闭。

## 语法

```java
public com.github.prohect.alias.builtinAlias.ToggleInventoryAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 若 `isUnderTextInputScreen()` 为 true，立即返回（文本输入时抑制）。
2. 若 `mc.player` 为 null，立即返回。
3. **若容器界面打开（`isInContainerScreen()`）：** 调用 `currentScreen.onClose()` 将其关闭。
4. **否则若没有界面打开（`!isUnderAnyScreen()`）：** 发送 `sendOpenInventory()` 数据包并将界面设置为新的 `InventoryScreen`。
5. **其他情况（非容器界面打开）：** 不执行任何操作（静默返回）。

**返回值：** `this`（流畅式返回）。

**副作用：** 打开或关闭玩家物品栏界面。打开时，向服务器发送 `OpenInventory` 数据包。关闭时，触发界面的 `onClose()` 处理器。

**界面抑制：** 在文本输入界面上被完全抑制。

**边界情况：** 若已有非容器界面打开（暂停菜单、设置等），此别名不执行任何操作——它不会关闭该界面，也不会尝试在其上方打开物品栏。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ToggleInventoryAlias](ToggleInventoryAlias.md) | 类概览 |
| [OpenInventoryAlias](../OpenInventoryAlias.java/run.md) | 基于开关的打开/关闭 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
