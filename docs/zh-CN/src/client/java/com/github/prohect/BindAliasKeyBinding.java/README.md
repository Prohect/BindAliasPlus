# BindAliasKeyBinding

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| [aliasNameOnKeyPressed](aliasNameOnKeyPressed.md) | `String` | 按下按键时调用的别名名称（可为空） |
| [aliasNameOnKeyReleased](aliasNameOnKeyReleased.md) | `String` | 松开按键时调用的别名名称（可为空） |
| [fromCFG](fromCFG.md) | `boolean` | 此绑定是否从 CFG 文件加载 |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| `BindAliasKeyBinding(String, String)` | 便捷构造函数 | 创建 `fromCFG = false` 的运行时绑定 |
| [equals](equals.md) | `boolean equals(Object)` | record 相等性 —— 三个组件必须都匹配 |
| [hashCode](hashCode.md) | `int hashCode()` | record 哈希 —— 由三个组件派生 |
| [toString](toString.md) | `String toString()` | record 字符串表示 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BINDING_PLUS](../BindAliasClient.java/BINDING_PLUS.md) | 存放这些绑定的映射 |
| [KeyPressed](../KeyPressed.java/KeyPressed.md) | 触发别名查找的按键事件 |
| [commandBindExecute](../BindAliasClient.java/commandBindExecute.md) | 在运行时创建这些绑定 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
