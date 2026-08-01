# static-init（src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java）

使用每个受支持游戏动作的别名名称模式填充 `ACTION_ALIAS_PATTERNS` 映射的静态初始化器。

## 备注

在类加载时执行一次。

对 `SUPPORTED_ACTIONS` 中的每个动作：
1. 去除 `"gameKey:"` 前缀以获得裸动作名称（例如 `"attack"`、`"forward"`）。
2. 生成三个别名名称模式：
   - `"+" + bare` — 按下形式（例如 `"+attack"`）
   - `"-" + bare` — 松开形式（例如 `"-attack"`）
   - `"builtin" + capitalizedBare` — 内置形式（例如 `"builtinAttack"`）
3. 将模式列表以裸动作名称为键存储在 `ACTION_ALIAS_PATTERNS` 中。

**用途：** 锁定游戏动作时，`lockModBoundKeys()` 使用这些模式找出所有绑定别名名称（按下或松开时）指向被锁定动作的模组按键绑定（`BINDING_PLUS` 条目），并同样阻止这些物理按键。这可以防止用户通过自定义按键绑定间接触发被锁定的动作。

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
