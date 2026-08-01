# McScreenHelper

桥接 Minecraft 26.1.x（`Minecraft.screen` 字段）与 26.2+（`Minecraft.gui.screen()` 方法）之间界面访问 API 变更的工具类。在静态初始化器中使用反射检测运行时可用的是哪个 API。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `GUI_HAS_SCREEN` | `boolean`（静态，私有） | `Gui` 类暴露 `screen()` 时为 `true`（26.2+），26.1.x 为 `false` |
| `GUI_FIELD` | `Field`（静态，私有） | 反射的 `Minecraft.gui` 字段句柄 |
| `GUI_SCREEN` | `Method`（静态，私有） | 反射的 `Gui.screen()` 方法句柄（26.1.x 上为 null） |
| `GUI_SET_SCREEN` | `Method`（静态，私有） | 反射的 `Gui.setScreen(Screen)` 方法句柄（26.1.x 上为 null） |
| `MINECRAFT_SCREEN` | `Field`（静态，私有） | 反射的 `Minecraft.screen` 字段句柄（26.2+ 上为 null） |
| `MINECRAFT_SET_SCREEN` | `Method`（静态，私有） | 反射的 `Minecraft.setScreen(Screen)` 方法句柄（26.2+ 上为 null） |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [getCurrentScreen](getCurrentScreen.md) | `static Screen getCurrentScreen(Minecraft client)` | 通过检测到的 API 获取当前界面 |
| [setScreen](setScreen.md) | `static void setScreen(Minecraft client, Screen screen)` | 通过检测到的 API 设置当前界面 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [MinecraftClientMixin](../../mixin/client/MinecraftClientMixin.java/README.md) | 主要调用方 —— 每刻跟踪 `currentScreen` |
