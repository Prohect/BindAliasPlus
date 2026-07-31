# setFromCFG method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public void setFromCFG(boolean fromAutoload)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `fromAutoload` | `boolean` | `true` to mark as CFG-loaded, `false` to mark as user-created |

## Remarks

Sets the `fromCFG` flag after construction. This allows the CFG loading logic (`BindAliasClient.loadCFG()`) to mark existing aliases that were loaded from the config file, distinguishing them from those created interactively.

## See Also

| Item | Description |
|------|-------------|
| [isFromCFG](isFromCFG.md) | Getter for this flag |
| [BindAliasClient.loadCFG](BindAliasClient.java/loadCFG.md) | Uses this setter when loading aliases from cfg |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
