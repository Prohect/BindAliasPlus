# isUnderAnyScreen 方法（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static boolean isUnderAnyScreen()
```

## 返回值

如果当前有任意界面打开（即 `getCurrentScreen() != null`），则返回 `true`，否则返回 `false`。

## 备注

与界面黑名单（`blackList4Screen`）配合使用，在 GUI 打开时抑制别名。别名也独立使用此方法——例如 `+freeCursor` 仅在界面打开时绕过原版的鼠标抓取逻辑，`toggleInventory` 则检查它以决定打开还是关闭物品栏。

**与 `isUnderTextInputScreen()` 的关键区别**：此方法对*所有*界面都返回 `true`，包括物品栏、合成界面和容器界面。`isUnderTextInputScreen()` 是更严格的子集，只捕获文本输入界面。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [isUnderTextInputScreen](isUnderTextInputScreen.md) | 更严格——仅文本输入界面 |
| [blackList4Screen](blackList4Screen.md) | 此方法返回 `true` 时被抑制的别名 |
| [isInContainerScreen](isInContainerScreen.md) | 更窄——仅容器界面 |
| [isInInventoryScreen](isInInventoryScreen.md) | 更窄——仅玩家物品栏界面 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
