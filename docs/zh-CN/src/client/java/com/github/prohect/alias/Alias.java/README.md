# Alias

别名执行引擎的核心接口。声明全局注册表、解析工具、界面防护以及 `run` 契约。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [aliasesWithArgs](aliasesWithArgs.md) | `HashMap<String, AliasWithArgs<?>>` | 接受参数的内置建议别名 |
| [aliasesWithArgs_notSuggested](aliasesWithArgs_notSuggested.md) | `HashMap<String, AliasWithArgs<?>>` | 接受参数的内部 / 非建议别名 |
| [aliasesWithoutArgs](aliasesWithoutArgs.md) | `HashMap<String, AliasWithoutArgs<?>>` | 不接受参数的建议别名（内置 + 用户） |
| [aliasesWithoutArgs_notSuggested](aliasesWithoutArgs_notSuggested.md) | `HashMap<String, AliasWithoutArgs<?>>` | 不接受参数的内部 / 非建议别名 |
| [aliasesWithoutArgs_fromBindCommand](aliasesWithoutArgs_fromBindCommand.md) | `HashMap<String, AliasWithoutArgs<?>>` | 由 `bind` 命令创建、用于按键绑定查找的别名 |
| [blackList4Screen](blackList4Screen.md) | `List<Alias<?>>` | 任意界面打开时被抑制的别名（松开事件除外） |
| [divider4AliasDefinition](divider4AliasDefinition.md) | `char` | 链中别名调用之间的分隔符（空格 `' '`） |
| [divider4AliasArgs](divider4AliasArgs.md) | `char` | 别名名与参数之间的分隔符（反斜杠 `\`） |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `T run(String args)` | 核心契约 —— 以给定参数执行别名；返回 `this` 以支持链式调用 |
| [addToScreenBlackList](addToScreenBlackList.md) | `default T addToScreenBlackList()` | 构建器方法 —— 将此别名加入界面黑名单 |
| [getOppositeDefinition](getOppositeDefinition.md) | `static String getOppositeDefinition(String)` | 翻转链中的 `+`/`-` 前缀以用于锁定/松开 |
| [getDefinitions](getDefinitions.md) | `static @NotNull ArrayList<String> getDefinitions(String)` | 按空格拆分别名链，尊重双引号块 |
| [getDefinitionSplits](getDefinitionSplits.md) | `static @NotNull ArrayList<String> getDefinitionSplits(String)` | 按 `\` 将单个定义拆分为名称 + 参数 |
| [getCurrentScreen](getCurrentScreen.md) | `static Screen getCurrentScreen()` | `BindAliasClient.currentScreen` 的访问器 |
| [isUnderTextInputScreen](isUnderTextInputScreen.md) | `static boolean isUnderTextInputScreen()` | 聊天 / 告示牌 / 书 / 命令方块是否打开 |
| [isUnderAnyScreen](isUnderAnyScreen.md) | `static boolean isUnderAnyScreen()` | 任意界面是否打开 |
| [isInContainerScreen](isInContainerScreen.md) | `static boolean isInContainerScreen()` | `AbstractContainerScreen` 是否打开 |
| [isInInventoryScreen](isInInventoryScreen.md) | `static boolean isInInventoryScreen()` | 玩家物品栏界面是否打开 |
| [isInCreativeInventoryScreen](isInCreativeInventoryScreen.md) | `static boolean isInCreativeInventoryScreen()` | 创造模式物品栏界面是否打开 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) | 带参数别名的子接口 |
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | 无参数别名的子接口 |
| [UserAlias](UserAlias.java/UserAlias.md) | 主要调用方 —— 解析链并通过这些注册表分发给别名 |
| [BindAliasClient](BindAliasClient.java/BindAliasClient.md) | 别名注册进这些注册表的位置 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
