# find 方法（src/client/java/com/github/prohect/mcp/RecipeBookHelper.java）

## 语法

```java
public static RecipeInfo find(Minecraft mc, String query)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `mc` | `Minecraft` | Minecraft 客户端实例 |
| `query` | `String` | 用户查询：精确的结果物品 ID（`"minecraft:torch"` 或裸 `"torch"`）或语言名子串（`"iron sword"`） |

## 返回值

第一个匹配的 `RecipeInfo`，无已解锁配方匹配时为 `null`。

## 备注

解析顺序：首先尝试精确注册表 ID 匹配（带或不带 `"minecraft:"` 前缀）。若无匹配，则回退到语言显示名的不区分大小写子串搜索。内部调用 `unlocked(mc)`，因此调用之间不做缓存假设。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [unlocked](unlocked.md) | 底层的完整列表 |
| [matches](matches.md) | 匹配逻辑（相同规则） |
| [ApplyRecipeAlias.run](../../alias/builtinAlias/ApplyRecipeAlias.java/run.md) | 调用 `find` 的 `applyRecipe` 别名 |
