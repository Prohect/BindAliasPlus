# ApplyRecipeAlias（src/client/java/com/github/prohect/alias/builtinAlias/ApplyRecipeAlias.java）

将已解锁、可合成的配方放入当前打开的配方菜单（物品栏、工作台、熔炉等）合成格中的内置别名。继承 `BuiltinAliasWithStringArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.ApplyRecipeAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.ApplyRecipeAlias>
```

## 静态初始化器

_无。_

## 备注

注册名为 `"applyRecipe"`。用法：`applyRecipe\<query>`。

查询可以是：
- 完整或简写的物品注册表 ID：`minecraft:torch` 或 `torch`
- 物品语言名称的不区分大小写的子串：`"iron sword"`

**不执行任何合成** —— 这只会把配方放入合成格（与在配方书中点击它完全一样）。取出产物是单独的操作，通常通过 `swapSlot\c1\10` 将结果槽位移入快捷栏。

**要求：**
- 必须打开配方菜单（玩家物品栏、工作台、熔炉等）——容器必须实现 `RecipeBookMenu`
- 配方必须在配方书中已解锁
- 玩家的物品栏中必须有所需的材料

**错误报告：** 错误（未打开菜单、未解锁、缺少材料、空查询）通过 `chatError()` 作为客户端侧系统消息发送到本地游戏聊天界面。成功则记录到模组频道。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md) | 将合成产物移出合成格 |
| [RecipeBookHelper](../../../mcp/RecipeBookHelper.java/RecipeBookHelper.md) | 按查询查找配方 |
| [ListRecipes](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a) | 列出已解锁配方的 MCP 工具 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
