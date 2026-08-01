# ReapplyAlias

在界面切换后重新断言单个按住按键的内置别名。用法：`reapply\action`（例如 `reapply\forward`）。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [SUPPORTED_ACTIONS](SUPPORTED_ACTIONS.md) | `List<String>` | 用于命令建议的受支持动作名称 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | 将动作名称解析为内置别名，若按键被按住则调用 `reapplyToGameKeyMapping()` |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/README.md) | 提供 `reapplyToGameKeyMapping()` 的基类 |
| [WaitAlias](../WaitAlias.java/README.md) | 用于安排重新应用的延迟执行 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
