# static-init（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 备注

在 `GameStateCollector` 类被加载时执行一次。用别名名到人类可读按住键名的映射初始化 `HELD_KEY_NAMES` map，用于 `held_keys` 状态成员：

- `"forward"` → `"forward"`
- `"back"` → `"back"`
- `"left"` → `"left"`
- `"right"` → `"right"`
- `"jump"` → `"jump"`
- `"sneak"` → `"sneak"`
- `"sprint"` → `"sprint"`
- `"attack"` → `"attack"`
- `"use"` → `"use"`
- `"drop"` → `"drop"`

`heldKeysJson` 读取此 map，决定当前按住的哪些别名会出现在 envelope 中。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [heldKeysJson](heldKeysJson.md) | 本 map 的读取方 |
