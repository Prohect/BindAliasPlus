# UnloadCFGBindsAlias

移除 CFG 加载的按键绑定并清理关联别名的一次性别名。用法：`unloadCFGBinds`。

## 字段

_无公共/受保护字段。_

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | 从 `BINDING_PLUS` 中移除 `fromCFG() == true` 的绑定 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/README.md) | 移除所有 CFG 加载的条目 |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/README.md) | 移除运行时绑定（反向操作） |
| [UnbindAlias](../UnbindAlias.java/README.md) | 基于服务器命令的解绑 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
