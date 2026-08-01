# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/ToggleInventoryAlias.java）

切换物品栏界面：关闭时打开，打开时关闭。

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

1. 如果 `isUnderTextInputScreen()` 为 true，则立即返回（在文本输入时抑制）。
2. 如果 `mc.player` 为 null，则立即返回。
3. **如果容器界面已打开（`isInContainerScreen()`）：** 调用 `getCurrentScreen().close()` 关闭它。
4. **否则如果没有界面打开（`!isUnderAnyScreen()`）：** 调用 `mc.setScreen(new InventoryScreen(mc.player))`。
5. **否则（非容器界面已打开）：** 什么也不做（静默返回）。

**返回值：** `this`（流畅返回）。

**副作用：** 打开或关闭玩家物品栏界面。关闭时，触发界面的 `close()` 处理器。

**界面抑制：** 在文本输入界面上完全被抑制。

**边界情况：** 如果非容器界面已打开（暂停界面、设置等），此别名什么也不做——它不会关闭该界面，也不会尝试在其上打开物品栏。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ToggleInventoryAlias](ToggleInventoryAlias.md) | 类概览 |
| [OpenInventoryAlias](../OpenInventoryAlias.java/run.md) | 基于开关的打开/关闭 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
