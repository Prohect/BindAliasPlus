# BindAliasPlus

一个 Minecraft Fabric 客户端模组,允许创建自定义别名和按键绑定,通过简单的按键操作来自动化复杂的游戏内动作。

<!-- languages -->
- 🇺🇸 [English](README.md)
- 🇨🇳 [中文 (简体)](README_CN.md)

## 概述

BindAliasPlus 通过让你定义自定义别名来执行一系列动作并将其绑定到按键上，从而增强你的 Minecraft 游戏体验。无论你需要快速交换物品栏槽位、自动化鞘翅飞行，还是链接多个动作（比如使用不在快捷栏或副手中的弓或放置方块），这个模组都能通过可配置的别名和按键绑定来简化重复性任务。

## 特性

- **自定义别名**：为单个或多个游戏内动作创建可重用的别名（例如：交换物品、使用能力、移动）。
- **按键绑定**：将别名绑定到按键，支持按下和释放时执行不同的动作。
- **内置别名**：为常见动作预定义的别名（例如：`swapSlot`、`wait`、`use`、`attack`）。
- **命令系统**：直观的命令来管理别名和绑定（例如：`/alias`、`/bind`、`/unbind`）。
- **配置持久化**：将别名和绑定保存在配置文件中，加入服务器时自动加载。
- **链式动作**：组合别名来创建复杂的序列（例如：装备鞘翅 → 使用烟花 → 飞行）。

## 安装

1. 确保你已为你的 Minecraft 版本安装了 [Fabric Loader](https://fabricmc.net/use/)。
2. 从[发布页面](https://modrinth.com/mod/bind-alias-plus/versions)下载最新的 `bind-alias-plus-*.*.*.jar`。
3. 将 JAR 文件放入你的 Minecraft `mods` 文件夹。
4. 使用 Fabric loader 启动 Minecraft。

## 使用方法

### 核心概念

- **别名（Alias）**：可以执行的自定义或内置动作（或一系列动作）。
- **按键绑定（Key Binding）**：物理按键（例如：`mouse5`、`keyboard.g`）与别名之间的链接（或两个别名：一个用于按下，一个用于释放）。

### 内置别名

BindAliasPlus 包含常见动作的预构建别名。它们分为**带参数的别名**和**不带参数的别名**。

#### 带参数的别名

*注意：槽位遵循 Minecraft 的内部编号：*

- 1-9 → 快捷栏槽位
- 10-36 → 物品栏槽位（10-19 = 第一行）
- 37-40 → 装备槽位（37 = 脚部，38 = 腿部，39 = 胸部，40 = 头部）
- 41 → 副手槽位
-
- 你可以用双引号包裹参数,这样其中的空格就不会被视为分隔符。
- **推荐用于嵌套定义**: 在其他别名定义内使用 `alias`、`bind`、`unbind`、`say` 或 `sendCommand` 内置别名时,使用分号 `;` 而不是空格 ` ` 作为参数之间的分隔符。这样你就可以在嵌套定义中使用正常的空格分隔符而不会产生冲突。示例: `alias +testAlias bind\";x +anotherAlias\" alias\";+yetAnotherAlias +use wait\\1 -use\" say\";Hello World\"` - 这里分号分隔这些内置别名的参数,而引号内的空格正常工作。

| 别名                    | 描述                                                           | 示例                                                            |
|------------------------|----------------------------------------------------------------|---------------------------------------------------------------|
| `log`                  | 将消息记录到游戏控制台（用于调试）。                                          | `log\Hello World`                                             |
| `slot\slotNumber`      | 切换到特定的快捷栏槽位（1-9）。                                            | `slot\3`（切换到快捷栏槽位 3）                                         |
| `swapSlot\slot1\slot2` | 交换两个物品栏槽位之间的物品。                                              | `swapSlot\10\39`（将物品栏槽位 10 与胸甲槽位交换）                           |
| `swapSlot\slot1`       | 交换当前持有的快捷栏槽位（主手）与指定的 `slot1` 之间的物品。                          | `swapSlot\19`（将当前快捷栏槽位与物品栏槽位 19 交换）                           |
| `wait\ticks`           | 暂停执行指定数量的游戏刻（20 刻 = 1 秒）。                                    | `wait\20`（等待 1 秒）                                            |
| `yaw\degrees`          | 按相对度数值调整玩家偏航角（水平旋转）。                                         | `yaw\90`（向右转 90°）                                            |
| `pitch\degrees`        | 按相对度数值调整玩家俯仰角（垂直旋转）。                                         | `pitch\-30`（向下看 30°）                                         |
| `setYaw\degrees`       | 将玩家偏航角设置为绝对度数值（0 = 北，90 = 东）。                                | `setYaw\180`（面向南）                                            |
| `setPitch\degrees`     | 将玩家俯仰角设置为绝对度数值（-90 = 垂直向上，90 = 垂直向下）。                        | `setPitch\0`（直视前方）                                           |
| `alias\args`           | 几乎与命令 alias 相同，只是你需要用双引号包裹参数。                                | `alias\"meow say\nya~"`（创建或替换别名）                             |
| `bind\args`            | 几乎与命令 bind 相同，只是你需要用双引号包裹参数。                                 | `bind\"m meow wait\0 +fly"`（创建或替换绑定）                         |
| `unbind\keyName`       | 几乎与命令 unbind 相同。                                              | `unbind\m`（解除按键上的绑定）                                         |
| `say\string`           | 发送聊天消息。                                                      | `say\"How old r u?"`（发送聊天消息 "how old r u?"）                  |
| `sendCommand\command`  | 发送命令。                                                        | `sendCommand\"gamemode creative"`（发送命令 "gamemode creative"） |

#### 不带参数的别名

这些是映射到常见 `state=1`（开始）和 `state=0`（停止）动作的简写别名，使用更简单：

| 别名          | 等价于                  | 描述                       |
|-------------|----------------------|--------------------------|
| `+attack`   | `builtinAttack\1`    | 开始攻击（按住左键）。              |
| `-attack`   | `builtinAttack\0`    | 停止攻击（释放左键）。              |
| `+use`      | `builtinUse\1`       | 开始使用持有的物品（按住右键）。         |
| `-use`      | `builtinUse\0`       | 停止使用持有的物品（释放右键）。         |
| `+forward`  | `builtinForward\1`   | 开始向前移动。                  |
| `-forward`  | `builtinForward\0`   | 停止向前移动。                  |
| `+back`     | `builtinBack\1`      | 开始向后移动。                  |
| `-back`     | `builtinBack\0`      | 停止向后移动。                  |
| `+left`     | `builtinLeft\1`      | 开始向左移动。                  |
| `-left`     | `builtinLeft\0`      | 停止向左移动。                  |
| `+right`    | `builtinRight\1`     | 开始向右移动。                  |
| `-right`    | `builtinRight\0`     | 停止向右移动。                  |
| `+jump`     | `builtinJump\1`      | 开始跳跃（按住跳跃键）。             |
| `-jump`     | `builtinJump\0`      | 停止跳跃（释放跳跃键）。             |
| `+sneak`    | `builtinSneak\1`     | 开始潜行（按住潜行键）。             |
| `-sneak`    | `builtinSneak\0`     | 停止潜行（释放潜行键）。             |
| `+sprint`   | `builtinSprint\1`    | 开始疾跑（按住疾跑键）。             |
| `-sprint`   | `builtinSprint\0`    | 停止疾跑（释放疾跑键）。             |
| `drop`      | `builtinDrop\0`      | 从持有的堆叠中丢弃一个物品。           |
| `dropStack` | `builtinDrop\1`      | 丢弃整个持有的堆叠。               |
| `swapHand`  | _                    | 交换主手和副手之间的物品。            |
| `+silent`   | `builtinSilent\1`    | 启用静默模式（禁止命令反馈消息）。        |
| `-silent`   | `builtinSilent\0`    | 禁用静默模式（重新启用命令反馈消息）。      |
| `reloadCFG` | —                    | 重新加载配置文件（无需重启即可应用更改）。    |

### 示例

以下是一些实用示例帮助你入门：

#### 1. 鞘翅 + 烟花自动化

使用单个按键自动化鞘翅部署和烟花使用：

```bash
# 定义别名以将鞘翅装备到槽位 39（胸甲槽位）
# 将你的鞘翅放在槽位 10（物品栏第一行的第一个槽位）
/alias equipElytra swapSlot\10\39

# 定义别名以跳跃一次
/alias jump +jump wait\1 -jump

# 定义 +fly（按键按下时）：装备鞘翅 → 跳跃两次以打开它 → 使用烟花
# 将你的烟花放在槽位 19（物品栏第二行的第一个槽位）
/alias +fly equipElytra jump wait\1 jump swapSlot\19 +use -use

# 定义 -fly（按键释放时）：重新装备之前装备的物品
/alias -fly equipElytra swapSlot\19

# 将鼠标按钮 5 绑定到 +fly/-fly
/bind mouse5 +fly
```

#### 2. 快速使用弓

快速切换到弓、使用它，然后切换回来：
（弓不再需要占用快捷栏，对于时运和精准采集镐或末影珍珠也可以尝试这个方法）

```bash
# 定义 +bow（按下时）：切换到弓（槽位 11） → 开始使用
/alias +bow swapSlot\11 +use

# 定义 -bow（释放时）：停止使用 → 切换回来
/alias -bow -use swapSlot\11

# 将鼠标按钮 4 绑定到 +bow/-bow
/bind mouse4 +bow
```

#### 3. 使用静默模式防止聊天刷屏

创建切换绑定（如 fly1/fly2 脚本）时，你可以使用静默模式来抑制反馈消息，避免聊天栏被刷屏：

```bash
# 示例 1：状态切换模式（每次按键切换状态）
# 这种方法在释放按键后仍保持状态
# 使用静默模式防止 "Bound key..." 消息

# 定义 fly1（状态 1）：启用静默，将 mouse5 重新绑定到 fly2，激活鞘翅，禁用静默
/alias fly1 +silent bind\"mouse5 fly2" +equipElytra -silent

# 定义 fly2（状态 2）：启用静默，将 mouse5 重新绑定到 fly1，停用鞘翅，禁用静默
/alias fly2 +silent bind\"mouse5 fly1" -equipElytra -silent

# 初始绑定到 mouse5
/bind mouse5 fly1

# 示例 2：不将动作包裹在静默模式中的状态切换
# bind 命令本身会是静默的，但 +fly/-fly 正常执行
# 当你希望状态改变是静默的但动作有反馈时，这样更简洁
/alias fly1 bind\"mouse5 fly2" +fly
/alias fly2 bind\"mouse5 fly1" -fly
/bind mouse5 fly1

# 示例 3：按住模式（不同于状态切换！）
# 这种方法使用 +/- 别名：按下时执行动作，释放时执行相反动作
# 注意：使用 "/bind mouse5 +silent" 只会在按住按键时启用静默模式
/alias quietFly +silent equipElytra jump wait\1 jump swapSlot\19 +use -use -silent
```

**注意**：
- **状态切换**（`fly1`/`fly2` 模式）：每次按键切换两种状态，释放后状态保持
- **按住模式**（`+alias`/`-alias` 模式）：按下时执行，释放时反转（如 `/bind mouse5 +fly`）
- 静默模式只抑制聊天中的命令反馈消息。错误/警告日志不受影响。

## 配置

- **配置文件**：位于 `config/bind-alias-plus.cfg`。如果不存在会自动创建。
- **自动加载**：配置文件中的别名和绑定在模组加载时自动加载。
- **手动编辑**：你可以直接编辑配置文件来添加/修改别名/绑定（使用与游戏内命令相同的语法）。  
  **配置文件内容示例**：
  ```
  # BindAliasPlus 配置示例
  # 定义鞘翅装备的别名
  alias +equipElytra swapSlot\10\39
  alias -equipElytra swapSlot\10\39
  # 定义烟花处理的别名
  alias +holdFireworks swapSlot\26\41
  alias -holdFireworks swapSlot\26\41
  # 定义简单的跳跃动作
  alias jump +jump wait\1 -jump
  # 定义飞行动作序列（按下时）
  alias +fly +equipElytra jump wait\1 jump +holdFireworks +use -use
  # 定义飞行动作序列（释放时）
  alias -fly -equipElytra -holdFireworks
  
  # 两种绑定按键的方式：
  
  # 方式 1：状态切换模式（每次按下切换状态，状态持续保持）
  # 这样更简洁 - bind 命令是静默的，但 +fly/-fly 正常执行
  alias fly1 bind\"mouse5 fly2" +fly
  alias fly2 bind\"mouse5 fly1" -fly
  bind mouse5 fly1
  
  # 方式 2：按住模式（按下时激活，释放时反转）
  # 当你希望动作仅在按住按键时执行时使用此方式
  bind mouse5 +fly
  ```

## 命令参考

| 命令                               | 用途                                                                                                                                  | 示例                                                       |
|----------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------|
| `/alias <name> <definition>`     | 创建自定义别名。                                                                                                                            | `/alias myAlias +jump wait\1 -jump`                      |
| `/bind <key> <definition>`       | 将按键绑定到此命令定义的别名序列或现有别名。对于通过分隔符分割的每个定义（双引号内的内容仍为同一块），如果以 + 或 - 开头，它将创建一个相反的别名。例如第一个例子，它还将 -forward 和 +back 绑定到键盘键 g 的释放 | `/bind g +forward wait\10 -back   或   /bind n dropStack` |
| `/bindByAliasName <key> <alias>` | 将按键绑定到现有别名。                                                                                                                         | `/bindByAliasName mouse5 +fly`                           |
| `/unbind <key>`                  | 移除按键绑定。                                                                                                                             | `/unbind mouse5`                                         |
| `/reloadCFG`                     | 从文件重新加载配置。                                                                                                                          | `/reloadCFG`                                             |

## 注意事项

- **兼容性**：与大多数 Fabric 模组兼容；可能与修改按键处理或物品栏机制的模组冲突。
- **Minecraft 版本**：需要 Minecraft 1.21+（查看发布页面获取特定版本的构建）。
- **安全性**：避免在带有反作弊系统的服务器上过度自动化（某些动作可能会被标记）。

## 贡献

欢迎贡献！请随时为错误/功能请求提出 issue，或提交改进的 pull request。

## 许可证

此模组基于 [Creative Commons Zero v1.0 Universal](LICENSE) 许可。