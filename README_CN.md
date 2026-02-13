# BindAliasPlus

一个 Fabric **客户端**模组，允许你创建 **别名 (aliases)**（宏）并**将它们绑定到按键/鼠标按钮**，从而通过一次按键运行完整的动作序列 —— 类似于 Source 引擎游戏的“bind/alias 配置”工作流。

<!-- languages -->
- 🇺🇸 [English](README.md)
- 🇨🇳 [中文 (简体)](README_CN.md)

> 仅客户端：服务器无需安装此模组。

---

## 安装

1. 为你的 Minecraft 版本安装 [Fabric Loader](https://fabricmc.net/use/)。
2. 从 Modrinth 下载模组：https://modrinth.com/mod/bind-alias-plus/versions
3. 将 `.jar` 文件放入你的 Minecraft `mods` 文件夹。
4. 启动 Minecraft。

---

## 核心理念

你将 **别名 (alias)** 定义为一系列 **步骤 (steps)**，然后绑定一个按键来运行它。

- 步骤之间用 **空格** 分隔。
- 一个步骤可以是：
  - 无参数的别名：`jump`
  - 带参数的别名：`swapSlot\10\39` (参数之间用反斜杠 `\` 分隔)

你可以绑定按键，使得：
- **按下** 时运行一个别名
- **松开** 时运行另一个别名 (典型的 `+something` / `-something` 模式)

---

## 语法

### A) 别名定义按空格分隔

别名定义是一系列由 **空格 ` ` 分隔** 的步骤：

- `equipElytra jump wait\1 jump swapSlot\19 +use -use`

### B) **步骤参数** 按反斜杠 `\` 分隔

如果一个步骤包含 `\`，则第一个 `\` 之前的部分是别名名称，其余部分是它的参数：

- `swapSlot\10\39` → 别名名称 `swapSlot`, 参数：`10`, `39`
- `wait\20` → 别名名称 `wait`, 参数：`20`

### C) 单个 **步骤参数** 内包含空格

如果单个 **步骤参数** 必须包含空格，请将该参数包裹在 **双引号** 中。

这主要用于发送聊天/命令的内置功能，或用于嵌套定义（见下一节）。

---

## 嵌套定义 (重要)：`BuiltinAliasWithGreedyStringArgs` 和 `;`

一些内置别名接受 **贪婪字符串 (greedy string)** 载荷，因此你可以在另一个别名内部 定义 / 绑定 / 解绑 / 发送聊天 / 运行命令：

- `alias`
- `bind`
- `unbind`
- `say`
- `sendCommand`

### 关键技巧：使用 `;` 避免引号

外层解析总是按 **空格** 分割别名步骤。

因此，如果你编写的嵌套载荷包含空格，通常需要引号将其保持为单个参数。

但是，对于贪婪字符串内置命令，**你也可以避免使用引号**，方法是将嵌套载荷写成一个标记 (token)，并使用 **分号 `;`** 来分隔该标记内的各个部分。

示例 (两种方式均可)：

- 带引号 (载荷有空格)：
  - `/alias makeJumpAlias alias\"jump +jump wait\1 -jump"`

- 不带引号 (载荷为一个标记，使用 `;` 作为分隔符)：
  - `/alias makeJumpAlias alias\jump;+jump;wait\1;-jump`

### 贪婪字符串内置命令如何处理载荷

- `alias\...`, `bind\...`, `unbind\...`:
  - 它们会在向游戏发送最终聊天命令之前 **将 `;` 替换为空格 ` `**。
  - 这就是 `/alias makeJumpAlias alias\jump;+jump;wait\1;-jump` 能工作的原因：当 `makeJumpAlias` 被调用时，它发送聊天命令 `/alias jump +jump wait\1 -jump`。

- `say\...` 和 `sendCommand\...`:
  - 它们 **不会替换 `;`**。
  - 无论你在载荷中放入什么，都会原样发送（所以 `;` 保持为字面字符）。

---

## 命令

### `/alias <名称> <定义>`
创建或替换一个 **用户别名**。

注意：
- 你 **不能覆盖** 内置别名。
- 运行用户别名时，会将其定义展开为步骤。

示例：
- `/alias pearl swapSlot\12 +use wait\1 -use swapSlot\12`

### `/bind <按键> <定义-或-别名名称>`
绑定一个按键/鼠标按钮。

行为：
1. 如果 `<定义-或-别名名称>` 匹配现有的别名名称（包括 `+name`/`-name` 形式），则绑定到该别名。
2. 否则，将其视为内联定义，并为该绑定创建一个内部别名。

按下/松开 行为：
- 如果你的绑定定义包含 `+something` 和/或 `-something`，模组可以根据这些 `+/-` 步骤自动推导出“相反”的松开侧动作。

示例：
- `/bind g jump`
- `/bind g +forward wait\10 -forward`

### `/bindByAliasName <按键> <别名名称>`
直接将按键绑定到现有的别名名称。

示例：
- `/bindByAliasName mouse5 +fly`
- `/bindByAliasName g jump`

### `/unbind <按键>`
移除绑定。

示例：
- `/unbind mouse5`

### `/reloadCFG`
从磁盘重载配置文件。

---

## 配置文件

路径：
- `config/bind-alias-plus.cfg`

规则：
- 每行一个命令
- 开头的 `/` 是可选的
- `#` 开始注释行

编辑后重载：
- `/reloadCFG`

---

## 内置别名

BindAliasPlus 附带了内置别名，你可以在你的别名定义中调用它们。

### 1) 带参数的内置别名 (使用 `\` 分隔参数)

| 别名 | 参数 | 作用 | 示例 |
|---|---:|---|---|
| `log\text` | text | 将消息记录到控制台 (调试) | `log\Hello` |
| `slot\n` | `n=1..9` | 选择快捷栏槽位 | `slot\3` |
| `swapSlot\a\b` | `a,b` | 交换两个槽位 | `swapSlot\10\39` |
| `swapSlot\a` | `a` | 将槽位 `a` 与 **当前选定的快捷栏槽位** 交换 | `swapSlot\19` |
| `wait\ticks` | ticks | 延迟执行 (`20 ticks = 1 秒`) | `wait\20` |
| `yaw\deg` | deg | 增加偏航角 (相对) | `yaw\90` |
| `pitch\deg` | deg | 增加俯仰角 (相对) | `pitch\-30` |
| `setYaw\deg` | deg | 设置偏航角 (绝对) | `setYaw\180` |
| `setPitch\deg` | deg | 设置俯仰角 (绝对) | `setPitch\0` |
| `alias\payload` / `alias\"payload"` | payload | 创建/替换别名。如果你想避免使用引号，可以将载荷写成一个标记并使用 `;` (它将被转换为空格)。 | `alias\jump;+jump;wait\1;-jump` |
| `bind\payload` / `bind\"payload"` | payload | 绑定按键。如果你想避免使用引号，将载荷写成一个标记并使用 `;` (它将被转换为空格)。 | `bind\mouse4;+bow` |
| `unbind\key` | key | 解绑按键。(不需要特殊的 `;` 处理。) | `unbind\g` |
| `say\text` / `say\"text"` | text | 发送聊天消息。`;` 在这里不是特殊的 (按字面发送)。只有当文本有空格时才需要引号。 | `say\"hello world"` |
| `sendCommand\cmd` / `sendCommand\"cmd"` | cmd | 发送命令 (无开头的 `/`)。`;` 在这里不是特殊的。只有当命令有空格时才需要引号。 | `sendCommand\"gamemode creative"` |

#### `swapSlot` 的槽位编号

槽位遵循 Minecraft 内部索引（如此模组的 UI 文档所示）：

- `1-9` → 快捷栏 (hotbar)
- `10-36` → 物品栏 (inventory)
- `37-40` → 盔甲槽 (37 脚部 … 40 头部)
- `41` → 副手 (offhand)

### 2) 无参数别名 (直接动作)

默认可用，便于用于 按下/松开 模式：

| 别名 | 等同于 | 作用 |
|---|---|---|
| `+attack` / `-attack` | `builtinAttack\1` / `builtinAttack\0` | 按住/松开 左键 |
| `+use` / `-use` | `builtinUse\1` / `builtinUse\0` | 按住/松开 右键 |
| `+forward` / `-forward` | `builtinForward\1` / `builtinForward\0` | 按住/松开 前进 |
| `+back` / `-back` | `builtinBack\1` / `builtinBack\0` | 按住/松开 后退 |
| `+left` / `-left` | `builtinLeft\1` / `builtinLeft\0` | 按住/松开 向左 |
| `+right` / `-right` | `builtinRight\1` / `builtinRight\0` | 按住/松开 向右 |
| `+jump` / `-jump` | `builtinJump\1` / `builtinJump\0` | 按住/松开 跳跃 |
| `+sneak` / `-sneak` | `builtinSneak\1` / `builtinSneak\0` | 按住/松开 潜行 |
| `+sprint` / `-sprint` | `builtinSprint\1` / `builtinSprint\0` | 按住/松开 疾跑 |
| `drop` / `dropStack` | `builtinDrop\0` / `builtinDrop\1` | 丢弃一个 / 丢弃一组 |
| `swapHand` | — | 交换主手和副手 |
| `cyclePerspective` | — | 循环切换视角 |
| `FPS` / `TPS` / `TPS2` | `builtinSetPerspective\0/1/2` | 设置特定视角 |
| `+silent` / `-silent` | `builtinSilent\1` / `builtinSilent\0` | 抑制/恢复 绑定/别名 反馈消息 |
| `reloadCFG` | — | 重载配置文件 |

---

## 示例

### 鞘翅 + 烟花 (按下并按住)

放置：
- 鞘翅在槽位 `10` (物品栏第一行，第一格)
- 烟花在槽位 `19` (物品栏第二行，第一格)

然后：

- `/alias equipElytra swapSlot\10\39`
- `/alias jump +jump wait\1 -jump`
- `/alias +fly equipElytra jump wait\1 jump swapSlot\19 +use -use`
- `/alias -fly equipElytra swapSlot\19`
- `/bind mouse5 +fly`

### 快速切弓 (无需占用快捷栏槽位)

将你的弓放在槽位 `11`：

- `/alias +bow swapSlot\11 +use`
- `/alias -bow -use swapSlot\11`
- `/bind mouse4 +bow`

### 切换绑定模式 (状态切换器)

你可以通过在每次按下时将按键重新绑定到不同的别名来创建切换开关。这在你想让状态在松开按键后仍然保持时非常有用（不像按下并按住模式）。

#### 完整的鞘翅切换示例

放置：
- 鞘翅在槽位 `10` (物品栏第一行，第一格)
- 烟花在槽位 `26` (物品栏第三行，第一格)

配置文件设置：

```
# 定义可复用的装备别名
alias +equipElytra swapSlot\10\39
alias -equipElytra swapSlot\10\39
alias +holdFireworks swapSlot\26\41
alias -holdFireworks swapSlot\26\41

# 定义跳跃辅助
alias jump +jump wait\1 -jump

# 定义实际飞行此动作
alias +fly +equipElytra jump wait\1 jump +holdFireworks +use -use
alias -fly -equipElytra -holdFireworks

# 状态切换器：创建两个状态来回切换
alias fly1 bind\"mouse5 fly2" +fly
alias fly2 bind\"mouse5 fly1" -fly

# 初始绑定
bind mouse5 fly1
```

如何工作：
1. 按下 `mouse5` → 执行 `fly1` → 将 `mouse5` 重绑定为 `fly2` → 运行 `+fly` (装备鞘翅并激活)
2. 再次按下 `mouse5` → 执行 `fly2` → 将 `mouse5` 重绑定回 `fly1` → 运行 `-fly` (卸下鞘翅)
3. 即使松开按键，状态也会保持（这是与按下并按住模式的关键区别）

#### 使用 `+silent/-silent` 避免刷屏

频繁重绑定按键时，你会看到 "Bound key..." 消息。使用静默模式来抑制它们：

```
# 将 bind 命令包裹在静默模式中
alias fly1 +silent bind\"mouse5 fly2" -silent +fly
alias fly2 +silent bind\"mouse5 fly1" -silent -fly
bind mouse5 fly1
```

或者保持更简洁 —— 当从别名内部调用时，`bind` 内置命令默认已经是静默的：

```
# bind 命令不会刷屏，但 +fly/-fly 会显示正常反馈
alias fly1 bind\"mouse5 fly2" +fly
alias fly2 bind\"mouse5 fly1" -fly
bind mouse5 fly1
```

#### 切换 vs 按下并按住 对比

**切换模式** (状态切换器):
- 使用 `bind mouse5 fly1` 配合重绑定逻辑
- 松开按键后状态保持
- 按一次激活，再按一次取消激活
- 适用于：可切换的模式，装备交换

**按下并按住模式**:
- 直接使用 `bind mouse5 +fly`
- 按下时激活，松开时取消激活
- 必须按住按键才能保持激活
- 适用于：临时动作，蓄力释放机制

两种模式都可以使用相同的 `+fly/-fly` 别名！

---

## 注意事项 / 限制

- 当你在以下界面输入时，按键绑定触发会被忽略：聊天、告示牌编辑、书本编辑、命令方块界面。
- 自动化操作在某些服务器上可能被视为可疑（反作弊）。请负责任地使用。

---

## 许可证

[CC0-1.0](LICENSE)