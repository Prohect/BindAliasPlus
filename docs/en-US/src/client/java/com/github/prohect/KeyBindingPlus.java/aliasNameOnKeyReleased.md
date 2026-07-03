# aliasNameOnKeyReleased method (src/client/java/com/github/prohect/KeyBindingPlus.java)

## Syntax

```java
public java.lang.String aliasNameOnKeyReleased()
```

## Remarks

Record accessor. Returns the alias name dispatched when the bound key is released.

For movement aliases (e.g., `+forward` / `-forward`), this typically contains
the "opposite" alias (e.g., `-forward`) so the action is toggled off on release.

May be an empty string if no release alias is configured.

## See Also

| Item                                                                        | Description                                            |
| --------------------------------------------------------------------------- | ------------------------------------------------------ |
| [aliasNameOnKeyPressed](aliasNameOnKeyPressed.md)                           | The press counterpart                                  |
| [Alias.getOppositeDefinition](../alias/Alias.java/getOppositeDefinition.md) | Computes the release alias for typical boolean actions |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
