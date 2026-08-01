# matches 方法（src/client/java/com/github/prohect/mcp/RecipeBookHelper.java）

## 语法

```java
public static boolean matches(RecipeInfo r, String query)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `r` | `RecipeInfo` | 要测试的配方记录 |
| `query` | `String` | 用户查询（规则与 `find` 相同） |

## 返回值

若 `query` 匹配 `r` 的物品 ID（精确匹配，带或不带 `"minecraft:"` 前缀）或语言显示名称（不区分大小写子串），则返回 `true`。

## 备注

与 `find` 相同的匹配规则，但应用于单个 `RecipeInfo` 记录。由 `handleListRecipes` 用于逐查询过滤模式。不区分大小写：`"TORCH"`、`"torch"` 与 `"Torch"` 均等匹配。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [find](find.md) | 用相同规则返回首个匹配 |
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | 基于查询过滤的调用方 |
