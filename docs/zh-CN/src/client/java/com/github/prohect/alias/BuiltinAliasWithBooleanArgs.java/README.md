# BuiltinAliasWithBooleanArgs

`+`/`-` 开关类别名（attack、use、forward、back、left、right、jump、sneak、sprint、drop、playerList、advancements、silent、freeCursor）的抽象基类。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [flag](flag.md) | `boolean` | 当前状态：按住时为 `true`，松开时为 `false` |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [parseArgs](parseArgs.md) | `void parseArgs(String args)` | 将 `"0"`/`"1"` 解析为 `flag` |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | `void reapplyToGameKeyMapping()` | 界面切换后重新同步按住的按键 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 父类 |
| [ReapplyAlias](builtinAlias/ReapplyAlias.java/ReapplyAlias.md) | 在所有布尔别名上触发重新应用 |
| [builtinAlias](builtinAlias/README.md) | 具体的 `+attack`、`+use`、`+forward`、... 实现 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
