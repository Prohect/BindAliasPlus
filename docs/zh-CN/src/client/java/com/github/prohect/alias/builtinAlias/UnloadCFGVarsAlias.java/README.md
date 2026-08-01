# UnloadCFGVarsAlias

移除 CFG 加载的通用变量的一次性别名。用法：`unloadCFGVars`。容器槽位变量**不会**被清理。

## 字段

_无公共/受保护字段。_

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | 从 `GENERAL_VARIABLES` 和 `CFG_VARIABLES` 中移除所有 CFG 跟踪名称的变量 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/README.md) | 移除所有 CFG 加载的条目 |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/README.md) | 移除运行时变量（反向操作） |
| [VarAlias](../VarAlias.java/README.md) | 变量系统 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
