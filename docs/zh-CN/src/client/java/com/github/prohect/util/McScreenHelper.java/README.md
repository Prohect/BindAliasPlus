# McScreenHelper

包装对 `MinecraftClient.currentScreen` 和 `MinecraftClient.setScreen()` 直接访问的简单静态工具。

## 字段

_（无——此分支上没有反射字段）_

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | `static Screen getCurrentScreen(MinecraftClient client)` | 返回 `client.currentScreen` |
| [setScreen](setScreen.md) | `static void setScreen(MinecraftClient client, Screen screen)` | 委托给 `client.setScreen(screen)` |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [MinecraftClientMixin](../../mixin/client/MinecraftClientMixin.java/README.md) | 主要调用方——每个 tick 跟踪 `currentScreen` |
