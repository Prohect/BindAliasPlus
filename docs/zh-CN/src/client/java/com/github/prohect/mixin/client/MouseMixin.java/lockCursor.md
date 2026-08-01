# lockCursor 方法（src/client/java/com/github/prohect/mixin/client/MouseMixin.java）

## 语法

```java
@Inject(at = @At("RETURN"), method = "grabMouse")
private void lockCursor(CallbackInfo ci)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `ci` | `CallbackInfo` | 未使用的回调 |

## 备注

注入于 `MouseHandler#grabMouse()` 的 `RETURN`。游戏重新锁定光标后（通常在关闭界面返回 3D 世界之后），遍历 `aliasesWithArgs_notSuggested` 和 `aliasesWithArgs` 中注册的所有 `BuiltinAliasWithBooleanArgs` 实例，并对每个调用 `reapplyToGameKeyMapping()`。这会将游戏的按键映射状态与任何可能被界面切换期间原版 `releaseAll()` 清除的按住别名标志（例如 `+forward`、`+attack`）重新同步。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithBooleanArgs.reapplyToGameKeyMapping](../../../alias/BuiltinAliasWithBooleanArgs.java/reapplyToGameKeyMapping.md) | 对每个按住别名调用的重新应用方法 |
| [Alias.aliasesWithArgs_notSuggested](../../../alias/Alias.java/aliasesWithArgs_notSuggested.md) | 遍历的第一个别名注册表 |
| [Alias.aliasesWithArgs](../../../alias/Alias.java/aliasesWithArgs.md) | 遍历的第二个别名注册表 |
