# client

注入到原版 Minecraft 客户端类中的 mixin 类。它们是游戏引擎与 BindAlias 模组的别名执行、MCP channel 消息、截图捕获和 freeCursor 支持之间的桥梁。

**推荐阅读顺序：** 从 [`MinecraftClientMixin`](MinecraftClientMixin.java/README.md)（中央 tick 驱动器）开始，然后是 [`KeyBoardMixin`](KeyBoardMixin.java/README.md) 和 [`MouseMixin`](MouseMixin.java/README.md)（输入路由），接着是 [`KeyboardInputMixin`](KeyboardInputMixin.java/README.md)（别名分发）。

## 目录

| 名称 | 说明 |
|------|-------------|
| [HandledScreenMixin.java](HandledScreenMixin.java/README.md) | freeCursor 激活时将悬停槽位固定到 agent 的槽位 14，使丢弃/交换操作针对确定性槽位，而不受宿主光标位置影响 |
| [ChatComponentMixin.java](ChatComponentMixin.java/README.md) | 拦截 `ChatHud` 上统一的 `addMessage(Text)` 入口点，将所有聊天消息（系统、玩家、客户端侧）送入 MCP `CHAT` channel |
| [ClientPacketListenerMixin.java](ClientPacketListenerMixin.java/README.md) | 拦截配方书添加数据包，将新解锁的配方名送入 MCP `RECIPE` channel |
| [KeyboardInputMixin.java](KeyboardInputMixin.java/README.md) | 每个 tick 排空 `KEY_QUEUE` 并将排队的按键事件分发到 `AliasWithoutArgs` 实例——桥接物理输入与别名执行 |
| [KeyBoardMixin.java](KeyBoardMixin.java/README.md) | 拦截物理键盘按下/松开事件，按窗口/锁定/绑定过滤，并将 `KeyPressed` 记录入队到 `KEY_QUEUE` |
| [MinecraftClientMixin.java](MinecraftClientMixin.java/README.md) | 中央每刻集成点：界面跟踪、WaitAlias 延迟任务、持续丢弃和 MCP nap 倒计时 |
| [MouseMixin.java](MouseMixin.java/README.md) | freeCursor 支持（OS 锁定抑制、相机转动取消、isCursorLocked 覆盖）、鼠标按钮路由到 `KEY_QUEUE`，以及光标锁定时的别名重新应用 |
| [NativeImageMixin.java](NativeImageMixin.java/README.md) | 拦截截图 PNG 写入以在内存中捕获字节供 MCP 截图 endpoint 使用，将响应时间从约 500ms 降到 <50ms |
