# static-init (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Remarks

Initializes the `ACTION_ALIAS_PATTERNS` map at class load time. For each entry in `SUPPORTED_ACTIONS` (e.g. `gameKey:attack`):

1. Strips the `gameKey:` prefix to get the bare action name (e.g. `attack`).
2. Builds a list of three alias patterns that map to this action:
   - `+attack` — the user-facing lock alias
   - `-attack` — the user-facing unlock alias
   - `builtinAttack` — the internal builtin alias (first letter capitalized)
3. Stores the mapping `"attack" → ["+attack", "-attack", "builtinAttack"]` in `ACTION_ALIAS_PATTERNS`.

This map is later used by `lockModBoundKeys()` and `unlockModBoundKeys()` to determine which mod-bound keys target a given locked action. The static block is used (rather than field initializers) because the logic involves iteration and string manipulation that cannot be expressed in a simple initializer.

No failure modes — all input is from the constant `SUPPORTED_ACTIONS` list.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
