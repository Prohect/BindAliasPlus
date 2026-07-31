# reapplyToGameKeyMapping method (src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java)

## Syntax

```java
public void reapplyToGameKeyMapping()
```

## Remarks

Re-syncs held keys after a screen transition. If `this.flag` is `true`, calls `this.run("1")` to re-inject the press into the game's key mapping.

This counters Minecraft's vanilla behavior: when a screen opens via `setScreen()`, the game calls `releaseAll()` which releases all held keys. After the screen closes, keys that were held before the screen opened would otherwise remain released. The `reapply` alias iterates all boolean-arg aliases and calls this method to restore their state.

Concrete subclasses can override this if they need custom reapply behavior. The default implementation simply re-runs with `"1"` when `flag` is `true`.

## See Also

| Item | Description |
|------|-------------|
| [flag](flag.md) | The held-state flag checked by this method |
| [ReapplyAlias](builtinAlias/ReapplyAlias.java/ReapplyAlias.md) | The `reapply` builtin that triggers this across all boolean aliases |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
