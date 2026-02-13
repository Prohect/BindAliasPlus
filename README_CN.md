# BindAliasPlus

一个 Fabric **客户端**模组，让你创建**别名**（宏）并**将它们绑定到按键/鼠标按钮**，这样一次按键就可以运行完整的动作序列——类似于"绑定/别名配置"工作流。

<!-- languages -->
- 🇺🇸 [English](README.md)
- 🇨🇳 [中文 (简体)](README_CN.md)

> 仅限客户端：服务器不需要安装此模组。

---

## 安装

1. 为你的 Minecraft 版本安装 [Fabric Loader](https://fabricmc.net/use/)。
2. 从 Modrinth 下载模组：https://modrinth.com/mod/bind-alias-plus/versions
3. 将 `.jar` 文件放入你的 Minecraft `mods` 文件夹。
4. 启动 Minecraft。

---

## 核心理念

你将**别名**定义为一系列**步骤**，然后绑定一个按键来运行它。

- 步骤之间用**空格**分隔。
- 一个步骤可以是：
  - 无参数的别名：`jump`
  - 带参数的别名：`swapSlot\10\39`（参数用反斜杠 `\` 分隔）

你可以绑定按键，使得：
- **按下**时运行一个别名
- **释放**时运行另一个别名（典型的 `+something` / `-something` 模式）

---

## 语法

### A) 别名定义按空格分割

别名定义是一系列**按空格 ` ` 分割**的步骤：

- `equipElytra jump wait\1 jump swapSlot\19 +use -use`

### B) **步骤参数**按反斜杠 `\` 分割

如果一个步骤包含 `\`，第一个 `\` 之前的部分是别名名称，其余部分是它的参数：

- `swapSlot\10\39` → 别名名称 `swapSlot`，参数：`10`、`39`
- `wait\20` → 别名名称 `wait`，参数：`20`

### C) 单个**步骤参数**内的空格

如果单个**步骤参数**必须包含空格，请用**双引号**包裹该参数。

这主要对发送聊天/命令的内置别名或嵌套定义（见下一节）很重要。

---

## 嵌套定义（重要）：`BuiltinAliasWithGreedyStringArgs` 和 `;`

一些内置别名接受**贪婪字符串**负载，这样你可以从另一个别名内部定义/绑定/解绑/聊天/运行命令：

- `alias`
- `bind`
- `unbind`
- `say`
- `sendCommand`

### 关键技巧：使用 `;` 避免引号

外部解析总是按**空格**分割别名步骤。

所以如果你编写的嵌套负载包含空格，你通常需要引号来将其保持为单个参数。

然而，对于贪婪字符串内置别名，**你也可以通过避免引号**的方式，将嵌套负载写成单个令牌，并使用**分号 `;`** 来分隔该令牌内的各部分。

示例（两种方式都有效）：

- 使用引号（负载有空格）：
  - `/alias makeJumpAlias alias\"jump +jump wait\1 -jump"`

- 不使用引号（负载是一个令牌，使用 `;` 作为分隔符）：
  - `/alias makeJumpAlias alias\jump;+jump;wait\1;-jump`

### 贪婪字符串内置别名如何处理负载

- `alias\...`、`bind\...`、`unbind\...`：
  - 在将最终聊天命令发送到游戏之前，它们**将 `;` 替换为空格 ` `**。
  - 这就是 `/alias makeJumpAlias alias\jump;+jump;wait\1;-jump` 有效的原因：当调用 `makeJumpAlias` 时，它会发送聊天命令 `/alias jump +jump wait\1 -jump`。

- `say\...` 和 `sendCommand\...`：
  - 它们**不替换 `;`**。
  - 你在其负载中放入的任何内容都会按原样发送（所以 `;` 作为字面字符保留）。

---

## 命令

### `/alias <name> <definition>`
创建或替换一个**用户别名**。

注意：
- 你**不能覆盖**内置别名。
- 用户别名通过将定义展开为步骤来运行。

示例：
- `/alias pearl swapSlot\12 +use wait\1 -use swapSlot\12`

### `/bind <key> <definition-or-aliasName>`
绑定一个按键/鼠标按钮。

行为：
1. 如果 `<definition-or-aliasName>` 匹配现有的别名名称（包括 `+name`/`-name` 形式），它会绑定到该别名。
2. 否则，它将其视为内联定义，并为绑定创建一个内部别名。

按下/释放行为：
- 如果你的绑定定义包含 `+something` 和/或 `-something`，模组可以从这些 `+/-` 步骤自动派生一个"相反的"释放端。

示例：
- `/bind g jump`
- `/bind g +forward wait\10 -forward`

### `/bindByAliasName <key> <aliasName>`
直接将按键绑定到现有的别名名称。

示例：
- `/bindByAliasName mouse5 +fly`
- `/bindByAliasName g jump`

### `/unbind <key>`
移除绑定。

示例：
- `/unbind mouse5`

### `/reloadCFG`
从磁盘重新加载配置文件。

---

## 配置文件

路径：
- `config/bind-alias-plus.cfg`

规则：
- 每行一个命令
- 开头的 `/` 是可选的
- `#` 开始注释行

编辑后重新加载：
- `/reloadCFG`

---

## 内置别名

BindAliasPlus 附带了内置别名，你可以在别名定义中调用它们。

### 1) 带参数的内置别名（参数之间使用 `\`）

| 别名 | 参数 | 功能 | 示例 |
|---|---:|---|---|
| `log\text` | text | 将消息记录到控制台（调试） | `log\Hello` |
| `slot\n` | `n=1..9` | 选择快捷栏槽位 | `slot\3` |
| `swapSlot\a\b` | `a,b` | 交换两个槽位 | `swapSlot\10\39` |
| `swapSlot\a` | `a` | 将槽位 `a` 与**当前选择的快捷栏槽位**交换 | `swapSlot\19` |
| `wait\ticks` | ticks | 延迟执行（`20 刻 = 1 秒`） | `wait\20` |
| `yaw\deg` | deg | 增加偏航角（相对） | `yaw\90` |
| `pitch\deg` | deg | 增加俯仰角（相对） | `pitch\-30` |
| `setYaw\deg` | deg | 设置偏航角（绝对） | `setYaw\180` |
| `setPitch\deg` | deg | 设置俯仰角（绝对） | `setPitch\0` |
| `alias\payload` / `alias\"payload"` | payload | 创建/替换别名。如果你想避免引号，可以将负载写成一个令牌并使用 `;`（它将被转换为空格）。 | `alias\jump;+jump;wait\1;-jump` |
| `bind\payload` / `bind\"payload"` | payload | 绑定按键。如果你想避免引号，将负载写成一个令牌并使用 `;`（它将被转换为空格）。 | `bind\mouse4;+bow` |
| `unbind\key` | key | 解绑按键。（不需要特殊的 `;` 处理。） | `unbind\g` |
| `say\text` / `say\"text"` | text | 发送聊天消息。`;` 在这里不是特殊字符（它会按字面发送）。只有文本有空格时才需要引号。 | `say\"hello world"` |
| `sendCommand\cmd` / `sendCommand\"cmd"` | cmd | 发送命令（不带开头的 `/`）。`;` 在这里不是特殊字符。只有命令有空格时才需要引号。 | `sendCommand\"gamemode creative"` |

#### `swapSlot` 的槽位编号

槽位遵循 Minecraft 的内部索引（在本模组的 UI 文档中）：

- `1-9` → 快捷栏
- `10-36` → 物品栏
- `37-40` → 装备槽位（37 脚部 … 40 头部）
- `41` → 副手

### 2) 无参数的别名（直接动作）

这些默认可用，对于按下/释放模式很方便：

| 别名 | 等价于 | 功能 |
|---|---|---|
| `+attack` / `-attack` | `builtinAttack\1` / `builtinAttack\0` | 按住/释放左键 |
| `+use` / `-use` | `builtinUse\1` / `builtinUse\0` | 按住/释放右键 |
| `+forward` / `-forward` | `builtinForward\1` / `builtinForward\0` | 按住/释放前进 |
| `+back` / `-back` | `builtinBack\1` / `builtinBack\0` | 按住/释放后退 |
| `+left` / `-left` | `builtinLeft\1` / `builtinLeft\0` | 按住/释放左移 |
| `+right` / `-right` | `builtinRight\1` / `builtinRight\0` | 按住/释放右移 |
| `+jump` / `-jump` | `builtinJump\1` / `builtinJump\0` | 按住/释放跳跃 |
| `+sneak` / `-sneak` | `builtinSneak\1` / `builtinSneak\0` | 按住/释放潜行 |
| `+sprint` / `-sprint` | `builtinSprint\1` / `builtinSprint\0` | 按住/释放疾跑 |
| `drop` / `dropStack` | `builtinDrop\0` / `builtinDrop\1` | 丢弃一个/整个堆叠 |
| `swapHand` | — | 交换主手和副手 |
| `cyclePerspective` | — | 循环摄像机视角 |
| `FPS` / `TPS` / `TPS2` | `builtinSetPerspective\0/1/2` | 设置特定视角 |
| `+silent` / `-silent` | `builtinSilent\1` / `builtinSilent\0` | 抑制/恢复绑定/别名反馈消息 |
| `reloadCFG` | — | 重新加载配置文件 |

---

## 示例

### 鞘翅 + 烟花（按住）

放置：
- 鞘翅在槽位 `10`（物品栏第一行，第一个槽位）
- 烟花在槽位 `19`（物品栏第二行，第一个槽位）

然后：

- `/alias equipElytra swapSlot\10\39`
- `/alias jump +jump wait\1 -jump`
- `/alias +fly equipElytra jump wait\1 jump swapSlot\19 +use -use`
- `/alias -fly equipElytra swapSlot\19`
- `/bind mouse5 +fly`

### 快速使用弓而不占用快捷栏槽位

将你的弓放在槽位 `11`：

- `/alias +bow swapSlot\11 +use`
- `/alias -bow -use swapSlot\11`
- `/bind mouse4 +bow`

### 切换绑定模式（状态开关）

你可以通过每次按键时重新绑定到不同的别名来创建状态开关。当你希望状态在释放按键后仍然保持时（不同于按住模式），这很有用。

#### 完整的鞘翅切换示例

准备：
- 鞘翅放在槽位 `10`（物品栏第一行，第一格）
- 烟花放在槽位 `26`（物品栏第三行，第一格）

配置文件设置：

```
# 定义可复用的装备别名
alias +equipElytra swapSlot\10\39
alias -equipElytra swapSlot\10\39
alias +holdFireworks swapSlot\26\41
alias -holdFireworks swapSlot\26\41

# 定义跳跃辅助
alias jump +jump wait\1 -jump

# 定义实际的飞行动作
alias +fly +equipElytra jump wait\1 jump +holdFireworks +use -use
alias -fly -equipElytra -holdFireworks

# 状态开关：创建两个来回切换的状态
alias fly1 bind\"mouse5 fly2" +fly
alias fly2 bind\"mouse5 fly1" -fly

# 初始绑定
bind mouse5 fly1
```

工作原理：
1. 按下 `mouse5` → 执行 `fly1` → 将 `mouse5` 重新绑定到 `fly2` → 运行 `+fly`（装备鞘翅并激活）
2. 再次按下 `mouse5` → 执行 `fly2` → 将 `mouse5` 绑定回 `fly1` → 运行 `-fly`（卸下鞘翅）
3. 状态在释放按键后仍然保持（这是与按住模式的关键区别）

#### 使用 `+silent/-silent` 避免聊天刷屏

频繁重新绑定按键时，你会看到"Bound key..."消息。使用静默模式来抑制它们：

```
# 用静默模式包裹绑定命令
alias fly1 +silent bind\"mouse5 fly2" -silent +fly
alias fly2 +silent bind\"mouse5 fly1" -silent -fly
bind mouse5 fly1
```

或者保持简洁——从别名内部调用 `bind` 内置别名时默认就是静默的：

```
# bind 命令不会刷屏，但 +fly/-fly 会显示正常的反馈
alias fly1 bind\"mouse5 fly2" +fly
alias fly2 bind\"mouse5 fly1" -fly
bind mouse5 fly1
```

#### 切换模式 vs 按住模式对比

**切换模式**（状态开关）：
- 使用 `bind mouse5 fly1` 配合重新绑定逻辑
- 状态在释放按键后仍然保持
- 按一次激活，再按一次关闭
- 适用于：可切换的模式、装备交换

**按住模式**：
- 直接使用 `bind mouse5 +fly`
- 按下时激活，释放时关闭
- 必须按住按键才能保持激活
- 适用于：临时动作、蓄力释放机制

两种模式可以使用相同的 `+fly/-fly` 别名！

---

## 注意事项/限制

- 在以下情况下，按键绑定触发会被忽略：聊天输入、告示牌编辑器、书本编辑器、命令方块界面。
- 某些服务器的反作弊系统可能会对自动化操作产生怀疑。请负责任地使用。

---

## 许可证

[CC0-1.0](LICENSE)
