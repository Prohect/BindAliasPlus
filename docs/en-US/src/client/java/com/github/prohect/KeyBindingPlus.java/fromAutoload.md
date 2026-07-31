# fromAutoload method (src/client/java/com/github/prohect/KeyBindingPlus.java)

## Syntax

```java
public boolean fromAutoload()
```

## Remarks

Record accessor. Returns `true` if this binding originated from the config file
(`bind-alias.cfg`) during [loadCFG](../BindAliasClient.java/loadCFG.md).

Bindings with `fromAutoload = true` are tracked separately so they can be cleared
by `unloadCFGBinds` / `unloadCFGAll` without removing user-created runtime bindings.

Runtime bindings (created via `/bind` or `/bindByAliasName` commands) have `fromAutoload = false`.

## See Also

| Item                                                                                         | Description                         |
| -------------------------------------------------------------------------------------------- | ----------------------------------- |
| [loadCFG](../BindAliasClient.java/loadCFG.md)                                            | Loads autoload bindings from config |
| [UnloadCFGBindsAlias](../alias/builtinAlias/UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | Removes autoloaded bindings         |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
