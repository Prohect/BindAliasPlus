# client

向原版 Minecraft 客户端类注入的 mixin 类。它们是游戏引擎与 BindAlias 模组的别名执行、MCP channel 消息、截图捕获和 freeCursor 支持之间的桥梁。

**推荐阅读顺序：** 从 [`MinecraftClientMixin`](MinecraftClientMixin.java/README.md)（中央 tick 驱动器）开始，然后是 [`KeyBoardMixin`](KeyBoardMixin.java/README.md) 和 [`MouseMixin`](MouseMixin.java/README.md)（输入路由），接着是 [`KeyboardInputMixin`](KeyboardInputMixin.java/README.md)（别名分发）。

## 目录

| 名称 | 说明 |
|------|-------------|
| [AbstractContainerScreenMixin.java](AbstractContainerScreenMixin.java/README.md) | freeCursor 生效时将悬停槽位固定到代理的槽位 14，使丢弃/交换操作无论宿主光标位置如何都瞄准确定性的槽位 |
| [ChatComponentMixin.java](ChatComponentMixin.java/README.md) | 拦截 `ChatComponent` 的全部三个消息入口点，将聊天消息送入 MCP `CHAT` channel |
| [ClientPacketListenerMixin.java](ClientPacketListenerMixin.java/README.md) | 拦截配方书添加数据包，将新解锁的配方名送入 MCP `RECIPE` channel |
| [KeyboardInputMixin.java](KeyboardInputMixin.java/README.md) | 每刻排空 `KEY_QUEUE` 并将排队按键事件分发给 `AliasWithoutArgs` 实例 —— 桥接物理输入与别名执行 |
| [KeyBoardMixin.java](KeyBoardMixin.java/README.md) | 拦截物理键盘按下/松开事件，按窗口/锁定/绑定过滤，并将 `KeyPressed` 记录入队到 `KEY_QUEUE` |
| [MinecraftClientMixin.java](MinecraftClientMixin.java/README.md) | 中央的每刻集成点：界面跟踪、WaitAlias 延迟任务、持续丢弃和 MCP nap 倒计时 |
| [MouseMixin.java](MouseMixin.java/README.md) | freeCursor 支持（OS 抓取抑制、相机转向取消、isMouseGrabbed 覆盖）、鼠标按键路由到 `KEY_QUEUE`，以及光标抓取时的别名重新应用 |
| [NativeImageMixin.java](NativeImageMixin.java/README.md) | 拦截截图 PNG 写入以在内存中捕获字节，供 MCP 截图 endpoint 使用，将响应时间从约 500ms 降到 <50ms |
