# captureMessage 方法（src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java）

## 语法

```java
@Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"))
private void captureMessage(Text message, CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `message` | `Text` | 正在添加到 HUD 的聊天消息 |
| `ci` | `CallbackInfo` | Mixin 回调（未使用——注入不可取消） |

## 备注

注入到 `ChatHud#addMessage(Text)` 的 `HEAD`，这是 Minecraft 1.21.x 中所有聊天消息到达 HUD 的唯一点。在该版本中，早期 Minecraft 版本的三个独立 `add*Message` 方法（`addClientSystemMessage`、`addServerSystemMessage`、`addPlayerMessage`）已统一为单个 `addMessage(Text)` 方法，因此单个注入点即可捕获所有消息类型：

- **系统消息**（服务端侧）：加入/离开通知、命令反馈、进度公告。
- **客户端侧系统消息**：浮层文本、动作条消息。
- **玩家聊天消息**：其他玩家或本地玩家发送的消息。

注入在 `HEAD` 运行——在原版将消息渲染到 HUD 之前——并调用私有静态辅助方法 `capture(message.getString())`，它将纯文本消息发布到 `GameChannels.CHAT`。不应用过滤或转换；原始的 `Text#getString()` 输出原样转发。

此方法不可取消。无论 MCP 捕获状态如何，原版消息总是到达 HUD。要从 HUD 抑制聊天消息，请改用 `+silent` / `-silent` 别名。

26.x（Mojang）分支使用了三个独立的 `@Inject` 方法，分别针对 `ChatComponent` 中的 `addClientSystemMessage(Component)`、`addServerSystemMessage(Component)` 和 `addPlayerMessage(Component, MessageSignature, GuiMessageTag)`。1.21.x 分支将它们合并为对 `ChatHud` 统一 `addMessage(Text)` 方法的单个 `captureMessage` 注入。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [capture](capture.md) | 发布到 CHAT channel 的私有辅助方法 |
| [ChatComponentMixin](ChatComponentMixin.md) | 外层 mixin 类 |
| [GameChannels.CHAT](../../../mcp/GameChannels.java/CHAT.md) | 目标 channel |
| [StateTracker](../../../mcp/StateTracker.java/README.md) | 将 CHAT channel 排入 MCP 响应 envelope |

*Documented for Commit: [9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0](https://github.com/Prohect/BindAlias/tree/9f5e40ecb82bef3f43edecd1810a9a9753ccd7f0)*
