# run method (src/client/java/com/github/prohect/alias/builtinAlias/ReloadCFGAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                       |
| ------ | -------- | ----------------------------------------------------------------- |
| `args` | `String` | Unused. ReloadCFGAlias takes no arguments; the string is ignored. |

## Remarks

Reloads the mod configuration from disk by calling `BindAliasClient.INSTANCE.loadCFG()`.

Algorithm:

1. Calls `BindAliasClient.INSTANCE.loadCFG()`, which reads `run/config/bind-alias.cfg`, clears existing aliases/binds/vars, and reparses the file.

Side effects: all existing aliases, binds, and variables are cleared and re-loaded from the config file. Any state held by running aliases (e.g., `BuiltinAliasWithBooleanArgs.flag`) is reset.

Callers: dispatched by [UserAlias](../../UserAlias.java/UserAlias.md) when the alias name `"reloadCFG"` matches an `AliasRecord`. Can be invoked at any time during gameplay to pick up config changes without restarting.

Error handling: errors during config parsing are logged by `loadCFG()`; the method itself returns normally.

## See Also

| Item                                                                          | Description                                 |
| ----------------------------------------------------------------------------- | ------------------------------------------- |
| [ReloadCFGAlias](ReloadCFGAlias.md)                                           | Owning class                                |
| [BindAliasClient.loadCFG](../../../BindAliasClient.java/loadCFG.md)   | The method that actually reloads the config |
| [BindAliasClient.INSTANCE](../../../BindAliasClient.java/INSTANCE.md) | Singleton instance                          |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
