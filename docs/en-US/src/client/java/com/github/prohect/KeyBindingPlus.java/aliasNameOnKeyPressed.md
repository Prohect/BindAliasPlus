# aliasNameOnKeyPressed method (src/client/java/com/github/prohect/KeyBindingPlus.java)

## Syntax

```java
public java.lang.String aliasNameOnKeyPressed()
```

## Remarks

Record accessor. Returns the alias name dispatched when the bound key is pressed down.

The mod's key-mixin hook iterates `KEY_QUEUE` and, for each `KeyPressed` where
`pressed` is `true`, looks up the corresponding `KeyBindingPlus` in `BINDING_PLUS`
and runs `aliasNameOnKeyPressed` as a [UserAlias](../alias/UserAlias.java/UserAlias.md).

May be an empty string if no alias is configured for key-down.

## See Also

| Item                                                | Description                            |
| --------------------------------------------------- | -------------------------------------- |
| [aliasNameOnKeyReleased](aliasNameOnKeyReleased.md) | The release counterpart                |
| [UserAlias](../alias/UserAlias.java/UserAlias.md)   | The alias type dispatched by this name |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
