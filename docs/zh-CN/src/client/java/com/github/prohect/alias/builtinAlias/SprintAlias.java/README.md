# SprintAlias

疾跑的开关别名。用法：`+sprint` / `-sprint`。需要 `+forward` 才能实际移动。

## 字段

_无公共/受保护字段（从 `BuiltinAliasWithBooleanArgs` 继承 `flag`）。_

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | 解析 +/- 参数：1=按下疾跑键，0=松开 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SneakAlias](../SneakAlias.java/README.md) | 潜行键（与疾跑互斥） |
| [ForwardAlias](../ForwardAlias.java/README.md) | 前进移动（疾跑所需） |
| [ReapplyAlias](../ReapplyAlias.java/README.md) | 界面切换后重新应用按住的按键 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
