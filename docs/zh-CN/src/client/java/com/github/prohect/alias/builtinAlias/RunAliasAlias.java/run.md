# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/RunAliasAlias.java）

将参数拆分为别名名称和额外参数，然后查找并调用该别名。

## 语法

```java
public com.github.prohect.alias.builtinAlias.RunAliasAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 别名名称，可选后跟 `\` 分隔的额外参数（例如 `"slot\3"`、`"myAlias"`） |

## 备注

**算法：**

1. 如果 `args` 为 null 或空白，记录一条警告并返回。
2. 在第一个 `\`（反斜杠，`divider4AliasArgs`）处拆分：
   - 拆分前 → `aliasName`（去除空白）
   - 拆分后 → `extraArgs`（如果没有拆分则为空字符串）
3. 按顺序搜索注册表：`aliasesWithoutArgs`、`aliasesWithoutArgs_notSuggested`、`aliasesWithArgs`、`aliasesWithArgs_notSuggested`。
4. 如果找到别名，调用 `alias.run(extraArgs)`。
5. 如果未找到，记录一条警告：`"Unknown alias: {aliasName}"`。

**返回值：** `this`（流畅式返回——不返回被调用别名的返回值）。

**错误处理：**
- 参数为 null/空白：记录警告并返回。
- 未知别名名称：记录警告并返回。

**副作用：** 调用解析出的别名的 `run()` 方法，根据别名不同可能产生各种副作用。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [RunAliasAlias](RunAliasAlias.md) | 类概览 |
| [UserAlias](../../UserAlias.java/UserAlias.md) | 链式执行（空格分隔的别名定义） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
