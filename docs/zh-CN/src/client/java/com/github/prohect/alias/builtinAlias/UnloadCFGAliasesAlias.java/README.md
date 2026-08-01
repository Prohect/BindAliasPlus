# UnloadCFGAliasesAlias

移除 CFG 加载的用户别名的一次性别名。用法：`unloadCFGAliases`。运行时创建的别名不受影响。

## 字段

_无公共/受保护字段。_

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | 从 `aliasesWithoutArgs` 中移除所有 `isFromCFG() == true` 的 `UserAlias` 实例 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/README.md) | 移除所有 CFG 加载的条目 |
| [UnloadUserAliasesAlias](../UnloadUserAliasesAlias.java/README.md) | 移除运行时创建的别名（反向操作） |
| [ReloadCFGAlias](../ReloadCFGAlias.java/README.md) | 卸载后重新加载 CFG |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
