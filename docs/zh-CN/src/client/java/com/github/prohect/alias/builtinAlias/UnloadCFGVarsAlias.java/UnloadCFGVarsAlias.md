# UnloadCFGVarsAlias（src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGVarsAlias.java）

移除所有从配置文件（CFG）加载的变量的一次性别名。继承 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.UnloadCFGVarsAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadCFGVarsAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `unloadCFGVars`（内部，以 `unloadCFGVars` 形式暴露）。

**行为：** 遍历 `VarAlias.CFG_VARIABLES`（跟踪哪些变量从 CFG 加载的集合），从 `VarAlias.GENERAL_VARIABLES` 和 `VarAlias.CFG_VARIABLES` 中移除每个变量名称。

**不受影响的内容：**
- 运行时创建的变量（运行时通过 `var` 别名创建）——它们不在 `CFG_VARIABLES` 中。
- `CONTAINER_SLOT_VARIABLES` 中的容器槽位变量——此别名**不**清理它们。`CFG_CONTAINER_SLOT_VARIABLES` 集合由 `UnloadUserVarsAlias` 引用以进行清理。

**关于 CONTAINER_SLOT_VARIABLES 的说明：** 此别名**只**清理通用变量。容器槽位变量不在 `CFG_VARIABLES` 中跟踪——它们改用 `CFG_CONTAINER_SLOT_VARIABLES`，由 `UnloadUserVarsAlias` 或 `UnloadCFGAllAlias` 间接清理。

**静默模式：** 当 `BindAliasClient.silentMode` 为 false 时，记录包含计数的 info 消息。

**用例：** 在重新加载 CFG 之前移除 CFG 定义的变量。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md) | 移除所有 CFG 加载的条目 |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md) | 移除 CFG 加载的别名 |
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | 移除 CFG 加载的按键绑定 |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/UnloadUserVarsAlias.md) | 移除运行时创建的变量（反向操作） |
| [VarAlias](../VarAlias.java/VarAlias.md) | 变量系统 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
