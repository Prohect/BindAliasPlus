# Java 文档撰写指南

本文是 [docs/README.md](../README.md) 的扩展——请先阅读该文档了解通用结构、路径结构和交叉引用规则。本文只补充 Java 特有的约定。

## 源码布局

```
src/client/java/com/github/prohect/   ← Fabric client-only sources (mixins, aliases, MCP)
src/main/java/com/github/prohect/     ← common sources (entry point, shared config)
```

生成的文档占位文件在 `docs/en-US/src/` 下与此结构对应。运行 `./gradlew build`，然后执行 `bash scripts/generate_docs.sh` 来补齐缺失的占位文档。

## 架构概览

### 入口点

| 类 | 作用 |
|-------|------|
| `BindAlias`（主源集） | 模组初始化——记录启动日志，设置 `MOD_ID` |
| `BindAliasClient`（客户端源集） | 客户端初始化——注册所有别名、按键绑定、界面黑名单，加载 CFG，启动 MCP 服务器 |

### 别名层级

```
Alias (interface)
├── AliasWithArgs         — builtin aliases that accept arguments
│   └── BuiltinAliasWithArgs (abstract)
│       ├── BuiltinAliasWithBooleanArgs  — +flag / -flag aliases (attack, use, forward, …)
│       ├── BuiltinAliasWithIntegerArgs  — aliases taking integer args (slot, wait, yaw, pitch)
│       ├── BuiltinAliasWithDoubleArgs   — aliases taking float args (setYaw, setPitch)
│       ├── BuiltinAliasWithStringArgs   — aliases taking string args (say, bind, sendCommand)
│       ├── LockAlias                    — direct arg-based lock (builtinLock\action\flag)
│       └── VarAlias                     — variable storage (var\name\source)
├── AliasWithoutArgs       — aliases triggered by key events (no args)
│   └── BuiltinAliasWithoutArgs (abstract)
│       ├── Single-action (esc, toggleInventory, swapHand, …)
│       └── LockAlias_OnLock / LockAlias_Unlock — +lockKey / -lockKey wrappers
└── UserAlias              — user-defined aliases from CFG or `alias` builtin
```

关键 record：`AliasRecord`、`WaitAliasRecord`、`BindAliasKeyBinding`、`KeyPressed`

### 注册模式

每个内置别名都通过 builder 链在 `BindAliasClient.onInitializeClient()` 中注册：

```java
new SomeAlias()
    .putToAliasesWithArgs()           // or putToAliasesWithoutArgs()
    .addToScreenBlackList()           // optional — suppress on screens
    ;
```

- `putToAliasesWithArgs` — 按内置别名名注册（如 `"slot"`、`"var"`）
- `putToAliasesWithArgs_notSuggested` — 仅供内部使用（如 `"builtinDrop"`）
- `putToAliasesWithoutArgs` / `putToAliasesWithoutArgs_notSuggested` — 无参数别名的同类注册方式
- `addToScreenBlackList()` — 当任意界面打开时抑制该别名（在 `UserAlias.run()` 中检查）

### 开关别名（+/- 模式）

继承 `BuiltinAliasWithBooleanArgs` 的别名响应 `+name` / `-name`：

- `parseArgs(args)` 根据 `"0"`（关）或 `"1"`（开）设置 `this.flag`
- `run("1")` → 按下，`run("0")` → 松开
- `reapplyToGameKeyMapping()` 在界面切换后调用——默认行为：若 `flag` 为 true，则以 `"1"` 重新执行
- 界面抑制：对文本输入界面，在 `run()` 开头检查 `Alias.isUnderTextInputScreen()`；对动作仍然生效的非文本界面，检查 `Alias.isUnderAnyScreen()`

### 变量系统

`VarAlias` 以名称为键存储 `Number` 值：

| 映射表 | 用途 |
|-----|---------|
| `GENERAL_VARIABLES` | 所有变量名 → Number（int 或 double） |
| `CONTAINER_SLOT_VARIABLES` | 变量名 → 容器槽位索引（从 1 开始），由 `cN` 数据来源设置；仅供 `SwapSlotAlias` 读取 |

数据来源：`hotbarSlot`/`selectedSlot`、`itemsOfSlot0`-`itemsOfSlot9`、`pitch`、`yaw`、`cN` 或字面数字。

供其他别名使用的解析器：`resolveValue(input)`、`resolveInt(input)`、`resolveDouble(input)`。

CFG 自动加载跟踪：`CFG_VARIABLES` 和 `CFG_CONTAINER_SLOT_VARIABLES` 记录哪些变量是从 cfg 加载的，以便 `unloadCFGVars` 清理它们。

### 界面类型辅助方法（Alias 接口）

| 方法 | 返回 true 的条件 |
|--------|-------------------|
| `isUnderTextInputScreen()` | 聊天界面、告示牌、书、命令方块界面打开时 |
| `isUnderAnyScreen()` | 任意界面打开时 |
| `isInContainerScreen()` | 容器界面（AbstractContainerScreen）打开时 |
| `isInInventoryScreen()` | 玩家物品栏界面打开时 |
| `isInCreativeInventoryScreen()` | 创造模式物品栏界面打开时 |

### Mixin 模块

所有 mixin 都注入原版 Minecraft 类。文档中需写明注入点（`@At`）、方法和用途。

| Mixin | 注入目标 | 用途 |
|-------|-------------|---------|
| `MinecraftClientMixin` | `Minecraft.tick()` | 刻驱动器：界面跟踪、WaitAlias 计时器、持续丢弃、MCP nap |
| `KeyboardInputMixin` | `KeyboardInput.tick()` | 将 `+forward`/`+back`/`+left`/`+right` 注入移动逻辑 |
| `KeyBoardMixin` | `KeyboardHandler.keyPress()` | 将按键事件路由到 `KEY_QUEUE` |
| `MouseMixin` | `MouseHandler` | 鼠标事件路由；freeCursor 光标捕获抑制 |
| `ClientPacketListenerMixin` | `ClientPacketListener` | 服务器断开连接 → 清除锁定 |
| `AbstractContainerScreenMixin` | `AbstractContainerScreen` | 容器界面槽位管理 |
| `ChatComponentMixin` | 聊天渲染 | 静默模式聊天消息抑制 |
| `NativeImageMixin` | 原生图像 | 截图捕获钩子 |

### MCP 模块

运行在 localhost 上的 HTTP JSON-RPC 服务器。关键类：

| 类 | 作用 |
|-------|------|
| `McpHttpServer` | HTTP 服务器、请求路由、nap 任务 |
| `StateTracker` | 游戏状态快照采集 |
| `ScreenshotCapture` | 截图拍摄与编码 |
| `SoundCapture` | 声音事件采集 |
| `GameChannels` | MCP 协议通道定义 |
| `RecipeBookHelper` | 为 `listRecipes`/`applyRecipe` 查找配方 |
| `GameStateCollector` | 聚合多个跟踪器的状态 |

## 类 / 接口 / 枚举 / Record（`<Type>.md`）

填写 **备注**：用途、生命周期（单例？还是每次调用创建？）、线程安全、主要协作者。
对于抽象基类，说明子类必须履行的契约。
对于 record，说明每个组件保存的内容以及不可变性保证。
填写 **另请参阅**：父接口/已实现接口、子类型、重度使用方。

## 方法（`<method>.md`）

根据签名填写 **参数**。填写 **备注**：算法（逐步说明）、副作用（状态变更、日志、网络调用、界面变化）、调用方、错误处理。对于非 void 方法，说明返回值。

对于别名的 `run()` 方法，需要特别说明：
- 参数格式（如 `+attack`/`-attack`、`slot\3`、`var\name\source`）
- 界面抑制行为（哪些界面会阻止该别名）
- 错误日志模式（参数无效时会记录什么）
- 对于 BooleanArgs 别名：说明按下（1）/ 松开（0）的行为
- 对于启用重新应用的别名：说明 `reapplyToGameKeyMapping()` 的行为

## 字段（`<field>.md`）

只有 public/protected 字段会生成占位文档。填写 **备注**：存储的内容、谁读写它、线程安全、默认值。

对于静态可变状态（如 `GENERAL_VARIABLES`、`KEY_QUEUE`）：
- 说明生命周期（何时填充、何时清空）
- 注明线程约束（仅游戏线程）
- 列出读写方

## 静态初始化块（`static-init.md`）

仅当源码中有显式的 `static { }` 块时才生成。说明初始化了什么、为什么用静态块而不是字段初始化器，以及失败模式。常见于使用反射的 mixin（如 `McScreenHelper` 的分支检测）。

## README.md（源文件级概览）

填写 **字段** 表（所有字段、类型、一行说明）。填写 **方法** 表（所有 public/protected 方法、精简签名、一行说明）。按类别分组（生命周期、命令处理、工具方法等）。在 **另请参阅** 中填写相关类型。

## 映射分支

本项目面向多个使用不同映射的 Minecraft 版本。当方法/类名在不同分支间有差异时，请在文档的语法块或备注中注明映射情况：

- **Mojang** (26.x): `MultiPlayerGameMode`, `AbstractContainerMenu`, `Component`, `Minecraft`
- **Yarn** (1.21.x): `ClientPlayerInteractionManager`, `ScreenHandler`, `Text`, `MinecraftClient`

## 提交页脚

每个文档文件都以提交 SHA 页脚结尾。不要删除或修改它——生成器用它进行过期检查。
