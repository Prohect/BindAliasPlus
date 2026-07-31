# SUPPORTED_ACTIONS field (src/client/java/com/github/prohect/alias/builtinAlias/ReapplyAlias.java)

List of action names accepted by the `reapply` alias.

## Syntax

```java
public static final java.util.List<java.lang.String> SUPPORTED_ACTIONS
```

## Remarks

Contains the 12 action names supported for command suggestions: `"attack"`, `"use"`, `"forward"`, `"back"`, `"left"`, `"right"`, `"jump"`, `"sneak"`, `"sprint"`, `"drop"`, `"openInventory"`, `"playerList"`.

Each name maps to a `builtin*` alias (e.g., `"forward"` → `builtinForward`). Names can optionally be prefixed with `+` or `-`, which is stripped before resolution.

**Readers:** `ReapplyAlias.run()` references these names for validation/logging.

**Note:** This list is used for command suggestions only — the actual resolution logic handles any `+`/`-` prefixed name dynamically.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
