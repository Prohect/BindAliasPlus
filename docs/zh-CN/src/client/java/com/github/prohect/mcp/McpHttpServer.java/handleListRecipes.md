# handleListRecipes 方法（src/client/java/com/github/prohect/mcp/McpHttpServer.java）

## 语法

```java
static void handleListRecipes(HttpExchange exchange) throws IOException
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `exchange` | `com.sun.net.httpserver.HttpExchange` | HTTP exchange；读取可选的 `query` 参数并返回配方列表 JSON |

## 备注

`POST /listRecipes[?query=<query>]` 处理器。在主线程上：

1. 调用 `RecipeBookHelper.unlocked(mc)` 获取当前所有已解锁配方及其实时可合成状态。
2. 若存在 `query` 参数：
   - 通过 `RecipeBookHelper.matches()` 过滤与查询匹配的配方。每个匹配配方以列表条目形式返回。
   - 响应中的 `recipe_errors` 成员报告没有配方匹配的查询结果。
3. 若无 `query` 参数：
   - 调用 `RecipeBookHelper.onlyNew()` 过滤到未被先前无查询 `listRecipes` 调用报告过的配方（差分模式）。世界变化时重置。
4. 通过 `RecipeBookHelper::recipesJson` 格式化结果，并以 `"recipes"` 键放入 JSON envelope。返回标准状态差分 envelope。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [RecipeBookHelper.unlocked](RecipeBookHelper.java/unlocked.md) | 获取所有已解锁配方 |
| [RecipeBookHelper.matches](RecipeBookHelper.java/matches.md) | 查询匹配逻辑 |
| [RecipeBookHelper.onlyNew](RecipeBookHelper.java/onlyNew.md) | 无查询模式的差分过滤 |
| [RecipeBookHelper.recipesJson](RecipeBookHelper.java/recipesJson.md) | JSON 格式化 |
