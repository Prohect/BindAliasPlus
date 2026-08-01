# alias

别名执行引擎——BindAlias 模组的核心。用户可以调用的每个命令（或模组内部使用的命令）都是一个**别名**：一个实现 [`Alias`](Alias.java/Alias.md) 接口的对象。

别名组织在根为 `Alias` 的类型层次结构中，包含两个顶层分支：

- **`AliasWithArgs`** —— 通过 `\` 分隔符接受参数的别名（例如 `slot\3`、`yaw\90`）。只有内置别名可以有参数；用户别名永远不会。
- **`AliasWithoutArgs`** —— 仅按名称（或按键事件）触发的别名。所有用户定义的别名和若干内置动作都属于此类。

每个别名在客户端初始化期间通过调用 `putToAliases*` 方法注册到 `Alias` 上的某个静态映射中。`UserAlias.run()` 方法在执行别名链时按名称从这些映射中查找别名。

## 推荐阅读顺序

| 顺序 | 条目 | 原因 |
|-------|------|--------|
| 1 | [Alias](Alias.java/Alias.md) | 根接口——注册映射、界面辅助方法、解析 |
| 2 | [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) / [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | 标记接口——接受参数与无参数别名的分界 |
| 3 | [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 所有接受参数的内置别名的抽象基类 |
| 4 | [BuiltinAliasWithBooleanArgs](BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) / [Integer](BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) / [Double](BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) / [String](BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | 类型化参数解析 |
| 5 | [BuiltinAliasWithoutArgs](BuiltinAliasWithoutArgs.java/BuiltinAliasWithoutArgs.md) | 无参数内置别名（可由按键触发）的抽象基类 |
| 6 | [UserAlias](UserAlias.java/UserAlias.md) | 来自 CFG 或 `alias` 命令的用户定义别名链 |
| 7 | [AliasRecord](AliasRecord.java/AliasRecord.md) | 不可变 record——一次带参数的别名调用，存储在延迟队列中 |

## 目录

| 名称 | 说明 |
|------|-------------|
| [Alias.java](Alias.java/README.md) | 根接口——注册映射、分隔符、界面辅助方法、解析 |
| [AliasRecord.java](AliasRecord.java/README.md) | record (aliasName, args)——存储在 WaitAlias 延迟队列中 |
| [AliasWithArgs.java](AliasWithArgs.java/README.md) | 接受参数的别名的标记接口 |
| [AliasWithoutArgs.java](AliasWithoutArgs.java/README.md) | 仅按名称触发的别名的标记接口 |
| [BuiltinAliasWithArgs.java](BuiltinAliasWithArgs.java/README.md) | 所有接受参数的内置别名的抽象基类 |
| [BuiltinAliasWithBooleanArgs.java](BuiltinAliasWithBooleanArgs.java/README.md) | `+`/`-` 开关类别名（attack、use、移动等）的基类 |
| [BuiltinAliasWithDoubleArgs.java](BuiltinAliasWithDoubleArgs.java/README.md) | 双精度参数别名（setYaw、setPitch）的基类 |
| [BuiltinAliasWithIntegerArgs.java](BuiltinAliasWithIntegerArgs.java/README.md) | 整数参数别名（slot、wait、yaw、pitch）的基类 |
| [BuiltinAliasWithStringArgs.java](BuiltinAliasWithStringArgs.java/README.md) | 字符串参数别名（say、sendCommand、alias、...）的基类 |
| [BuiltinAliasWithoutArgs.java](BuiltinAliasWithoutArgs.java/README.md) | 无参数内置别名的抽象基类 |
| [UserAlias.java](UserAlias.java/README.md) | 用户定义的别名链；主要的别名执行入口点 |
| [builtinAlias](builtinAlias/README.md) | 所有具体的内置别名实现 |

## 架构图

```
Alias (interface)
├── AliasWithArgs (marker interface)
│   └── BuiltinAliasWithArgs (abstract, stores builtinAliasName)
│       ├── BuiltinAliasWithBooleanArgs  ← +flag / -flag (attack, use, forward, …)
│       ├── BuiltinAliasWithIntegerArgs  ← slot, wait, yaw, pitch
│       ├── BuiltinAliasWithDoubleArgs   ← setYaw, setPitch
│       ├── BuiltinAliasWithStringArgs   ← say, sendCommand, alias, applyRecipe, …
│       ├── LockAlias                    ← builtinLock\action\flag
│       └── VarAlias                     ← var\name\source
├── AliasWithoutArgs (marker interface)
│   └── BuiltinAliasWithoutArgs (abstract, stores builtinAliasName)
│       ├── esc, toggleInventory, swapHand, pickItem, …
│       ├── LockAlias_OnLock / LockAlias_Unlock  ← +lockKey / -lockKey wrappers
│       └── UserAlias                   ← user-defined alias chains
└── (UserAlias implements AliasWithoutArgs directly)
```

## 关键概念

- **别名链**：以空格分隔的别名调用字符串，例如 `+attack slot\1 wait\5 -attack`。由 `Alias.getDefinitions()` 解析。
- **定义拆分**：单次别名调用中反斜杠分隔的字符串，例如 `slot\3`。由 `Alias.getDefinitionSplits()` 解析。
- **界面黑名单**：通过 `addToScreenBlackList()` 添加的别名在任何界面打开时会被抑制（只有松开事件 `"0"` 通过）。在 `UserAlias.run()` 中检查。
- **建议与不建议**：`_notSuggested` 注册映射用于不应出现在面向用户的命令建议中的内部别名。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
