# reapplyToGameKeyMapping 方法（src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java）

界面切换后重新同步丢弃按键状态。覆写 `BuiltinAliasWithBooleanArgs` 的默认行为以避免额外的丢弃事件。

## 语法

```java
public void reapplyToGameKeyMapping()
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（无）_ | | |

## 备注

界面关闭后，原版 Minecraft 会对所有 KeyMapping 调用 `releaseAll()`，这通常会清除按住状态。`reapplyToGameKeyMapping()` 机制（由 `ReapplyAlias` 调用）重新启用处于按住状态的别名。

`BuiltinAliasWithBooleanArgs` 的默认行为：若 `flag` 为 true，则以 `"1"` 重新执行——对大多数别名意味着 `setDown(true)` + `clickCount++`。但对 DropAlias 而言，`clickCount++` 会在关闭界面后光标重新锁定时导致**多余的意外丢弃**。

**自定义：** 此覆写仅调用 `keyDrop.setDown(true)` 以恢复按住状态，并有意**不**递增 `clickCount`。这确保玩家在界面切换后回到 3D 世界时不会多丢弃物品。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [run](run.md) | 主要按下/松开处理器 |
| [ReapplyAlias](../ReapplyAlias.java/ReapplyAlias.md) | 界面切换后调用 `reapplyToGameKeyMapping()` |
| [BuiltinAliasWithBooleanArgs.reapplyToGameKeyMapping()](../../BuiltinAliasWithBooleanArgs.java/reapplyToGameKeyMapping.md) | 默认重新应用行为 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
