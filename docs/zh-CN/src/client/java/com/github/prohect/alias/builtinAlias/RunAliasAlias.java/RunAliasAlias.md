# RunAliasAlias（src/client/java/com/github/prohect/alias/builtinAlias/RunAliasAlias.java）

按名称执行已注册的别名并可传入额外参数的内置别名。继承 `BuiltinAliasWithStringArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.RunAliasAlias extends com.github.prohect.alias.BuiltinAliasWithStringArgs<com.github.prohect.alias.builtinAlias.RunAliasAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `builtinRunAlias`（内部，以 `builtinRunAlias` 形式暴露）。

**用途：** 允许一个别名按名称调用另一个别名。参数是别名名称，可选后跟反斜杠分隔的额外参数以传递给被调用的别名。

**用法模式：**
- `builtinRunAlias\slot\3` — 以参数 `3` 调用 `slot` 别名
- `builtinRunAlias\say\hello` — 以参数 `hello` 调用 `say` 别名
- `builtinRunAlias\myAlias` — 不带额外参数调用用户定义的别名 `myAlias`

**解析方式：** 在第一个 `\`（反斜杠）处拆分参数字符串：前面的部分是别名名称，后面的部分成为额外参数。然后按顺序搜索全部四个别名注册表：
1. `aliasesWithoutArgs`
2. `aliasesWithoutArgs_notSuggested`
3. `aliasesWithArgs`
4. `aliasesWithArgs_notSuggested`

如果找到，调用 `alias.run(extraArgs)`。如果未找到，记录一条警告。

**与 UserAlias 链的比较：** 在类似 `slot\3 say\hello` 的链定义中，每个空格分隔的 token 会自动被视为单独的别名调用（由 `UserAlias.run()` 处理）。`builtinRunAlias` 是在别名的 `run()` 方法内部或从 MCP 显式、以编程方式调用另一个别名的途径。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasAlias](../AliasAlias.java/AliasAlias.md) | 在运行时定义别名 |
| [UserAlias](../../UserAlias.java/UserAlias.md) | 用户定义别名链的执行 |
| [BuiltinAliasWithStringArgs](../../BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | 字符串参数别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
