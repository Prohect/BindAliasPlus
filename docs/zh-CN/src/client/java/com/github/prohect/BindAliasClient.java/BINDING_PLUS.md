# BINDING_PLUS 字段（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
public static final java.util.Map<com.mojang.blaze3d.platform.InputConstants$Key, com.github.prohect.BindAliasKeyBinding> BINDING_PLUS
```

## 备注

所有生效的 按键→别名 绑定。以 Minecraft 的 `InputConstants.Key`（表示键盘按键或鼠标按键）为键。每个值是一个 `BindAliasKeyBinding`，指定按下时调用哪个别名、松开时调用哪个别名。由 CFG 自动加载（`bind` / `bindByAliasName` 行）和运行时 `/bind` 命令填充。`MinecraftClientMixin` 的 tick 循环消费它来分发 `KEY_QUEUE` 事件。`fromCFG=true` 的条目可通过 `unloadCFGBinds` 移除。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
