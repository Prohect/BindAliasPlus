# isInInventoryScreen 方法（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static boolean isInInventoryScreen()
```

## 返回值

如果当前界面特指 `InventoryScreen`（玩家生存模式物品栏），则返回 `true`，否则返回 `false`。

## 备注

比 `isInContainerScreen()` 更窄的检查——只匹配玩家自身的物品栏界面，不匹配箱子、熔炉等外部容器界面。用于需要区分玩家物品栏与其他容器 GUI 的别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [isInContainerScreen](isInContainerScreen.md) | 更宽泛——包括此界面在内的任何容器界面 |
| [isInCreativeInventoryScreen](isInCreativeInventoryScreen.md) | 创造模式物品栏变体 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
