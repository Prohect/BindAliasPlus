# util

模组各处共享的通用工具类。

## 目录

| 名称 | 说明 |
|------|-------------|
| [McScreenHelper.java](McScreenHelper.java/README.md) | 跨版本界面 API 桥 —— 使用反射检测 Minecraft 版本暴露的是 `Minecraft.screen`（26.1.x）还是 `Minecraft.gui.screen()`（26.2+），并通过检测到的 API 提供 `getCurrentScreen` 和 `setScreen` |
