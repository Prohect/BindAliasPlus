# static-init（src/client/java/com/github/prohect/util/McScreenHelper.java）

## 备注

`McScreenHelper` 类加载时执行一次。使用反射检测可用的 Minecraft 界面访问 API：

1. 尝试从 `Minecraft.class` 获取 `gui` 字段（26.1.x 和 26.2+ 中都存在）。
2. 检查 `gui` 字段的类型是否暴露 `screen()` 方法：
   - **若是**（26.2+）：缓存 `gui` 字段、`screen()` 方法和 `Gui` 类上的 `setScreen(Screen)` 方法。设置 `GUI_HAS_SCREEN = true`。`Minecraft.screen`/`setScreen` 相关句柄保持为 null。
   - **若否**（26.1.x）：直接在 `Minecraft.class` 上反射 `screen` 字段和 `setScreen(Screen)` 方法。设置 `GUI_HAS_SCREEN = false`。`Gui` 相关句柄保持为 null。

所有反射句柄都通过 `setAccessible(true)` 使其可访问。检测期间的异常被静默吞掉 —— 若两个 API 都未找到，`getCurrentScreen`/`setScreen` 会在调用时抛出 `RuntimeException`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | 使用 `GUI_HAS_SCREEN` 和缓存的句柄 |
| [setScreen](setScreen.md) | 使用 `GUI_HAS_SCREEN` 和缓存的句柄 |
