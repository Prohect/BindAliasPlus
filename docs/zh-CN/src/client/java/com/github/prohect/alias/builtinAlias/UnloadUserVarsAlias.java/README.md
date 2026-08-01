# UnloadUserVarsAlias

移除运行时创建变量（通用和容器槽位）的一次性别名。用法：`unloadUserVars`。

## 字段

_无公共/受保护字段。_

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | 从 `GENERAL_VARIABLES` 和 `CONTAINER_SLOT_VARIABLES` 中移除不在 CFG 跟踪集合中的变量 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/README.md) | 移除所有运行时创建的条目 |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/README.md) | 移除 CFG 加载的变量（反向操作） |
| [VarAlias](../VarAlias.java/README.md) | 变量系统 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
