# getCurrentScreen 方法（src/client/java/com/github/prohect/util/McScreenHelper.java）

## 语法

```java
public static Screen getCurrentScreen(MinecraftClient client)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `client` | `MinecraftClient` | Minecraft 客户端实例 |

## 返回值

当前打开的 `Screen`；没有打开的界面则为 `null`。

## 备注

直接返回 `client.currentScreen`——Yarn 映射中 `MinecraftClient` 上的公开字段（相当于 Mojang 映射中的 `Minecraft.screen`）。由 `MinecraftClientMixin.tick()` 调用以每帧更新 `BindAliasClient.currentScreen`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [setScreen](setScreen.md) | 对应的设置方法 |
| [static-init](static-init.md) | 初始化 `GUI_HAS_SCREEN` 和缓存的 `Field`/`Method` 句柄的地方 |
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | 主要调用方 |
