# isInContainerScreen 方法（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static boolean isInContainerScreen()
```

## 返回值

若当前界面是 `AbstractContainerScreen`（任意容器 GUI —— 箱子、熔炉、工作台、玩家物品栏等），返回 `true`，否则返回 `false`。

## 备注

`VarAlias` 和 `SwapSlotAlias` 用它来判断容器槽位操作是否有效。只有当此方法返回 `true` 时，`cN` 参数语法（容器槽位 N）才有效。`PickItemAlias` 和 `+drop` 也用它来调整容器界面下的行为。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [isInInventoryScreen](isInInventoryScreen.md) | 范围更窄 —— 仅玩家物品栏界面 |
| [isInCreativeInventoryScreen](isInCreativeInventoryScreen.md) | 范围更窄 —— 创造模式物品栏界面 |
| [isUnderAnyScreen](isUnderAnyScreen.md) | 范围最广 —— 任意界面 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
