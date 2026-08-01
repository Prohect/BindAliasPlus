# onlyNew 方法（src/client/java/com/github/prohect/mcp/RecipeBookHelper.java）

## 语法

```java
public static synchronized List<RecipeInfo> onlyNew(List<RecipeInfo> all)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `all` | `List<RecipeInfo>` | 当前已解锁配方的完整列表 |

## 返回值

尚未被先前无查询 `listRecipes` 调用报告过的配方列表。世界变化时重置（经 `BindAliasClient.joinTick` 检测）。

## 备注

维护一个已报告 `displayId.index()` 值的 `Set<Integer>`。每次调用时，把 `all` 过滤为仅剩不在集合中的配方，然后把它们加入集合。世界变化时（joinTick 与 `baselineJoinTick` 不同），清空集合重新开始。通过 `synchronized` 保证线程安全。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [reset](reset.md) | 手动重置已报告集合 |
| [unlocked](unlocked.md) | `all` 列表的来源 |
| [McpHttpServer.handleListRecipes](McpHttpServer.java/handleListRecipes.md) | 无查询模式的调用方 |
