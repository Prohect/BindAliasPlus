# unlocked 方法（src/client/java/com/github/prohect/mcp/RecipeBookHelper.java）

## 语法

```java
public static List<RecipeInfo> unlocked(Minecraft mc)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `mc` | `Minecraft` | Minecraft 客户端实例 |

## 返回值

所有当前已解锁配方的 `RecipeInfo` 记录列表，按 `(itemId, displayName)` 去重。无玩家或关卡生效时返回空列表。

## 备注

枚举玩家配方书中的所有 `RecipeCollection` 条目。对每个 `RecipeDisplayEntry`，计算结果物品（通过 `SlotDisplayContext.fromLevel`）并构建 `RecipeInfo`，包含第一个结果的显示名、注册表 ID、可合成状态（通过 `entry.canCraft(stacked)`）和显示 ID。可合成状态会考虑玩家的整个物品栏以及打开的 `RecipeBookMenu` 中的任意合成槽位。有无打开界面均可工作。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [find](find.md) | `unlocked` 的搜索包装 |
| [onlyNew](onlyNew.md) | 无查询 `listRecipes` 的 diff 过滤器 |
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | MCP endpoint |
