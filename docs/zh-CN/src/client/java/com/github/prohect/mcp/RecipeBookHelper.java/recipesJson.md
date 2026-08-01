# recipesJson 方法（src/client/java/com/github/prohect/mcp/RecipeBookHelper.java）

## 语法

```java
public static String recipesJson(List<RecipeInfo> recipes)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `recipes` | `List<RecipeInfo>` | 要序列化的配方列表 |

## 返回值

JSON 数组字符串：`[{"name":"Torch","item":"minecraft:torch","craftable":true}, ...]`。为游戏后期配方数量预分配 `StringBuilder`（500+ 已解锁配方 → 约 40 KiB）。

## 备注

把 `RecipeInfo` 记录列表格式化为 JSON。每个条目包含 `"name"`（转义后的显示名称）、`"item"`（转义后的注册表 ID）与 `"craftable"`（布尔值）。字符串转义使用 `GameStateCollector.jsonEscape`，避免双引号/反斜杠破坏 JSON。初始 `StringBuilder` 容量按 `recipes.size() * 80 + 2` 估算，避免反复扩容。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [GameStateCollector.jsonEscape](GameStateCollector.java/jsonEscape.md) | 共享字符串转义工具 |
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | 调用方 |
