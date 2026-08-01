# UnloadUserVarsAlias（src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserVarsAlias.java）

移除所有在运行时创建（非 CFG）的变量（包括通用变量和容器槽位变量）的一次性别名。继承 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.UnloadUserVarsAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadUserVarsAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `unloadUserVars`（内部，以 `unloadUserVars` 形式暴露）。

**行为：** 从两个变量映射中移除所有运行时创建的变量：
1. `GENERAL_VARIABLES` — 移除名称不在 `CFG_VARIABLES` 中的条目。
2. `CONTAINER_SLOT_VARIABLES` — 移除名称不在 `CFG_CONTAINER_SLOT_VARIABLES` 中的条目。

**不受影响的内容：** 从 CFG 加载的变量（在 `CFG_VARIABLES` 和 `CFG_CONTAINER_SLOT_VARIABLES` 中跟踪的）。

**静默模式：** 当 `BindAliasClient.silentMode` 为 false 时，记录一条详细消息：`"Removed {total} runtime variable(s) ({generalCount} general, {containerCount} container_slot)"`。

**与 `unloadCFGVars` 的关键区别：** 此别名同时清理通用变量**和**容器槽位变量。`UnloadCFGVarsAlias` 只清理通用变量（它**不**访问 `CONTAINER_SLOT_VARIABLES` 或 `CFG_CONTAINER_SLOT_VARIABLES`）。

**用例：** 清理游戏/自动化过程中创建的临时变量，同时保留 CFG 中永久配置的变量。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/UnloadUserAllAlias.md) | 移除所有运行时创建的条目 |
| [UnloadUserAliasesAlias](../UnloadUserAliasesAlias.java/UnloadUserAliasesAlias.md) | 移除运行时创建的别名 |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/UnloadUserBindsAlias.md) | 移除运行时创建的按键绑定 |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md) | 移除 CFG 加载的变量（反向操作，仅通用） |
| [VarAlias](../VarAlias.java/VarAlias.md) | 变量系统 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
