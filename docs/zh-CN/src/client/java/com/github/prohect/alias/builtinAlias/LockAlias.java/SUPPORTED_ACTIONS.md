# SUPPORTED_ACTIONS 字段（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

所有受支持的按键动作类型的公共静态列表，带 `"gameKey:"` 前缀，用于命令建议。

## 语法

```java
public static final java.util.List<java.lang.String> SUPPORTED_ACTIONS
```

## 备注

包含：`gameKey:attack`、`gameKey:use`、`gameKey:forward`、`gameKey:back`、`gameKey:left`、`gameKey:right`、`gameKey:jump`、`gameKey:sneak`、`gameKey:sprint`。

此列表有双重用途：
1. **命令建议：** 暴露给命令建议系统，使自动补全能够提供有效的锁定目标。
2. **静态初始化种子：** 用于填充 `ACTION_ALIAS_PATTERNS`，它将每个裸动作名称映射到其别名名称模式（`+attack`、`-attack`、`builtinAttack`）。

通过 `getKeyBindingForAction()` 查找实际 `KeyBinding` 时会去掉 `gameKey:` 前缀。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
