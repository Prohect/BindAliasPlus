# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGBindsAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description |
| ------ | -------- | ----------- |
| `args` | `String` | Ignored.    |

## Remarks

Removes all autoloaded keybindings from `BindAliasClient.BINDING_PLUS` and cleans up associated command-created aliases.

**Algorithm**:

1. Iterate `BINDING_PLUS`, collecting keys where `binding.fromAutoload()` is true.
2. Also track alias names from `aliasNameOnKeyPressed()` and `aliasNameOnKeyReleased()` for cleanup.
3. Remove collected keys from `BINDING_PLUS`.
4. Remove tracked alias names from `Alias.aliasesWithoutArgs_fromBindCommand`.
5. Log the removal count unless `silentMode` is active.

**Side effects**: Removes entries from `BindAliasClient.BINDING_PLUS` and `Alias.aliasesWithoutArgs_fromBindCommand`.

**Callers**: Invoked by the alias dispatch system and by `UnloadCFGAllAlias.run()`.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
