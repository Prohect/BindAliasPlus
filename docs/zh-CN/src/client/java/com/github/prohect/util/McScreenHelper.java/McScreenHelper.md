# McScreenHelper（src/client/java/com/github/prohect/util/McScreenHelper.java）

## 语法

```java
public final class com.github.prohect.util.McScreenHelper
```

## 静态初始化器

_参见 [static-init](static-init.md)。_

## 备注

桥接 Minecraft 26.1.x 与 26.2+ 之间的界面访问 API 变更：
- **26.1.x**：`Minecraft.screen`（公共字段）和 `Minecraft.setScreen(Screen)`（方法）。
- **26.2+**：两者都移入 `Gui` 类，通过 `client.gui.screen()` 和 `client.gui.setScreen(Screen)` 访问。

检测在静态初始化器中执行一次，使用反射检查 `Gui` 类（`Minecraft.gui` 的类型）是否暴露 `screen()` 方法。检测到的代码路径缓存在静态字段中，供 `getCurrentScreen()` 和 `setScreen()` 使用。这避免了与任一 API 版本的编译期耦合，使单个 jar 能在两种映射下工作。

该类是 `final` 的，带私有构造器 —— 纯静态工具类。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | 使用检测到的 API 获取当前界面 |
| [setScreen](setScreen.md) | 使用检测到的 API 设置当前界面 |
| [static-init](static-init.md) | 基于反射的 API 检测 |
| [MinecraftClientMixin.tick](../../mixin/client/MinecraftClientMixin.java/tick.md) | `getCurrentScreen` 的主要调用方 |
