# BuiltinAliasWithoutArgs（src/client/java/com/github/prohect/alias/BuiltinAliasWithoutArgs.java）

## 语法

```java
public abstract class BuiltinAliasWithoutArgs<T extends BuiltinAliasWithoutArgs<T>> implements AliasWithoutArgs<T>
```

不接受参数的内置别名的抽象基类。存储用于注册的 `builtinAliasName`，并提供以 `this.builtinAliasName` 作为注册键的无参 `putToAliasesWithoutArgs()` / `putToAliasesWithoutArgs_notSuggested()` 重载。

## 备注

这是所有由按键事件或在别名链中按名称触发的单动作内置别名的注册基类。由于它们不接受参数，因此可以通过 `BindAliasKeyBinding` 绑定到键盘按键。

构造函数接受 `String builtinAliasName`——即进入全局 `Alias.aliasesWithoutArgs` 映射的名称。当 `UserAlias.run()` 在别名链中遇到该名称时，就是与此名称进行匹配。

**具体子类**：`EscAlias`、`ToggleInventoryAlias`、`SwapHandAlias`、`PickItemAlias`、`CyclePerspectiveAlias`、`FpsAlias`、`TpsAlias`、`Tps2Alias`、`CloseScreenAlias`、`ScreenshotAlias`、`DebugOverlayAlias`、`ShutdownAlias`、`OpenInventoryAlias`、`LockAlias_OnLock`、`LockAlias_Unlock` 等。

**注意**：`UserAlias` 直接实现 `AliasWithoutArgs`——它**不**扩展此类。用户别名不是内置的。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [AliasWithoutArgs](AliasWithoutArgs.java/AliasWithoutArgs.md) | 此类实现的标记接口 |
| [BuiltinAliasWithArgs](BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | 带参数的对应类 |
| [UserAlias](UserAlias.java/UserAlias.md) | 非内置的 AliasWithoutArgs——用户定义的别名链 |
| [builtinAlias](builtinAlias/README.md) | 具体实现 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
