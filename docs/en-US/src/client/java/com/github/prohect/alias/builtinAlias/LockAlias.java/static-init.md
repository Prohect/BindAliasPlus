# static-init (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Static initializer that populates the `ACTION_ALIAS_PATTERNS` map with alias-name patterns for each supported game action.

## Remarks

Executed once when the class is loaded.

For each action in `SUPPORTED_ACTIONS`:
1. Strips the `"gameKey:"` prefix to get the bare action name (e.g., `"attack"`, `"forward"`).
2. Generates three alias-name patterns:
   - `"+" + bare` — the press form (e.g., `"+attack"`)
   - `"-" + bare` — the release form (e.g., `"-attack"`)
   - `"builtin" + capitalizedBare` — the builtin form (e.g., `"builtinAttack"`)
3. Stores the patterns list in `ACTION_ALIAS_PATTERNS` keyed by bare action name.

**Purpose:** When locking a game action, `lockModBoundKeys()` uses these patterns to find all mod key bindings (`BINDING_PLUS` entries) whose bound alias name (on press or release) targets the locked action, and blocks those physical keys too. This prevents a user from indirectly triggering a locked action through a custom key binding.

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
