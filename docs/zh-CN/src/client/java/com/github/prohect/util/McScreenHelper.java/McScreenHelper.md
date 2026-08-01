# McScreenHelper（src/client/java/com/github/prohect/util/McScreenHelper.java）

## 语法

```java
public final class com.github.prohect.util.McScreenHelper
```

## 静态初始化器

_参见 [static-init](static-init.md)。_

## 备注

包装对 `MinecraftClient.currentScreen`（Yarn 映射中的公开字段；Mojang 中的 `Minecraft.screen`）和 `MinecraftClient.setScreen(Screen)` 直接访问的简单静态工具。

该类是 `final` 的，带私有构造函数——纯静态工具。在此分支（Yarn 映射）上，不需要反射或分支检测：`currentScreen` 是公开字段，`setScreen()` 是 `MinecraftClient` 上的公开方法。

（Yarn：`MinecraftClient.currentScreen`；Mojang：`Minecraft.screen`）

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | 使用检测到的 API 获取当前界面 |
| [setScreen](setScreen.md) | 使用检测到的 API 设置当前界面 |
| [static-init](static-init.md) | 基于反射的 API 检测 |
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | `getCurrentScreen` 的主要调用方 |
