# chatError 方法（src/client/java/com/github/prohect/alias/builtinAlias/ApplyRecipeAlias.java）

向玩家的本地游戏聊天发送错误消息（仅客户端侧，显示在 HUD 和聊天频道中）。

## 语法

```java
private static void chatError(net.minecraft.client.player.LocalPlayer, java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `player` | `LocalPlayer` | 接收消息的本地玩家 |
| `message` | `String` | 错误消息文本 |

## 备注

使用 `player.sendSystemMessage(Component.literal(message))` 仅客户端显示消息——**不会**发送到服务器。这适用于别名级别的错误（无效参数、缺少菜单、未知配方），这些错误只需本地玩家看到。

所有 `ApplyRecipeAlias` 错误都通过此辅助方法处理。消息前缀 `[applyRecipe]` 由调用方添加。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ApplyRecipeAlias.run()](run.md) | 所有错误路径均使用此辅助方法 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
