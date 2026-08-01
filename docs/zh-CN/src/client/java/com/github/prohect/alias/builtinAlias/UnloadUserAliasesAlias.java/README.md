# UnloadUserAliasesAlias

移除运行时创建的用户别名的一次性别名。用法：`unloadUserAliases`。CFG 加载的别名和预定义别名保留。

## 字段

_无公共/受保护字段。_

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | 移除 `!isFromCFG() && !isPredefined()` 的 `UserAlias` 实例 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/README.md) | 移除所有运行时创建的条目 |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/README.md) | 移除 CFG 加载的别名（反向操作） |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
