# RecipeBookHelper（src/client/java/com/github/prohect/mcp/RecipeBookHelper.java）

## 语法

```java
public final class com.github.prohect.mcp.RecipeBookHelper
```

## 静态初始化

_无。_

## 备注

MCP API 的客户端配方书读取侧。列出已解锁配方（结果物品的语言名称 + 注册表 ID + 实时可合成状态），并为 `applyRecipe` 别名与 `listRecipes` MCP 工具解析名称/ID 查询。

可合成状态镜像配方书自身的逻辑：统计玩家物品栏中的每个物品堆叠以及打开的 `RecipeBookMenu` 的合成槽位，然后检查 `RecipeDisplayEntry.canCraft(StackedItemContents)`。配方按 `(itemId, displayName)` 去重。查询解析先尝试精确的结果物品 ID（`"minecraft:torch"` 或裸 `"torch"`），再尝试语言名称的不区分大小写子串。

嵌套的 `RecipeInfo` 记录保存每个配方的显示名称、注册表物品 ID、可合成布尔值与 `RecipeDisplayId`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | 调用本类的 MCP endpoint（端点） |
| [ApplyRecipeAlias](../../alias/builtinAlias/ApplyRecipeAlias.java/README.md) | 调用 `find` 的 `applyRecipe` 别名 |
| [unlocked](unlocked.md) | 列出所有已解锁配方 |
| [find](find.md) | 按 ID 或名称子串查找配方 |
