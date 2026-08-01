# isInCreativeInventoryScreen 方法（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static boolean isInCreativeInventoryScreen()
```

## 返回值

如果当前界面是 `CreativeInventoryScreen`（创造模式的物品选择界面），则返回 `true`，否则返回 `false`。

## 备注

供需要区分创造模式与生存模式物品栏行为的别名使用。创造模式物品栏有不同的槽位布局和机制（例如销毁物品槽位）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [isInInventoryScreen](isInInventoryScreen.md) | 生存模式物品栏变体 |
| [isInContainerScreen](isInContainerScreen.md) | 更宽——任意容器界面 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
