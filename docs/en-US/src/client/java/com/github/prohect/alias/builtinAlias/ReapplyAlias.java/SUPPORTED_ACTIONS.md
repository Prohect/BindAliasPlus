# SUPPORTED_ACTIONS field (src/client/java/com/github/prohect/alias/builtinAlias/ReapplyAlias.java)

## Syntax

```java
public static final java.util.List<java.lang.String> SUPPORTED_ACTIONS
```

## Remarks

An immutable list of action names accepted by `ReapplyAlias` for command suggestion auto-completion: `attack`, `use`, `forward`, `back`, `left`, `right`, `jump`, `sneak`, `sprint`, `drop`, `openInventory`.

Each name maps to a `builtin*` alias (e.g. `forward` → `builtinForward`) via the capitalization logic in `run()`. Read-only after class loading.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
