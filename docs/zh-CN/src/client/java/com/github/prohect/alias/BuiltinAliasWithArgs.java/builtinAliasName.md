# builtinAliasName 字段（src/client/java/com/github/prohect/alias/BuiltinAliasWithArgs.java）

## 语法

```java
public final @NotNull String builtinAliasName
```

用于注册和查找的名称。由构造函数设置，之后永远不会改变。带 `@NotNull` 注解。

## 备注

每个具体的内置别名都在其构造函数中将名称传给 `super(name)`。`putToAliasesWithArgs()`（无参重载）随后将此名称用作全局 `Alias.aliasesWithArgs` 映射的键。

例如，`SlotAlias` 传入 `"slot"`，因此 `new SlotAlias().putToAliasesWithArgs()` 将其注册为 `aliasesWithArgs["slot"]`。

`final` 修饰符确保名称在构造后无法更改——每个实例恰好代表一个别名身份。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [putToAliasesWithArgs](putToAliasesWithArgs.md) | 使用此字段作为注册键 |
| [BuiltinAliasWithoutArgs.builtinAliasName](BuiltinAliasWithoutArgs.java/builtinAliasName.md) | 无参数基类中的同名字段 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
