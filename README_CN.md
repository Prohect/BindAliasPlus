# BindAlias

一个 Minecraft Fabric 客户端模组，允许创建自定义别名和按键绑定，通过简单的按键操作来自动化复杂的游戏内动作。同时通过内置 HTTP API 支持 AI 代理自主控制——代理可以独立
观察、推理并操作 Minecraft。配合 [BindAlias MCP 工具](https://github.com/Prohect/BindAlias-mcp)
让 AI 来玩游戏。

<!-- languages -->

- 🇺🇸 [English](README.md)
- 🇨🇳 [中文 (简体)](README_CN.md)

## 概述

BindAlias 通过让你定义自定义别名来执行一系列动作并将其绑定到按键上，从而增强你的 Minecraft 游戏体验。无论你需要快速交换物品栏槽位、自动化鞘翅飞行，还是链接多个动作（比如使用不在快捷栏或副手中的弓或放置方块），这个模组都能通过可配置的别名和按键绑定来简化重复性任务。

## 特性

- **自定义别名**：为单个或多个游戏内动作创建可重用的别名（例如：交换物品、使用能力、移动）。
- **按键绑定**：将别名绑定到按键，支持按下和释放时执行不同的动作。
- **内置别名**：为常见动作预定义的别名（例如：`swapSlot`、`wait`、`use`、`attack`）。
- **命令系统**：直观的命令来管理别名和绑定（例如：`/alias`、`/bind`、`/unbind`）。
- **配置持久化**：将别名和绑定保存在配置文件中，加入服务器时自动加载。
- **链式动作**：组合别名来创建复杂的序列（例如：装备鞘翅 → 使用烟花 → 飞行）。
- **AI 代理支持**：内置 HTTP API（`GET /state`、`GET /screenshot`、`POST /runAlias` 等）用于 AI 代理
  控制。配合 [BindAlias MCP 工具](https://github.com/Prohect/BindAlias-mcp) 让 AI 代理观察、
  推理并操作你的 Minecraft 世界。

## 安装

1. 确保你已为你的 Minecraft 版本安装了 [Fabric Loader](https://fabricmc.net/use/)。
2. 从[发布页面](https://modrinth.com/mod/bind-alias/versions)下载最新的 `bind-alias-*.*.*.jar`。
3. 将 JAR 文件放入你的 Minecraft `mods` 文件夹。
4. 使用 Fabric loader 启动 Minecraft。

## 使用方法

### 核心概念

- **别名（Alias）**：可以执行的自定义或内置动作（或一系列动作）。
- **按键绑定（Key Binding）**：物理按键（例如：`mouse5`、`keyboard.g`）与别名之间的链接（或两个别名：一个用于按下，一个用于释放）。

### 内置别名

BindAlias 包含常见动作的预构建别名。它们分为**带参数的别名**和**不带参数的别名**。

#### 带参数的别名

_注意：槽位遵循 Minecraft 的内部编号：_

- 1-9 → 快捷栏槽位
- 10-36 → 物品栏槽位（10-19 = 第一行）
- 37-40 → 装备槽位（37 = 脚部，38 = 腿部，39 = 胸部，40 = 头部）
- 41 → 副手槽位
-
- 你可以用双引号包裹参数,这样其中的空格就不会被视为分隔符。
- **推荐用于嵌套定义**: 在其他别名定义内使用 `alias`、`bind`、`unbind`、`say`、`sendCommand` 或 `localSay` 内置别名时,使用分号 `;` 而不是空格 ` ` 作为参数之间的分隔符。这样你就可以在嵌套定义中使用正常的空格分隔符而不会产生冲突。示例: `alias +testAlias bind\v;+anotherAlias alias\+yetAnotherAlias;+anotherAlias;+jump alias\+nextAlias;wait\2;+yetAnotherAlias wait\1 bind\x;+testAlias` - 这里分号分隔这些内置别名的参数,而引号内的空格正常工作。

| 别名                   | 描述                                                                                                                                                                                                                           | 示例                                                                                        |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------- |
| `log`                  | 将消息记录到游戏控制台（用于调试）。                                                                                                                                                                                           | `log\Hello World`                                                                           |
| `slot\slotNumber`      | 切换到特定的快捷栏槽位（1-9）。接受变量名。                                                                                                                                                                                    | `slot\3`（切换到快捷栏槽位 3），`slot\mySlot`（使用变量）                                   |
| `swapSlot\slot1\slot2` | 交换两个槽位之间的物品（1-9 快捷栏，10-36 背包，37-40 装备，41 副手）。支持任意容器界面；用 `cN` 表示当前打开界面的第 N 个槽位（如合成台 `c1` = 产物，熔炉 `c3` = 输出）——可用于合成/锻造/附魔等。接受变量名（仅限普通槽位）。 | `swapSlot\10\39`（将物品栏槽位 10 与胸甲槽位交换），`swapSlot\c1\10`（取合成产物到槽位 10） |
| `swapSlot\slot1`       | 交换当前持有的快捷栏槽位（主手）与指定的 `slot1` 之间的物品。接受变量名。                                                                                                                                                      | `swapSlot\19`（将当前快捷栏槽位与物品栏槽位 19 交换）                                       |
| `wait\ticks`           | 暂停执行指定数量的游戏刻（20 刻 = 1 秒）。接受变量名。                                                                                                                                                                         | `wait\20`（等待 1 秒），`wait\myTicks`（使用变量）                                          |
| `yaw\degrees`          | 按相对度数值调整玩家偏航角（水平旋转）。接受变量名。                                                                                                                                                                           | `yaw\90`（向右转 90°），`yaw\myVar`（使用变量）                                             |
| `pitch\degrees`        | 按相对度数值调整玩家俯仰角（垂直旋转）。接受变量名。                                                                                                                                                                           | `pitch\-30`（向下看 30°），`pitch\myVar`（使用变量）                                        |
| `setYaw\degrees`       | 将玩家偏航角设置为绝对度数值（0 = 北，90 = 东）。接受变量名。                                                                                                                                                                  | `setYaw\180`（面向南），`setYaw\myVar`（使用变量）                                          |
| `setPitch\degrees`     | 将玩家俯仰角设置为绝对度数值（-90 = 垂直向上，90 = 垂直向下）。接受变量名。                                                                                                                                                    | `setPitch\0`（直视前方），`setPitch\myVar`（使用变量）                                      |
| `alias\args`           | 几乎与命令 alias 相同，只是你需要用双引号包裹参数。                                                                                                                                                                            | `alias\"meow say\nya~"`（创建或替换别名）                                                   |
| `bind\args`            | 几乎与命令 bind 相同，只是你需要用双引号包裹参数。                                                                                                                                                                             | `bind\"m meow wait\0 +fly"`（创建或替换绑定）                                               |
| `unbind\keyName`       | 几乎与命令 unbind 相同。                                                                                                                                                                                                       | `unbind\m`（解除按键上的绑定）                                                              |
| `say\string`           | 发送聊天消息。                                                                                                                                                                                                                 | `say\"How old r u?"`（发送聊天消息 "how old r u?"）                                         |
| `localSay\string`      | 仅在本地客户端显示聊天消息，不发送到服务器。适用于测试、通知和调试输出。                                                                                                                                                       | `localSay\"Debug: slot is \(mySlot)"`（仅本地显示的消息）                                   |
| `sendCommand\command`  | 发送命令。                                                                                                                                                                                                                     | `sendCommand\"gamemode creative"`（发送命令 "gamemode creative"）                           |
| `var\varName\source`   | 将值存入变量。来源：`hotbarSlot`、`itemsOfSlot0-9`、`pitch`、`yaw` 或数字。                                                                                                                                                    | `var\mySlot\hotbarSlot`（存储快捷栏槽位），`var\angle\pitch`（存储俯仰角）                  |
| `reapply\action`       | 手动重新激活一个按住状态的布尔别名（attack, use, forward, back, left, right, jump, sneak, sprint, drop, openInventory）。适合在 UserAlias 末尾用于界面切换后恢复按键状态。                                                     | `reapply\forward`（如果处于按住状态则重新按下前进键）                                       |
| `openInventory\state`  | 打开（1）或关闭（0）物品栏界面。                                                                                                                                                                                               | `openInventory\1`（打开物品栏），`openInventory\0`（关闭物品栏）                            |

> **数值别名支持变量引用：** `yaw`、`pitch`、`setYaw`、`setPitch`、`slot`、`swapSlot`、`wait` 和 `setPerspective` 均接受变量名（如 `yaw\myVar` 或 `slot\mySlot`）代替原始数字。

#### 不带参数的别名

这些是映射到常见 `state=1`（开始）和 `state=0`（停止）动作的简写别名，使用更简单：

| 别名                | 等价于                    | 描述                                                                                                                              |
| ------------------- | ------------------------- | --------------------------------------------------------------------------------------------------------------------------------- |
| `+attack`           | `builtinAttack\1`         | 开始攻击（按住左键）。                                                                                                            |
| `-attack`           | `builtinAttack\0`         | 停止攻击（释放左键）。                                                                                                            |
| `+use`              | `builtinUse\1`            | 开始使用持有的物品（按住右键）。                                                                                                  |
| `-use`              | `builtinUse\0`            | 停止使用持有的物品（释放右键）。                                                                                                  |
| `+forward`          | `builtinForward\1`        | 开始向前移动。                                                                                                                    |
| `-forward`          | `builtinForward\0`        | 停止向前移动。                                                                                                                    |
| `+back`             | `builtinBack\1`           | 开始向后移动。                                                                                                                    |
| `-back`             | `builtinBack\0`           | 停止向后移动。                                                                                                                    |
| `+left`             | `builtinLeft\1`           | 开始向左移动。                                                                                                                    |
| `-left`             | `builtinLeft\0`           | 停止向左移动。                                                                                                                    |
| `+right`            | `builtinRight\1`          | 开始向右移动。                                                                                                                    |
| `-right`            | `builtinRight\0`          | 停止向右移动。                                                                                                                    |
| `+jump`             | `builtinJump\1`           | 开始跳跃（按住跳跃键）。                                                                                                          |
| `-jump`             | `builtinJump\0`           | 停止跳跃（释放跳跃键）。                                                                                                          |
| `+sneak`            | `builtinSneak\1`          | 开始潜行（按住潜行键）。                                                                                                          |
| `-sneak`            | `builtinSneak\0`          | 停止潜行（释放潜行键）。                                                                                                          |
| `+sprint`           | `builtinSprint\1`         | 开始疾跑（按住疾跑键）。                                                                                                          |
| `-sprint`           | `builtinSprint\0`         | 停止疾跑（释放疾跑键）。                                                                                                          |
| `+drop`             | `builtinDrop\1`           | 按下丢弃键。**按住可持续丢弃**物品，在3D游戏和容器/物品栏界面中均可使用（与原版一致，有初始连发延迟）。与键盘ctrl组合可丢弃整组。 |
| `-drop`             | `builtinDrop\0`           | 释放丢弃键。                                                                                                                      |
| `+openInventory`    | `builtinOpenInventory\1`  | 打开物品栏界面。                                                                                                                  |
| `-openInventory`    | `builtinOpenInventory\0`  | 关闭物品栏界面（如果已打开）。                                                                                                    |
| `pickItem`          | —                         | 对瞄准的方块/实体触发原版的选取方块功能。                                                                                         |
| `swapHand`          | _                         | 交换主手和副手之间的物品。                                                                                                        |
| `+silent`           | `builtinSilent\1`         | 启用静默模式（禁止命令反馈消息）。                                                                                                |
| `-silent`           | `builtinSilent\0`         | 禁用静默模式（重新启用命令反馈消息）。                                                                                            |
| `+lockKey\<target>` | `builtinLock\<target>\1`  | 锁定游戏按键或自定义别名。对原版按键使用 `gameKey:attack`、`gameKey:forward` 等格式，或直接使用别名名称。                         |
| `-lockKey\<target>` | `builtinLock\<target>\0`  | 解锁之前锁定的游戏按键或自定义别名。                                                                                              |
| `cyclePerspective`  | —                         | 循环切换视角（FPS → TPS → TPS2）。                                                                                                |
| `FPS`               | `builtinSetPerspective\0` | 切换到第一人称视角。                                                                                                              |
| `TPS`               | `builtinSetPerspective\1` | 切换到第三人称背面视角。                                                                                                          |
| `TPS2`              | `builtinSetPerspective\2` | 切换到第三人称正面视角。                                                                                                          |
| `reloadCFG`         | —                         | 重新加载配置文件（无需重启即可应用更改）。                                                                                        |
| `unloadCFGAliases`  | —                         | 移除所有从配置文件加载的别名。                                                                                                    |
| `unloadCFGBinds`    | —                         | 移除所有从配置文件加载的按键绑定。                                                                                                |
| `unloadCFGVars`     | —                         | 移除所有从配置文件加载的变量。                                                                                                    |
| `unloadCFGAll`      | —                         | 移除所有从配置文件加载的别名、按键绑定和变量。                                                                                    |

### 变量

变量可以捕获并复用游戏内的值（快捷栏槽位、视角角度、物品数量等）。

**来源** 用于 `var\varName\source`：

| 来源           | 描述                                      | 示例                     |
| -------------- | ----------------------------------------- | ------------------------ |
| `hotbarSlot`   | 当前快捷栏槽位（1-9）                     | `var\mySlot\hotbarSlot`  |
| `itemsOfSlotN` | 槽位 N 中的物品数量（0=副手，1-9=快捷栏） | `var\count\itemsOfSlot2` |
| `pitch`        | 玩家当前的俯仰角（浮点数）                | `var\myPitch\pitch`      |
| `yaw`          | 玩家当前的偏航角（浮点数）                | `var\myYaw\yaw`          |
| `42` 或 `3.14` | 直接数值（整数或浮点数）                  | `var\backup\42`          |

变量可以在任何数值别名中作为参数使用（如 `yaw\myVar`、`slot\mySlot`、`wait\myTicks`）。

**变量相关命令：**

| 命令                   | 用途                       | 示例                     |
| ---------------------- | -------------------------- | ------------------------ |
| `/var <name> <source>` | 创建或更新变量。           | `/var mySlot hotbarSlot` |
| `/unloadCFGVars`       | 移除所有从配置加载的变量。 | `/unloadCFGVars`         |

### AI 代理 / MCP HTTP 服务器

BindAlias 内置了一个 HTTP 服务器，使 AI 代理（如 Claude、ChatGPT 或自定义自动化脚本）能够观察和控制你的 Minecraft 客户端。这是
[BindAlias MCP 工具](https://github.com/Prohect/BindAlias-mcp) 的配套模组。

**托管指南：** 请参阅 [`src/MCP_HOSTING_GUIDE.md`](src/MCP_HOSTING_GUIDE.md)，了解如何为代理设置游戏、
连接 MCP 桥接以及管理长期运行的代理会话。

服务器默认监听 `http://localhost:25567`（可在 `config/bind-alias.cfg` 中配置），提供以下接口：

| 接口           | 方法 | 描述                                                   |
| -------------- | ---- | ------------------------------------------------------ |
| `/state`       | GET  | 玩家位置、生命值、手持物品、打开容器的内容（压缩格式） |
| `/screenshot`  | GET  | 内存 PNG 截图（无聊天刷屏，无文件 I/O）                |
| `/runAlias`    | POST | 远程执行别名链（如 `swapSlot\1\2`）                    |
| `/defineAlias` | POST | 通过 API 定义新别名                                    |
| `/readCFG`     | GET  | 读取当前配置文件内容                                   |
| `/writeCFG`    | POST | 写入配置文件（修改按键绑定、别名、变量）               |

**示例代理用法：**

```bash
# 查看玩家视角
curl http://localhost:25567/state

# 执行别名链
curl -X POST http://localhost:25567/runAlias -d "swapSlot\1\2\wait\2\+attack"

# 截图
curl http://localhost:25567/screenshot -o screen.png
```

### 示例配置

这是一个真实的配置文件 (`config/bind-alias.cfg`)，展示了所有核心功能的实际用法：

```cfg
/var offHand 41
/alias jumpOnce +jump wait\0 -jump
## 36->elytra; 27->firework
/alias +fly swapSlot\36\39 jumpOnce wait\0 jumpOnce swapSlot\27\41 +use -use TPS
/alias -fly swapSlot\36\39 swapSlot\27\41 wait\2 FPS
/alias +fastUse_Var swapSlot\varFastUse\offHand +use
/alias -fastUse_Var -use swapSlot\varFastUse\offHand
/alias +fastAttack_Var swapSlot\varFastAttack wait\1 +attack
/alias -fastAttack_Var -attack swapSlot\varFastAttack

# alias fly_on +silent bind\"mouse5 fly_off" -silent +fly
# alias fly_off +silent bind\"mouse5 fly_on" -silent -fly

/bind w +forward
/bind a +left
/bind s +back
/bind d +right
/bind space +jump
/bind left.shift +sneak
/bind left.control +sprint
/bind mouse1 +attack
/bind mouse2 +use
/bind mouse5 +fly
## water; powder_snow; food; ender_pearl; bow;... 3 fast use slots
/bind mouse4 var\varFastUse\19 +fastUse_Var
/bind b var\varFastUse\20 +fastUse_Var
/bind v var\varFastUse\28 +fastUse_Var
## fortune_pickaxe 1 fast mine slot
/bind n var\varFastAttack\29 +fastAttack_Var
```

## 配置

- **配置文件**：位于 `config/bind-alias.cfg`。如果不存在会自动创建。
- **自动加载**：配置文件中的别名和绑定在模组加载时自动加载。
- **手动编辑**：你可以直接编辑配置文件来添加/修改别名/绑定（使用与游戏内命令相同的语法）。
  参见上方[示例配置](#示例配置)部分了解完整的真实配置。

## 命令参考

| 命令                             | 用途                                                                                                                                                                                                             | 示例                                                  |
| -------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------- |
| `/alias <name> <definition>`     | 创建自定义别名。                                                                                                                                                                                                 | `/alias myAlias +jump wait\1 -jump`                   |
| `/bind <key> <definition>`       | 将按键绑定到此命令定义的别名序列或现有别名。对于通过分隔符分割的每个定义（双引号内的内容仍为同一块），如果以 + 或 - 开头，它将创建一个相反的别名。例如第一个例子，它还将 -forward 和 +back 绑定到键盘键 g 的释放 | `/bind g +forward wait\10 -back   或   /bind n +drop` |
| `/bindByAliasName <key> <alias>` | 将按键绑定到现有别名。                                                                                                                                                                                           | `/bindByAliasName mouse5 +fly`                        |
| `/unbind <key>`                  | 移除按键绑定。                                                                                                                                                                                                   | `/unbind mouse5`                                      |
| `/reloadCFG`                     | 从文件重新加载配置。                                                                                                                                                                                             | `/reloadCFG`                                          |
| `/var <name> <source>`           | 创建/更新变量。来源：`hotbarSlot`、`itemsOfSlot0-9`、`pitch`、`yaw` 或数字。                                                                                                                                     | `/var mySlot hotbarSlot`、`/var angle pitch`          |
| `/unloadCFGAliases`              | 移除所有从配置加载的别名。                                                                                                                                                                                       | `/unloadCFGAliases`                                   |
| `/unloadCFGBinds`                | 移除所有从配置加载的按键绑定。                                                                                                                                                                                   | `/unloadCFGBinds`                                     |
| `/unloadCFGVars`                 | 移除所有从配置加载的变量。                                                                                                                                                                                       | `/unloadCFGVars`                                      |
| `/unloadCFGAll`                  | 移除所有从配置加载的别名、按键绑定和变量。                                                                                                                                                                       | `/unloadCFGAll`                                       |

## 注意事项

- **兼容性**：与大多数 Fabric 模组兼容；可能与修改按键处理或物品栏机制的模组冲突。
- **Minecraft 版本**：需要 Minecraft 1.21+（Yarn 映射）或 26.x（Mojang 映射）。请查看发布页面获取特定版本
  的构建文件（文件名包含 MC 版本）。
- **变量**：支持整数和浮点数值。数值别名（`yaw`、`pitch`、`setYaw`、`setPitch`、`slot`、`swapSlot`、`wait`、
  `setPerspective`）接受变量名代替原始数字。
- **安全性**：避免在带有反作弊系统的服务器上过度自动化（某些动作可能会被标记）。
- **MCP 服务器**：内置 HTTP API 默认监听 `25567` 端口。如端口冲突，可在 `config/bind-alias.cfg`
  中修改 `mcpPort`。配合 [BindAlias MCP 工具](https://github.com/Prohect/BindAlias-mcp)
  实现 AI 代理集成。

## 贡献

欢迎贡献！请随时为错误/功能请求提出 issue，或提交改进的 pull request。

## 许可证

此模组基于 [Creative Commons Zero v1.0 Universal](LICENSE) 许可。
