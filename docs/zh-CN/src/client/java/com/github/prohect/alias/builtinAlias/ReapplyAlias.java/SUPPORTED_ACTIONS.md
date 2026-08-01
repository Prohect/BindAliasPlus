# SUPPORTED_ACTIONS 字段（src/client/java/com/github/prohect/alias/builtinAlias/ReapplyAlias.java）

`reapply` 别名接受的动作名称列表。

## 语法

```java
public static final java.util.List<java.lang.String> SUPPORTED_ACTIONS
```

## 备注

包含用于命令建议的 12 个受支持动作名称：`"attack"`、`"use"`、`"forward"`、`"back"`、`"left"`、`"right"`、`"jump"`、`"sneak"`、`"sprint"`、`"drop"`、`"openInventory"`、`"playerList"`。

每个名称映射到一个 `builtin*` 别名（例如 `"forward"` → `builtinForward`）。名称可以带 `+` 或 `-` 前缀，解析前会被去掉。

**读取者：** `ReapplyAlias.run()` 引用这些名称进行验证/记录日志。

**注意：** 此列表仅用于命令建议——实际解析逻辑会动态处理任何带 `+`/`-` 前缀的名称。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
