# BindAliasKeyBinding（src/client/java/com/github/prohect/BindAliasKeyBinding.java）

## 语法

```java
public final class com.github.prohect.BindAliasKeyBinding extends java.lang.Record
```

## 静态初始化

_无。_

## 备注

一个 Java `record`，将按键映射到按下和松开该按键时运行的别名。只有 `AliasWithoutArgs` 别名能被按键事件触发，因为按键绑定不传递参数 —— 底层以空参数字符串调用 `UserAlias.run("")`。

每个记录有三个组件：
- `aliasNameOnKeyPressed` —— 按键按下时调用的别名。
- `aliasNameOnKeyReleased` —— 按键松开时调用的别名（对于只有按下动作才重要的一次性按键动作，可为空字符串）。
- `fromCFG` —— 跟踪此绑定是否从配置文件加载，使 `unloadCFGBinds` 可以清理它。

一个便捷构造函数将运行时创建的绑定的 `fromCFG` 默认为 `false`。

由于是 record，因此不可变。实例存储在 `BindAliasClient.BINDING_PLUS` 中，`MinecraftClientMixin` 每刻排空 `KEY_QUEUE` 时查找它们。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BINDING_PLUS](../BindAliasClient.java/BINDING_PLUS.md) | 存放这些绑定的映射 |
| [KeyPressed](../KeyPressed.java/KeyPressed.md) | 触发 `BINDING_PLUS` 中别名查找的按键事件 |
| [commandBindExecute](../BindAliasClient.java/commandBindExecute.md) | 在运行时创建这些绑定 |
| [commandBindByAliasNameExecute](../BindAliasClient.java/commandBindByAliasNameExecute.md) | 按别名名称创建这些绑定 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
