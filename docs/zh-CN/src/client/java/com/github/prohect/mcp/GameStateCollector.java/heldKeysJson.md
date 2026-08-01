# heldKeysJson 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
static String heldKeysJson()
```

## 返回值

当前按住的移动/动作键名称的 JSON 数组字符串（例如 `["forward","attack","sneak"]`），无按键按住时返回 `null`。

## 备注

遍历所有已注册的 `BuiltinAliasWithBooleanArgs` 实例（`Alias` 中的 `aliasesWithArgs_notSuggested` 与 `aliasesWithArgs` 两个 map）。对每个 `flag` 为 `true` 的别名，在 `HELD_KEY_NAMES` 中查找人类可读名称（键为别名名，如 `"forward"`、`"attack"`、`"sneak"`、`"jump"`、`"sprint"`、`"drop"` 等）并加入输出数组。为空时返回 `null`（而非 `"[]"`）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BuiltinAliasWithBooleanArgs.flag](../../alias/BuiltinAliasWithBooleanArgs.java/flag.md) | 此处检查的按住状态标志 |
| [MouseMixin.lockCursor](../../mixin/client/MouseMixin.java/lockCursor.md) | 在界面切换后重新应用按住的别名——即 `held_keys` 被强制包含进每个 envelope 的原因 |
