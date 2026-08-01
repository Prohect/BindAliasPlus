# setScreen 方法（src/client/java/com/github/prohect/util/McScreenHelper.java）

## 语法

```java
public static void setScreen(MinecraftClient client, Screen screen)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `client` | `MinecraftClient` | Minecraft 客户端实例 |
| `screen` | `Screen` | 要打开的界面；传 `null` 关闭当前界面 |

## 备注

直接委托给 `client.setScreen(screen)`。将 `screen` 传 `null` 会关闭当前界面。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | 对应的获取方法 |
| [static-init](static-init.md) | 初始化 `GUI_HAS_SCREEN` 和缓存句柄的地方 |
