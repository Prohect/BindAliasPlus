# BuiltinAliasWithArgs（src/client/java/com/github/prohect/alias/BuiltinAliasWithArgs.java）

## 语法

```java
public abstract class BuiltinAliasWithArgs<T extends BuiltinAliasWithArgs<T>> implements AliasWithArgs<T>
```

所有接受参数的内置别名的抽象基类。存储用于注册的 `builtinAliasName`，并提供以 `this.builtinAliasName` 作为注册键的无参 `putToAliasesWithArgs()` / `putToAliasesWithArgs_notSuggested()` 重载。

## 备注

每个接受参数的具体内置别名都扩展自某个类型化子类（`BuiltinAliasWithBooleanArgs`、`BuiltinAliasWithIntegerArgs`、`BuiltinAliasWithDoubleArgs`、`BuiltinAliasWithStringArgs`），但注册本身发生在此层级。

构造函数接受 `String builtinAliasName`——即进入全局 `Alias.aliasesWithArgs` 映射的名称。当 `UserAlias.run()` 在别名链中遇到 `aliasName` 时，就是与此名称进行匹配。

**子类契约**：具体子类必须实现 `run(String args)`。它们通常会先调用 `parseArgs(args)` 设置类型化的 `flag` 字段，然后执行别名操作。定义了自己的 `divider4AliasDefinition` 的子类（如 `BuiltinAliasWithStringArgs` 使用 `;`）会在构造别名链时覆盖默认的空格分隔符。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasWithArgs](AliasWithArgs.java/AliasWithArgs.md) | 此类实现的标记接口 |
| [BuiltinAliasWithBooleanArgs](BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | `+`/`-` 开关类别名的子类 |
| [BuiltinAliasWithIntegerArgs](BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | 整数参数别名的子类（slot、wait、yaw、pitch） |
| [BuiltinAliasWithDoubleArgs](BuiltinAliasWithDoubleArgs.java/BuiltinAliasWithDoubleArgs.md) | 双精度参数别名的子类（setYaw、setPitch） |
| [BuiltinAliasWithStringArgs](BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | 使用 `;` 定义分隔符的字符串参数别名的子类 |
| [builtinAlias](builtinAlias/README.md) | 具体实现 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
