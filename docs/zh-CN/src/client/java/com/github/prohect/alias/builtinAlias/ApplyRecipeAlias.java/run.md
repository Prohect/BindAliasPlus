# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/ApplyRecipeAlias.java）

将已解锁的可合成配方放入打开的配方菜单的合成格中。

## 语法

```java
public com.github.prohect.alias.builtinAlias.ApplyRecipeAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 配方查询：物品注册 ID（`torch`、`minecraft:torch`）或语言名子串（`"iron sword"`） |

## 备注

**算法（逐步）：**

1. 校验玩家非 null；若为 null，记录警告并返回。
2. 去除查询字符串两端空白。若为空，向本地聊天发送错误：`"[applyRecipe] usage: applyRecipe\<recipe name or item id>"`。
3. 检查当前容器菜单是否实现 `RecipeBookMenu`。若不是，发送错误：`"[applyRecipe] no recipe menu open (open the inventory or a crafting station)"`。
4. 通过 `RecipeBookHelper.find(mc, query)` 查找配方。若未找到（null），发送错误：`"[applyRecipe] not unlocked or unknown recipe: <query>"`。
5. 检查 `recipe.craftable()`。若为 false（缺少材料），发送错误：`"[applyRecipe] missing ingredients for: <recipe name>"`。
6. 调用 `mc.gameMode.handlePlaceRecipe(menu.containerId, recipe.displayId(), false)` 将配方放入合成格。`false` 参数表示"不取全部"（单次合成）。
7. 记录成功日志：`"[applyRecipe] applied <recipe name>"`。

**错误报告：** 所有错误均使用 `chatError()`，它向玩家的本地聊天发送 `Component.literal()` 系统消息（显示在 HUD 和聊天频道中）。成功仅记录到模组日志，不在聊天中显示。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [chatError](chatError.md) | 错误转聊天辅助方法 |
| [RecipeBookHelper.find()](../../../mcp/RecipeBookHelper.java/find.md) | 按查询查找配方 |
| [SwapSlotAlias.run()](../SwapSlotAlias.java/run.md) | 移动合成产物 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
