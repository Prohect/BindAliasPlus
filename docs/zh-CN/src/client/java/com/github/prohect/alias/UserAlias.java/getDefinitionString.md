# getDefinitionString 方法（src/client/java/com/github/prohect/alias/UserAlias.java）

## 语法

```java
public String getDefinitionString()
```

## 返回值

用于构造此 `UserAlias` 的原始定义字符串——以空格分隔的别名链，例如 `"+attack slot\1 wait\5 -attack"`。

## 备注

返回构造时设置的 `args` 字段。这是原始、未解析的定义字符串。构造后永远不会被修改。

`AliasAlias` 内置别名使用它来检索现有别名的当前定义（用于显示或修改）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasAlias](builtinAlias/AliasAlias.java/AliasAlias.md) | 读取此方法以显示现有定义的 `alias` 内置别名 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
