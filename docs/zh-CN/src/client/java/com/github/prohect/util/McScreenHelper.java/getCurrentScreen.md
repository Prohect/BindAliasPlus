# getCurrentScreen 方法（src/client/java/com/github/prohect/util/McScreenHelper.java）

## 语法

```java
public static Screen getCurrentScreen(Minecraft client)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `client` | `Minecraft` | Minecraft 客户端实例 |

## 返回值

当前打开的 `Screen`，无界面打开时为 `null`。

## 备注

使用类加载时检测到的 API 版本获取当前界面：
- **26.2+（GUI_HAS_SCREEN）**：反射 `Minecraft.class` 的 `gui` 字段，然后调用 `gui.screen()`。
- **26.1.x**：直接反射 `Minecraft.class` 的 `screen` 字段并读取其值。

反射失败时抛出 `RuntimeException`（例如两个 API 版本都不存在预期的字段/方法）。由 `MinecraftClientMixin.tick()` 调用，每帧更新 `BindAliasClient.currentScreen`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [setScreen](setScreen.md) | 对应的设置方法 |
| [static-init](static-init.md) | `GUI_HAS_SCREEN` 和缓存的 `Field`/`Method` 句柄初始化的位置 |
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | 主要调用方 |
