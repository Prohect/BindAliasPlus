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

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
