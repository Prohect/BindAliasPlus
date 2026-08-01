# args 字段（src/client/java/com/github/prohect/alias/AliasRecord.java）

## 语法

```java
public String args()
```

## 返回值

本次别名调用的参数字符串；无参数别名为 `""`。已按 `Alias.divider4AliasArgs` 拆分——不包含别名名称或反斜杠分隔符。

## 备注

这是 record 组件访问器。对于像 `swapSlot\1\c2` 这样的定义，`aliasName` 为 `"swapSlot"`，`args` 为 `"1\c2"`（剩余标记用 `\` 拼接的原始形式）。对于像 `esc` 这样的无参数别名，`args` 为 `""`。

只有 `AliasWithArgs` 实例会使用此值。当查找到 `AliasWithoutArgs` 时，`UserAlias.run()` 总是传入 `""`。

当 `UserAlias` 在 `WaitAlias` 之后重建延迟链时，会用 `Alias.divider4AliasArgs` 反斜杠重新拼接 `aliasName` 和 `args`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [aliasName](aliasName.md) | 对应的别名名称 |
| [AliasRecord](AliasRecord.md) | 所属的 record 类型 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
