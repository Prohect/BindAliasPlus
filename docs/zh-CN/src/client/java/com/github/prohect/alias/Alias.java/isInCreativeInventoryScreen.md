# isInCreativeInventoryScreen 方法（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static boolean isInCreativeInventoryScreen()
```

## 返回值

若当前界面是 `CreativeModeInventoryScreen`（创造模式物品选择界面），返回 `true`，否则返回 `false`。

## 备注

需要区分创造模式与生存模式物品栏行为的别名会用到此方法。创造模式物品栏的槽位布局与机制不同（例如销毁物品槽位）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [isInInventoryScreen](isInInventoryScreen.md) | 生存模式物品栏变体 |
| [isInContainerScreen](isInContainerScreen.md) | 范围更广 —— 任意容器界面 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
