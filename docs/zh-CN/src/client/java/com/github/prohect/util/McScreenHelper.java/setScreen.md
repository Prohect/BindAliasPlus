# setScreen 方法（src/client/java/com/github/prohect/util/McScreenHelper.java）

## 语法

```java
public static void setScreen(Minecraft client, Screen screen)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `client` | `Minecraft` | Minecraft 客户端实例 |
| `screen` | `Screen` | 要打开的界面；传 `null` 关闭当前界面 |

## 备注

使用类加载时检测到的 API 版本设置当前界面：
- **26.2+（GUI_HAS_SCREEN）**：反射 `Minecraft.class` 的 `gui` 字段，然后调用 `gui.setScreen(screen)`。
- **26.1.x**：反射 `Minecraft.setScreen(Screen)` 方法并调用它。

反射失败时抛出 `RuntimeException`。`screen` 传 `null` 会关闭当前界面。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | 对应的获取方法 |
| [static-init](static-init.md) | `GUI_HAS_SCREEN` 和缓存句柄初始化的位置 |
