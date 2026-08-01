# unlocked 方法（src/client/java/com/github/prohect/mcp/RecipeBookHelper.java）

## 语法

```java
public static List<RecipeInfo> unlocked(MinecraftClient mc)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `mc` | `MinecraftClient` | Minecraft 客户端实例 |

## 返回值

当前所有已解锁配方的 `RecipeInfo` 记录列表，按 `(itemId, displayName)` 去重。没有活动的玩家或关卡时返回空列表。

## 备注

枚举玩家配方书中的所有 `RecipeCollection` 条目。对每个 `RecipeDisplayEntry`，计算结果物品（通过 `SlotDisplayContext.fromLevel`），并构建包含第一个结果的显示名、注册表 ID、可合成性（通过 `entry.canCraft(stacked)`）和显示 ID 的 `RecipeInfo`。可合成性考虑玩家的整个物品栏加上打开的 `RecipeBookMenu` 中的任何合成格。无论是否有打开的界面都能工作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [find](find.md) | 围绕 `unlocked` 的搜索包装 |
| [onlyNew](onlyNew.md) | 无查询 `listRecipes` 的 diff 过滤器 |
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | MCP endpoint |
