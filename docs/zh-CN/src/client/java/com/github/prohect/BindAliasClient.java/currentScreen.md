# currentScreen 字段（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public static net.minecraft.client.gui.screens.Screen currentScreen
```

## 备注

当前打开的 `Screen` 的缓存引用，无打开界面时为 `null`。每刻由 `MinecraftClientMixin` 通过 `Minecraft.getInstance().screen` 更新。别名运行逻辑读取它来决定界面抑制行为，`Alias.isUnderAnyScreen()` / `Alias.isUnderTextInputScreen()` / `Alias.isInContainerScreen()` 也读取它。只能在游戏线程上访问。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
