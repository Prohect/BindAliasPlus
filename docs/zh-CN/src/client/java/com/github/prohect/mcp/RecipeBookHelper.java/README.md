# RecipeBookHelper

MCP API 的客户端配方书读取侧。列出已解锁配方及其实时可合成性，按物品 ID 或语言名解析查询，并为 `listRecipes` endpoint 提供 diff 记账。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|

## 方法

**列出与查询：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [unlocked](unlocked.md) | `static List<RecipeInfo> unlocked(MinecraftClient mc)` | 列出当前所有已解锁配方及其实时可合成性 |
| [find](find.md) | `static RecipeInfo find(MinecraftClient mc, String query)` | 按物品 ID 或名称子串查找第一个匹配的已解锁配方 |
| [matches](matches.md) | `static boolean matches(RecipeInfo r, String query)` | 测试单个配方是否匹配查询 |

**Diff 记账：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [onlyNew](onlyNew.md) | `static synchronized List<RecipeInfo> onlyNew(List<RecipeInfo> all)` | 过滤出尚未上报的配方（无查询 `listRecipes` 的 diff 模式） |
| [reset](reset.md) | `static void reset()` | 清空已上报配方集合（加入世界时调用） |

**格式化：**

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [recipesJson](recipesJson.md) | `static String recipesJson(List<RecipeInfo> recipes)` | 将配方列表格式化为 JSON 数组 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | MCP endpoint |
| [ApplyRecipeAlias](../../alias/builtinAlias/ApplyRecipeAlias.java/README.md) | `applyRecipe` 别名 |
