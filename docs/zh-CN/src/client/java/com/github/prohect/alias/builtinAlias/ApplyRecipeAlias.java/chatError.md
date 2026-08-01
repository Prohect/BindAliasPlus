# chatError 方法（src/client/java/com/github/prohect/alias/builtinAlias/ApplyRecipeAlias.java）

向玩家的本地游戏聊天发送错误消息（仅客户端侧，显示在 HUD 和聊天频道中）。

## 语法

```java
private static void chatError(net.minecraft.client.player.ClientPlayerEntity, java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `player` | `ClientPlayerEntity` | 要发送消息的本地玩家 |
| `message` | `String` | 错误消息文本 |

## 备注

使用 `player.sendMessage(Text.literal(message), false)` 仅客户端侧显示消息——**不会**发送到服务器。这适用于只有本地玩家需要看到的别名级错误（参数无效、缺少菜单、未知配方）。（Yarn：`Text`；Mojang：`Component`）

所有 `ApplyRecipeAlias` 错误都通过此辅助方法路由。消息前缀 `[applyRecipe]` 由调用方包含。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [ApplyRecipeAlias.run()](run.md) | 所有错误路径都使用此辅助方法 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
