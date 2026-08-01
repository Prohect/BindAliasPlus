# currentScreen 字段（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public static net.minecraft.client.gui.screen.Screen currentScreen
```

## 备注

当前打开的 `Screen` 的缓存引用；没有打开的界面时为 `null`。由 `MinecraftClientMixin` 通过 `MinecraftClient.getInstance().currentScreen` 每个 tick 更新。别名运行逻辑读取它以决定界面抑制行为，`Alias.isUnderAnyScreen()` / `Alias.isUnderTextInputScreen()` / `Alias.isInContainerScreen()` 也读取它。只能在游戏线程上访问。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
