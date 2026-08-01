# aliasName 字段（src/client/java/com/github/prohect/alias/AliasRecord.java）

## 语法

```java
public String aliasName()
```

## 返回值

要在全局注册映射中查找的别名名称。

## 备注

这是 record 组件访问器（由 Java `record` 自动生成）。该值在构造时设置，之后永远不会改变——`AliasRecord` 是不可变的。

当 `UserAlias.decodeArgs2Alias()` 解析像 `slot\3` 这样的定义时，第一个标记（`"slot"`）会成为 `aliasName`。`UserAlias.run()` 随后使用此名称在 `Alias.aliasesWithoutArgs`、`Alias.aliasesWithArgs` 等映射中查找别名。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [args](args.md) | 对应的参数字符串 |
| [AliasRecord](AliasRecord.md) | 所属的 record 类型 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
